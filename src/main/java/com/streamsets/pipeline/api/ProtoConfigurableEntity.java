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

import com.streamsets.pipeline.api.el.ELEval;
import com.streamsets.pipeline.api.el.ELEvalException;
import com.streamsets.pipeline.api.el.ELVars;

import java.util.Map;

/**
 * Shared interface for all active configurable objects - such as Stage or Service.
 *
 * This interface is used to share methods and contexts between applicable stages. It should not be used otherwise.
 */
public interface ProtoConfigurableEntity {

  /**
   * Context to create and use Java Expression Language (EL) evaluators.
   */
  public interface ELContext {

    /**
     * Validates an EL is syntactically correct.
     *
     * @param el EL to validate.
     * @throws ELEvalException if the EL is not syntactically valid.
     */
    public void parseEL(String el) throws ELEvalException;

    /**
     * Creates an {@link ELVars} instance to provide variables to {@link ELEval} when evaluating ELs.
     *
     * @return an empty <code>ELVar</code> instance.
     */
    public ELVars createELVars();

    /**
     * Creates an {@link ELEval} configured with the EL functions and constants defined by the indicated stage
     * configuration.
     *
     * @param configName stage configuration name.
     * @return the configured <code>ELEval</code> instance.
     * @see ConfigDef#elDefs()
     */
    public ELEval createELEval(String configName);

    /**
     * Creates an {@link ELEval} configured with the EL functions and constants defined by the indicated stage
     * configuration plus the additional EL functions and constants specified.
     *
     * @param configName stage configuration name.
     * @param elDefClasses class defining additional EL functions and constants to configure the <code>ELEval</code>
     * instance with.
     * @return the configured <code>ELEval</code> instance.
     * @see ConfigDef#elDefs()
     */
    public ELEval createELEval(String configName, Class<?>... elDefClasses);

  }

  /**
   * Context that provides runtime information and services to the stage.
   */
  public interface Context extends ConfigIssueContext, ELContext, MetricContext {
    /**
     * Return value for given configuration option from data collector main configuration.
     *
     * Stages have their own namespace, so method won't be able to return generic SDC configuration.
     *
     * @param configName Configuration option name
     * @return String representation of the value or null if it's not defined.
     * @deprecated Use getConfiguration() instead.
     */
    @Deprecated
    public String getConfig(String configName);

    /**
     * Return configuration relevant to stages from the data collector main configuration files.
     *
     * Stages have their own namespace, so method won't be able to return generic SDC configuration.
     */
    public Configuration getConfiguration();

    /**
     * Get integer representing runner id - a value that doesn't change for given stage as it's executed in different
     * threads. This value can be used to create temporary resources on remote system to make sure that different
     * instances of the same stage in multi threaded pipeline won't step on each other's toes.
     *
     * @return Returns 0..N representing the runner id.  0 is used for inherent singletons (Error stage, Origin, ...)
     */
    public int getRunnerId();

    /**
     * Returns the absolute path to the SDC resources directory.
     *
     * @return  the absolute path to the SDC resources directory.
     */
    public String getResourcesDirectory();

    /**
     * Return pipeline constants.
     *
     * @return Immutable Map with pipeline constants.
     */
    public Map<String, Object> getPipelineConstants();

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
