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

import java.util.Collections;
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
   * SSH Port forwarding handler. Returned by the <B>SshTunnelService</B> on initialization.
   */
  interface PortsForwarding {

    /**
     * Returns if port forwarding is enabled or not.
     */
    boolean isEnabled();

    /**
     * Returns the host and port pairs mapping for port forwarding.
     * <p/>
     * The keys of the Map are the target host and port pairs given in the <B>SshTunnelService#init()</B> method.
     * <p/>
     * The values of the Map are the forwarder host and port pairs.
     * <p/>
     * IMPORTANT: if port forwarding is not enabled the key and value of each Map entry are the same host and port pair.
     */
    Map<HostPort, HostPort> getPortMapping();

    /**
     * Checks the port forwarding health, throws a <B>StageException</B> if unhealthy.
     */
    void healthCheck() throws StageException;
  }

  /**
   * It should be used by stages as default value instead of NULL.
   */
  PortsForwarding NONE = new PortsForwarding() {
    @Override
    public boolean isEnabled() {
      return false;
    }

    @Override
    public Map<HostPort, HostPort> getPortMapping() {
      return Collections.emptyMap();
    }

    @Override
    public void healthCheck() throws StageException {
    }
  };

  /**
   * Returns if SSH tunneling is enabled or not.
   */
  boolean isEnabled();

  /**
   * Starts the ports forwarding if the configuration of the service indicates tunneling is enabled,
   * otherwise is a No-Op.
   * <p/>
   * This method must be called before the stage attempts to connect to the external system and the connection
   * to the external system must be done using the host and port pairs returned by the SshTunnel
   * <p/>
   * There will be a single SSH Tunnel with one port forwarding per target host and port pair given.
   */
  PortsForwarding start(List<HostPort> targetHostsPorts);

  /**
   * Stops the SSH tunnel. If tunneling is disabled is a No-Op.
   */
  void stop(PortsForwarding portsForwarding);

}
