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

import com.streamsets.pipeline.api.Label;

import java.util.Arrays;
import java.util.List;

import static com.streamsets.pipeline.api.lineage.LineageEventConstants.DESCRIPTION;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.ENTITY_MODE;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.LOGICAL_ENDPOINT_TYPE;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.LOGICAL_ENTITY_NAME;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.PERMALINK;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.PHYSICAL_ENDPOINT_TYPE;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.PHYSICAL_ENTITY_LOCATION;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.PHYSICAL_ENTITY_NAME;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.PROPERTIES;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.TAGS;
import static com.streamsets.pipeline.api.lineage.LineageEventConstants.TIME_STAMP;

public enum LineageEventType implements Label {
  START(
      "START",
      true,
      Arrays.asList(
          TAGS,
          PROPERTIES,
          PERMALINK,
          DESCRIPTION
      )),
  STOP(
      "STOP",
      true,
      Arrays.asList(
          TIME_STAMP,
          TAGS,
          PROPERTIES,
          PERMALINK,
          DESCRIPTION
      )),
  ENTITY_CREATED(
      "ENTITY_CREATED",
      false,
      Arrays.asList(
          LOGICAL_ENDPOINT_TYPE,
          PHYSICAL_ENDPOINT_TYPE,
          PHYSICAL_ENTITY_NAME,
          LOGICAL_ENTITY_NAME,
          PHYSICAL_ENTITY_LOCATION,
          ENTITY_MODE,
          TAGS,
          PROPERTIES,
          PERMALINK,
          DESCRIPTION
      )),
  ENTITY_READ_OR_WRITTEN(
      "ENTITY_READ_OR_WRITTEN",
      false,
      Arrays.asList(
          LOGICAL_ENDPOINT_TYPE,
          PHYSICAL_ENDPOINT_TYPE,
          PHYSICAL_ENTITY_NAME,
          LOGICAL_ENTITY_NAME,
          PHYSICAL_ENTITY_LOCATION,
          ENTITY_MODE,
          TAGS,
          PROPERTIES,
          PERMALINK,
          DESCRIPTION,
          ENTITY_MODE
      )),
  ;
  private String label;
  private boolean frameworkOnly;
  private List<String> specificAttributeNames;

  LineageEventType(String label, boolean frameworkOnly, List<String> specificAttributes){
    this.label = label;
    this.frameworkOnly = frameworkOnly;
    this.specificAttributeNames = specificAttributes;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public boolean isFrameworkOnly() {
    return frameworkOnly;

  }

  public List<String> getSpecificAttributeNames() {
    return specificAttributeNames;
  }
}
