/*
 * Copyright 2020 StreamSets Inc.
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
package com.streamsets.pipeline.api;

import com.streamsets.pipeline.api.base.BaseSource;

import java.util.List;

/**
 * Allows Connections to verify their configuration.  It is up to the individual Connection type to determine the best
 * way to do this.  For some types that may be simply calling a "ping" function while others may require running a
 * pipeline preview, or something else entirely.  In any case, subclasses should implement the {@link #initConnection()}
 * method to provide this logic, and optionally use the {@link #destroyConnection()} method for any cleanup.  Subclasses
 * should also include the Config Bean with the associated {@link ConnectionDef} as the configuration for this stage.
 */
public abstract class ConnectionVerifier extends BaseSource {
  @Override
  protected List<ConfigIssue> init() {
    List<ConfigIssue> issues = super.init();
    issues.addAll(initConnection());
    return issues;
  }

  @Override
  public void destroy() {
    destroyConnection();
  }

  @Override
  public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {
    return null;
  }

  /**
   * Verifies the Connection's configuration.  Subclasses should override this to provide the logic to do the
   * verification.
   *
   * @return  A list of {@link ConfigIssue} describing any problems, or an empty list if no problems.
   */
  protected abstract List<ConfigIssue> initConnection();

  /**
   * Destroys the Connection, if applicable.  Subclasses can override this to provide the logic for that; the default
   * implementation is a no-op.
   */
  protected void destroyConnection() {};
}
