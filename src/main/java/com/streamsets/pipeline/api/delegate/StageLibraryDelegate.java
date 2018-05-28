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
package com.streamsets.pipeline.api.delegate;

public interface StageLibraryDelegate {

  /**
   * Context that provides runtime information and services to the delegate.
   */
  public interface Context {

    /**
     * Return value for given configuration option from data collector main configuration.
     *
     * @param configName Configuration option name
     * @return String representation of the value or null if it's not defined.
     */
    public String getConfig(String configName);
  }

  /**
   * Set context for the delegate.
   *
   * This method will be called once by the framework immediately after instantiation and before any
   * other use of the instance.
   *
   * @param context Instance of context.
   */
  public void setContext(Context context);

}
