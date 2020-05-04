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
package com.streamsets.pipeline.api.gateway;

/**
 * API Gateway Info, primarily used in the Microservice pipeline to use Data Collector instance as API Gateway.
 */
public interface GatewayInfo {

  /**
   * Return Pipeline ID of the Microservice Pipeline
   *
   * @return the Pipeline ID
   */
  String getPipelineId();

  /**
   * Return the service name for the API Gateway, used in the gateway rest point URL.
   * http://localhost:18630/rest/v1/gateway/<ServiceName>
   *
   * @return the Gateway Service name
   */
  String getServiceName();

  /**
   * Return the REST Service API Endpoint.
   *
   * @return the REST Service HTTP URL
   */
  String getServiceUrl();

  /**
   * Return true to use protected Data Collector URL
   * "http://localhost:18630/rest/v1/gateway/<service name>.".
   *
   * Otherwise, Gateway URL is an unprotected Data Collector URL -
   * "http://localhost:18630/public-rest/v1/gateway/<service name>."
   *
   * @return Return true to use protected Gateway Endpoint
   */
  boolean getNeedGatewayAuth();

  /**
   * Return the Gateway secret to use in the HTTP request header.
   *
   * To avoid users calling REST Service URL directly instead of going through gateway URL,
   * we use GATEWAY_SECRET header, and REST Service will process requests only if secret matches.
   *
   * @return the gateway secret
   */
  String getSecret();

}
