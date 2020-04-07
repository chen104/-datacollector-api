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

import java.util.List;

/**
 * Allows Connections to verify their configuration.  It is up to the individual Connection type to determine the best
 * way to do this.  For some types that may be simply calling a "ping" function while others may require running a
 * pipeline preview, or something else entirely.
 */
public interface ConnectionVerifier {

  /**
   * Verifying the connection configuration.
   *
   * @param configs The configurations for the Connection to verify.
   * @return A list of {@link ConfigIssue} describing any problems, or an empty list if no problems.
   */
  List<ConfigIssue> verify(List<Config> configs);
}
