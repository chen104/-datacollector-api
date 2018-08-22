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
package com.streamsets.pipeline.api.impl;

import com.streamsets.pipeline.api.FieldBatch;
import com.streamsets.pipeline.api.Record;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRecordBasedFieldBatch {

  @Test
  public void testGetFieldName() {
    // Maps
    validateGetFieldName("/map", "map");
    validateGetFieldName("/map/inner", "inner");
    validateGetFieldName("/map/really_inner", "really_inner");

    // Lists
    validateGetFieldName("/list[0]", "list");
    validateGetFieldName("/list[666]", "list");

    // Supper nested
    validateGetFieldName("/map/in/list[0]", "list");
    validateGetFieldName("/list[1]/in/map", "map");

    // Root
    validateGetFieldName("", "");
    validateGetFieldName("[1]", "");
  }

  private static void validateGetFieldName(String inputPath, String expectedName) {
    Record record = Mockito.mock(Record.class);
    FieldBatch batch = new RecordBasedFieldBatch(record, Collections.singleton(inputPath).iterator());
    assertTrue(batch.next());
    assertEquals(expectedName, batch.getFieldName());

  }
}
