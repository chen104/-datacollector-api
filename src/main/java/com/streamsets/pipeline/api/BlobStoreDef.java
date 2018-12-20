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

public class BlobStoreDef {

  private String namespace;
  private String id;
  private long version;

  public BlobStoreDef(String namespace, String id, long version) {
    this.namespace = namespace;
    this.id = id;
    this.version = version;
  }

  public BlobStoreDef(String namespace, String id) {
    this.namespace = namespace;
    this.id = id;
  }

  public String getNamespace() {
    return namespace;
  }

  public String getId() {
    return id;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

}