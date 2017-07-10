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

import java.util.List;
import java.util.Map;

public interface LineageEvent {

  /**
   * fetch general parameter - EventType
   * @return EventType
   */
  String getEventType();

  /**
   * fetch general parameter - PipelineVersion
   * @return PipelineVersion
   */
  String getPipelineVersion();

  /**
   * fetch general parameter - PipelineUuid
   * @return PipelineUuid
   */
  String getPipelineUuid();

  /**
   * fetch general parameter -  PipelineUser
   * @return PipelineUser
   */
  String getPipelineUser();

  /**
   * fetch general parameter -  PipelineStartTime
   * @return pipelineStartTime
   */
  String getPipelineStartTime();

  /**
   * fetch general parameter - PipelineName
   * @return PipelineName
   */
  String getPipelineName();

  /**
   * fetch general parameter - DataCollectorId
   * @return DataCollectorId
   */
  String getPipelineDataCollectorId();

  /**
   * Setter to add a specific attribute to the LineageEvent.
   * @param name - Attribute's name.
   * @param value - Attribute's value.
   */
  void setSpecificAttribute(String name, String value);

  /**
   * Getter for a specific attribute.
   * @return attribute
   */
  String getSpecificAttribute(String name);

  /**
   * Sets the tag array.
   * @param tags
   */
  void setTags(List<String> tags);

  /**
   * Retrieves the tags array.
   * @return
   */
  List<String> getTags();

  /**
   * Set the properties map.
   *
   * @param properties
   */
  void setProperties(Map<String, String> properties);

  /**
   * Gets the properties map.
   *
   * @return properties map.
   */
  Map<String, String> getProperties();

  /**
   * Provides a list of all the specific attributes associated with this Lineage Event type which are not set.
   * @return list of all specific attributes which are not set.
   */
  List<String> missingSpecificAttributes();

}
