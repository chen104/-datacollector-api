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

import java.util.Map;

public abstract class BaseInterceptor implements Interceptor {

  /**
   * Context associated with this instance.
   */
  private Interceptor.Context context;

  /**
   * Returns the interceptor context.
   */
  protected Interceptor.Context getContext() {
    return context;
  }

  /**
   * Initialize the interceptor. This method should not be overridden by subclasses, instead the
   * init(Map<String,String>) should be used (overridden)
   */
  @Override
  public boolean init(Map<String, String> parameters, Context context) {
    this.context = context;
    return init(parameters);
  }

  /**
   * Initializes the interceptor. Subclasses should override this method for initialization.
   *
   * This implementation is a no-operation.
   */
  protected boolean init(Map<String, String> parameters) {
    return true;
  }

  /**
   * Default implementation of destroy does not do anything.
   */
  @Override
  public void destroy() {
  }

}
