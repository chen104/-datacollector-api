/*
 * Copyright 2019 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.api.service.sshtunnel;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.impl.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Service that provides SSH Tunnel support.
 * <p/>
 * It initiates a forward SSH tunnel to the configured SSH server.
 */
public interface SshTunnelService {

  /**
   * Host and port pair.
   */
  class HostPort {
    private final String host;
    private final int port;

    public HostPort(String host, int port) {
      this.host = Utils.checkNotNull(host, "host");
      Utils.checkArgument(port > 0 && port <= 65535, Utils.formatL("Host '{}', port '{}' out of range", host, port));
      this.port = port;
    }

    public String getHost() {
      return host;
    }

    public int getPort() {
      return port;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      HostPort that = (HostPort) o;
      return port == that.port &&
          host.equals(that.host);
    }

    @Override
    public int hashCode() {
      return Objects.hash(host, port);
    }

    @Override
    public String toString() {
      return "HostPortPair{" +
          "host='" + host + '\'' +
          ", port=" + port +
          '}';
    }
  }

  /**
   * Returns if SSH tunneling is enabled or not.
   */
  boolean isEnabled();

  /**
   * Starts the SSH tunnel and ports forwarding through the SSH tunnel if the configuration of the service indicates
   * tunneling is enabled otherwise is a No-Op.
   * <p/>
   * There will be a single SSH Tunnel with one port forwarding per target host and port given.
   * <p/>
   * This method must be called before the stage attempts to connect to the external system and the connection
   * to the external system must be done using the host and port pairs returned by the SshTunnel.
   * <p/>
   * @return The target host port to local host port forwarding mapping. If SSH tunnel is not enabled the key and
   * value of each Map entry are the same host and port pair.
   */
  Map<HostPort, HostPort> start(List<HostPort> targetHostsPorts);

  /**
   * Checks the SSH tunnel health throwing a <B>StageException</B> if unhealthy.
   */
  void healthCheck() throws StageException;

  /**
   * Stops the SSH tunnel. If tunneling is disabled is a No-Op.
   */
  void stop();

}