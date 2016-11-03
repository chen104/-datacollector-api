/**
 * Copyright 2016 StreamSets Inc.
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
package com.streamsets.pipeline.api;

import java.util.List;

/**
 * A <code>ProtoSource</code> is base for Data Collector origin stage. Origin stages consume data from an external system
 * creating records that can be processed by processor ({@link Processor}) or destination ({@link Target}) stages.
 *
 * @see Source
 * @see PushSource
 */
public interface ProtoSource<C extends Stage.Context> extends Stage<C> {

  /**
   * <code>Source</code> stage context.
   */
  public interface Context extends Stage.Context {

    /**
     * Returns the output lane names (stream names) of the <code>Source</code>.
     *
     * @return the output lane names (stream names) of the <code>Source</code>.
     */
    public List<String> getOutputLanes();

  }

}
