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
package com.streamsets.pipeline.api.delegate.exported;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Delegate interface to manage cluster pipeline jobs.
 */
public interface ClusterJob {

  /**
   * Client to interact with a cluster for running cluster pipelines.
   */
  interface Client {

    /**
     * Creates a cluster.
     * <p/>
     * This method is optional for a client to implement.
     *
     * @param clusterName cluster name.
     * @return the cluster ID.
     */
    default String createCluster(String clusterName) { return null;}

    /**
     * Destroys a cluster. It should be used only if the cluster was created by the client for the current pipeline.
     * <p/>
     * This method is optional for a client to implement.
     *
     * @param clusterId the cluster ID.
     */
    default void terminateCluster(String clusterId) {}

    /**
     * Finds a cluster ID based on a cluster name.
     * <p/>
     * This method is optional for a client to implement. This method is used only for clusters created by the
     * client.
     *
     * @param clusterName cluster name.
     * @return the cluster ID, or <code>NULL</code> if not found.
     */
    default String getActiveCluster(String clusterName) { return null;}

    /**
     * Returns the cluster status.
     *
     * @param clusterId cluster ID.
     * @return the cluster status.
     */
    Properties getClusterStatus(String clusterId);

    /**
     * Uploads pipeline job files to a filesystem accessible to the cluster.
     *
     * @param jobProps job configuration.
     * @param files files to upload.
     * @return the URIs of the uploaded files in the target filesystem.
     * @throws IOException thrown if the files could not be uploaded.
     */
    List<String> uploadJobFiles(Properties jobProps, List<File> files) throws IOException;

    /**
     * Deletes pipeline job files used to run the job from the filesystem accessible to the cluster once the
     * pipeline job ended.
     *
     * @param jobProps job configuration.
     * @throws IOException thrown if the files could not be deleted.
     */
    void deleteJobFiles(Properties jobProps) throws IOException;

    /**
     * Submits a pipeline job for execution to the cluster.
     *
     * @param jobProps job configuration.
     * @return the job configuration augmented with the cluster job information (ID, etc.).
     * @throws IOException thrown if the job could not be submitted.
     */
    Properties submitJob(Properties jobProps) throws IOException;

    /**
     * Returns the pipeline job status.
     *
     * @param jobProps job configuration, it must be of a submitted job (to include cluster job information).
     * @return the job status.
     * @throws IOException thrown if the job status could not be obtained.
     */
    Properties getJobStatus(Properties jobProps) throws IOException;

    /**
     * Terminates a pipeline job.
     *
     * @param jobProps job configuration, it must be of a submitted job (to include cluster job information).
     * @throws IOException thrown if the job could not be terminated.
     */
    void terminateJob(Properties jobProps) throws IOException;
  }

  /**
   * Creates a client to interact with a cluster for running cluster pipelines.
   * <p/>
   * The client is light weight and it does not hold any state or resources.
   *
   * @param clusterProps cluster configuration.
   *
   * @return the client to interact with a cluster for running cluster pipelines.
   */
  Client getClient(Properties clusterProps);

}
