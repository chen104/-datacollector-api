/**
 * Copyright 2016 StreamSets Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
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

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.streamsets.pipeline.api.el.ELEval;
import com.streamsets.pipeline.api.el.ELEvalException;
import com.streamsets.pipeline.api.el.ELVars;

import java.util.List;

/**
 * Base interface for Data Collector stages implementations defining their common context and lifecycle.
 *
 * @see Source
 * @see Processor
 * @see Target
 */
public interface Stage<C extends Stage.Context> {

  /**
   * It provides information about the stage.
   */
  public interface Info {

    /**
     * Returns the name of the stage.
     * <p/>
     * This name is fixed at compile time and it does not change.
     * Different instances of the same stage have the same name.
     *
     * @return the name of the stage.
     */
    public String getName();

    /**
     * Returns the version of the stage.
     *
     * @return the version of the stage.
     */
    public int getVersion();

    /**
     * Returns the instance name of the stage.
     * <p/>
     * This name is assigned when a stage is added to a pipeline.
     * Different instances of the same stage have different instance name.
     *
     * @return the instance name of the stage.
     */
    public String getInstanceName();

  }

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
   * Stage Context that provides runtime information and services to the stage.
   */
  public interface Context extends ELContext {

    /**
     * Returns the current execution mode of the pipeline.
     *
     * @return the current execution mode of the pipeline.
     */
    public ExecutionMode getExecutionMode();

    /**
     * Returns the maximum amount of memory (in bytes) the pipeline can use.
     *
     * @return the maximum amount of memory (in bytes) the pipeline can use.
     */
    public long getPipelineMaxMemory();

    /**
     * Indicates if the pipeline is running in preview mode or not.
     *
     * @return if the pipeline is running in preview mode or not.
     */
    public boolean isPreview();

    /**
     * Creates a configuration issue for the stage (at initialization time).
     *
     * @param configGroup the configuration group of the stage configuration, if applicable.
     * @param configName the configuration name of the stage configuration, if applicable.
     * @param errorCode the <code>ErrorCode</code> for the issue.
     * @param args the arguments for the <code>ErrorCode</code> message.
     * @return the configuration issue to report back.
     */
    public ConfigIssue createConfigIssue(String configGroup, String configName, ErrorCode errorCode, Object... args);

    /**
     * Returns a list with the {@link Info} of all stages in the pipeline.
     *
     * @return a list with the {@link Info} of all stages in the pipeline.
     */
    public List<Info> getPipelineInfo();

    /**
     * Returns the {@link MetricRegistry} used by the pipeline.
     *
     * @return the {@link MetricRegistry} used by the pipeline.
     */
    public MetricRegistry getMetrics();

    /**
     * Creates a {@link Timer} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Timer</code> name.
     * @return A <code>Timer</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Timer createTimer(String name);

    /**
     * Gets the already created {@link Timer} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Timer</code> name.
     * @return the already created <code>Timer</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Timer getTimer(String name);

    /**
     * Creates a {@link Meter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Meter</code> name.
     * @return a <code>Meter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Meter createMeter(String name);

    /**
     * Gets the already created {@link Meter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Meter</code> name.
     * @return the already created <code>Meter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Meter getMeter(String name);

    /**
     * Creates a {@link Counter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Counter</code> name.
     * @return a <code>Counter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Counter createCounter(String name);

    /**
     * Gets the already created {@link Counter} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Counter</code> name.
     * @return the already created <code>Counter</code> namespaced with the pipeline name and the stage instance name plus the given name.
     */
    public Counter getCounter(String name);

    /**
     * Creates a {@link Gauge} namespaced with the pipeline name and the stage instance name plus the given name.
     *
     * @param name the <code>Gauge</code> name.
     * @param gauge Gauge
     * @param <T> Gauge Object Type.
     */
   public <T> Gauge<T> createGauge(String name, Gauge<T> gauge);

      /**
       * Gets the already created {@link Gauge} namespaced with the pipeline name and the stage instance name plus the given name.
       *
       * @param name the <code>Gauge</code> name.
       * @param <T> Gauge Object Type.
       * @return the already created <code>Gauge</code> namespaced with the pipeline name and the stage instance name plus the given name.
       */
    public <T> Gauge<T> getGauge(String name);

    /**
     * Reports an <code>Exception</code> as an error.
     *
     * @param exception the <code>Exception</code> to report as error.
     */
    public void reportError(Exception exception);

    /**
     * Reports an error using a non-localizable error message.
     *
     * @param errorMessage the non-localizable error message.
     */
    public void reportError(String errorMessage);

    /**
     * Reports an error using a localizable error code and arguments for it.
     *
     * @param errorCode the error code to report.
     * @param args the arguments for the <code>ErrorCode</code> message template.
     */
    public void reportError(ErrorCode errorCode, Object... args);

    /**
     * Returns the configured error handling for the stage.
     * <p/>
     * The stage must be coded to honor this configured error handling.
     *
     * @return the configured error handling for the stage.
     */
    public OnRecordError getOnErrorRecord();

    /**
     * Sends a record to the pipeline error stream with an associated <code>Exception</code>
     *
     * @param record the record to send to the error stream.
     * @param exception the associated <code>Exception</code>.
     */
    public void toError(Record record, Exception exception);

    /**
     * Sends a record to the pipeline error stream with an associated non-localizable error message.
     *
     * @param record the record to send to the error stream.
     * @param errorMessage the non-localizable error message.
     */
    public void toError(Record record, String errorMessage);

    /**
     * Sends a record to the pipeline error stream with an associated error code.
     *
     * @param record the record to send to the error stream.
     * @param errorCode the error code to for the record.
     * @param args the arguments for the <code>ErrorCode</code> message template.
     */
    public void toError(Record record, ErrorCode errorCode, Object... args);

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

    /**
     * Returns the time of completion of the previous batch.
     * <p/>
     * @return the time of completion of the previous batch or zero if it is the previous batch processing time is not
     * known.
     */
    public long getLastBatchTime();

    /**
     * Returns the absolute path to the SDC resources directory.
     *
     * @return  the absolute path to the SDC resources directory.
     */
    public String getResourcesDirectory();

    /**
     * Indicates if the pipeline has been stopped while the stage is processing a batch of records.
     *
     * @return if the pipeline has been stopped or not.
     */
    public boolean isStopped();

    /**
     * Creates standard event record with pre-filled required header attributes.
     *
     * @param type Type of the event (value is up to the generating stage)
     * @param version Version of the event type (to support event evolution)
     * @param recordSourceId the ID to identify the record. It should include enough information to track down
     * the record source.
     * @return New record.
     */
    public EventRecord createEventRecord(String type, int version, String recordSourceId);

    /**
     * Sends given event record to event lane.
     *
     * @param record Record that should be send on event lane
     */
    public void toEvent(EventRecord record);
  }

  /**
   * Interface for configuration issues.
   *
   * @see Context#createConfigIssue(String, String, ErrorCode, Object...)
   */
  public interface ConfigIssue {
  }

  /**
   * Initializes the stage.
   * <p/>
   * This method is called once when the pipeline is being initialized before the processing any data.
   * <p/>
   * If the stage returns an empty list of {@link ConfigIssue}s then the stage is considered ready to process data.
   * Else it is considered it is mis-configured or that there is a problem and the stage is not ready to process data,
   * thus aborting the pipeline initialization.
   *
   * @param info the stage information.
   * @param context the stage context.
   */
  public List<ConfigIssue> init(Info info, C context);

  /**
   * Destroys the stage. It should be used to release any resources held by the stage after initialization or
   * processing.
   * <p/>
   * This method is called once when the pipeline is being shutdown. After this method is called, the stage will not
   * be called to process any more data.
   * <p/>
   * This method is also called after a failed initialization to allow releasing resources created before the
   * initialization failed.
   */
  public void destroy();

}
