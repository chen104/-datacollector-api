/**
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.api.lineage;

import com.streamsets.pipeline.api.ErrorCode;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface LineagePublisher {

  /**
   * Interface for configuration issues.
   *
   * @see Context#createConfigIssue(ErrorCode, Object...)
   */
  public interface ConfigIssue {
  }

  /**
   * Stage Context that provides runtime information and services to the stage.
   */
  public interface Context {

    /**
     * Creates a configuration issue for the publisher (at initialization time).
     *
     * @param errorCode the <code>ErrorCode</code> for the issue.
     * @param args the arguments for the <code>ErrorCode</code> message.
     * @return the configuration issue to report back.
     */
    public ConfigIssue createConfigIssue(ErrorCode errorCode, Object... args);

    /**
     * Return value for given configuration option from data collector main configuration.
     *
     * Publisher have it's own namespace, so method won't be able to return generic SDC configuration.
     *
     * @param configName Configuration option name
     * @return String representation of the value or null if it's not defined.
     */
    public String getConfig(String configName);

  }

  /**
   * Initializes the publisher.
   *
   * This method is called once during start up of data collector.
   *
   * If the publisher returns an empty list of {@link ConfigIssue}s then it is considered ready to process events.
   *
   * Else it is considered it is mis-configured or that there is a non-recoverable problem and the publisher is
   * not ready to process events, thus aborting the data collector initialization.
   *
   * @param context the publisher context.
   */
  public List<ConfigIssue> init(Context context);

  /**
   * Start processing given blocking queue of events and push them to the lineage collector. This method will be
   * called in separate thread and hence the plugin can block on the queue without affecting other processes
   * in data collector.
   *
   * @param queue Blocking queue with lineage events
   * @param context the publisher context.
   */
  public void run(BlockingQueue<LineageEvent> queue, Context context);

  /**
   * Stops the publisher.
   *
   * This method is called once when the data collector is being shutdown.
   *
   * This method is also called after a failed initialization to allow releasing resources created before the
   * initialization failed.
   */
  public void stop();
}
