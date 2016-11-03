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

/**
 * A <code>PushSource</code> is a type of Data Collector origin stage that consumes or listen for incoming data and
 * pushes them down to({@link Processor}) or destination ({@link Target}) stages.
 *
 * @see Source
 * @see ProtoSource
 */
public interface PushSource extends ProtoSource<PushSource.Context> {

  /**
   * <code>PushSource</code> stage context.
   */
  public interface Context extends ProtoSource.Context {

    /**
     * Create BatchMaker for creating new batch.
     *
     * This method is thread safe.
     *
     * @return New instance of BatchMaker
     */
    public BatchMaker createBatchmaker();

    /**
     * Process given batch - run it through rest of the pipeline. The method returns true
     * if and only if the data reached all destinations properly, otherwise it returns false.
     * Source can use this to for example properly respond to HTTP call with error status.
     *
     * This is a blocking call, the execution will wait until a pipeline runner is available.
     *
     * This method is thread safe.
     *
     * @param batchMaker Batch to be passed to the pipeline.
     * @return true if and only if the batch has reached all destinations
     */
    public boolean processBatch(BatchMaker batchMaker);

    /**
     * Register offset in the external system from which the source can resume operation after
     * pipeline restart.
     *
     * This method is thread safe.
     *
     * @param offset String representation of the source offset
     */
    public void commitOffset(String offset);
  }

  /**
   * Returns the ideal number of threads that the source would like to run. Data Collector
   * will use this information to create sufficiently large pipeline runner pool.
   *
   * @return Expected number of threads
   */
  public int getNumberOfThreads();

  /**
   * When a pipeline is initialized and prepared to run, the Data Collector calls this method to start the Source.
   *
   * When this method returns the pipeline transitions to stopped state. Use methods in the Context to create batches
   * of data and propagate them to Data Collector.
   *
   * @param maxBatchSize the requested maximum batch size that a single call to Context.processBatch should produce
   * @throws StageException if the <code>PushSource</code> had an error while consuming data or creating records.
   */
  void produce(int maxBatchSize) throws StageException;
}
