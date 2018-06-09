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
package com.streamsets.pipeline.api.base;

import com.streamsets.pipeline.api.FieldProcessor;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.impl.RecordBasedFieldBatch;

/**
 * Field processor implementation providing empty Data Collector lifecycle methods and convenience methods for subclasses.
 */
public abstract class BaseFieldProcessor extends SingleLaneRecordProcessor implements FieldProcessor  {
  @Override
  protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {
    process(new RecordBasedFieldBatch(record));
    batchMaker.addRecord(record);
  }

}
