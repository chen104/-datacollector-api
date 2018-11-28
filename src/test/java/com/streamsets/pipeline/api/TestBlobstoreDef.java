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

import org.junit.Assert;
import org.junit.Test;

public class TestBlobstoreDef {

  @Test
  public void testBlobstoreDef() {
    BlobStoreDef def = new BlobStoreDef("test", "v1", 12345);
    Assert.assertEquals("test", def.getNamespace());
    Assert.assertEquals("v1", def.getId());
    Assert.assertEquals(12345, def.getVersion());
  }

  @Test
  public void testBlobstoreDefSetVersion() {
    BlobStoreDef def = new BlobStoreDef("test", "v1");
    def.setVersion(12345);
    Assert.assertEquals("test", def.getNamespace());
    Assert.assertEquals("v1", def.getId());
    Assert.assertEquals(12345, def.getVersion());
  }
}
