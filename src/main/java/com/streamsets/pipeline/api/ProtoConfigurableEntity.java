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
package com.streamsets.pipeline.api;

/**
 * Shared interface for all active configurable objects - such as Stage or Service.
 *
 * This interface is used to share methods and contexts between applicable stages. It should not be used otherwise.
 */
public interface ProtoConfigurableEntity {

  /**
   * Context that provides runtime information and services to the stage.
   */
  public interface Context extends ConfigIssueContext {
    /**
     * Returns the absolute path to the SDC resources directory.
     *
     * @return  the absolute path to the SDC resources directory.
     */
    public String getResourcesDirectory();

    /**
     * Creates an empty record.
     *
     * @param recordSourceId the ID to identify the record. It should include enough information to track down
     * the record source.
     * @return an empty record with the specified ID.
     */
    Record createRecord(String recordSourceId);

    /**
     * Creates an empty record including the original raw data of the record.
     *
     * @param recordSourceId the ID to identify the record. It should include enough information to track down
     * the record source.
     * @param raw the record raw data.
     * @param rawMime the MIME type of the raw data.
     * @return an empty record with the specified ID and raw data.
     */
    Record createRecord(String recordSourceId, byte[] raw, String rawMime);

  }
}
