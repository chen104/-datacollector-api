/*
 * Copyright 2018 StreamSets Inc.
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
package com.streamsets.pipeline.api.interceptor;

import com.streamsets.pipeline.api.BlobStore;
import com.streamsets.pipeline.api.StageType;

/**
 * Default interceptor creator is used to create instance of interceptor in case that no interceptors were specified
 * by the runtime framework (Control Hub in this case).
 */
public interface DefaultInterceptorCreator {

  /**
   * Context that provides runtime information and services to the creator.
   */
  public interface Context {
    /**
     * Return value for given configuration option from data collector main configuration.
     *
     * @param configName Configuration option name
     * @return String representation of the value or null if it's not defined.
     */
    public String getConfig(String configName);

    /**
     * Returns SDC singleton instance for the BLOB store
     */
    public BlobStore getBlobStore();
  }

  /**
   * Create interceptor if needed for given stage.
   *
   * @param stageType Stage type
   * @param context Creator context
   * @return Interceptor that should be used for given stage or null if no interceptor is needed.
   */
  public Interceptor create(StageType stageType, Context context);

  /**
   * Default interceptor creator that will never create any interceptors.
   */
  public static class Default implements DefaultInterceptorCreator {
    @Override
    public Interceptor create(StageType stageType, Context context) {
      return null;
    }
  }
}
