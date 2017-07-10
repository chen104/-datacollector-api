/*
 * Copyright 2017 StreamSets Inc.
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

package com.streamsets.pipeline.api.lineage;

public class LineageEventConstants {

  public static final String EVENT_TYPE = "eventType";
  public static final String PIPELINE_VERSION ="version";
  public static final String PIPELINE_UUID = "uuid";
  public static final String PIPELINE_USER = "user";
  public static final String PIPELINE_START_TIME = "pipelineStartTime";
  public static final String PIPELINE_NAME = "name";
  public static final String PIPELINE_DATACOLLECTOR_ID = "dataCollectorId";

  // specific attributes - each LineageEventType needs a subset of these...
  public static final String TIME_STAMP = "timeStamp";
  public static final String PERMALINK = "permaLink";
  public static final String DESCRIPTION = "description";

  public static final String LOGICAL_ENDPOINT_TYPE = "logicalEndPointType";
  public static final String LOGICAL_ENTITY_NAME = "logicalEntityName";
  public static final String PHYSICAL_ENDPOINT_TYPE = "physicalEndPointType";
  public static final String PHYSICAL_ENTITY_NAME = "physicalEntityName";
  public static final String PHYSICAL_ENTITY_LOCATION = "entityLocation";
  public static final String ENTITY_MODE = "entityMode";

  public static final String TAGS = "tags";
  public static final String PROPERTIES = "properties";

  private LineageEventConstants() {
    // Instantiation is prohibited
  }
}
