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
package com.streamsets.pipeline.api;

/**
 * Various flags describing how given stage behaves that Data Collector framework can use when executing the flags. If
 * not certain, specifying no flags at all is the most safest option.
 */
public enum StageBehaviorFlags {

  // Use when given stage is not communicating with an external system. E.g. the stage's output is only direct function
  // of the stage argument and input record. Framework can use this to cache output of the stage and reuse it to speed
  // up processing.
  PURE_FUNCTION,

  // Stage is passing records through unchanged - e.g. the records themselves are not changed, they might be just filtered or
  // sent to different output lanes. Adding headers to fields or record is considered a change.
  PASSTHROUGH,

  // Use when given stage is executing given by the user. Best examples are scripting processors. Framework can use this
  // flag to further restrict stage execution privileges (at a slight cost of slower execution time).
  USER_CODE_INJECTION,
}
