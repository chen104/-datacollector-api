/*
 * Copyright 2019 StreamSets Inc.
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

import java.util.List;

  /**
   * A SourceResponseSink is used to pass responses from destinations or processors back to the origin about the batch
   * that was processed. After a batch is processed, an origin can retrieve these responses from the SourceResponseSink.
   *
   * An example use case is an HTTP Server origin that consumes data for processing.
   * The response records could be used to indicate whether the data was successfully processed and the
   * origin could then send the appropriate response to the client.
   *
   * A new SourceResponseSink is created for each Batch and responses should be interpreted as responses
   * to the entire batch rather than individual records.
   */
public interface SourceResponseSink {

  /**
   * Processors or destinations can use this method to add a response to the batch being processed.
   *
   * @param record a response record to be consumed by the origin
   */
  public void addResponse(Record record);

  /**
   *  Origins can use getResponseRecords to retrieve responses added by processors and destinations
   *  during the processing of the batch. Individual response records are NOT attributable to individual
   *  records in the initial batch - they are responses to the batch as a whole.
   *
   *  The only guarantee made about the ordering of the list is that responses from upstream stages
   *  will precede those from downstream stages. Consider the following pipeline:
   *
   *        O -- P1 -- D1
   *          \
   *           - P2 -- D2
   *
   *  If each stage adds one response record after processing the batch, these are all valid interleavings
   *  of the responses and may be returned by getResponseRecords:
   *
   *        P1, D1, P2, D2
   *        P1, P2, D2, D1
   *        P1, P2, D1, D2
   *        P2, D2, P1, D1
   *        P2, P1, D2, D1
   *        P2, P1, D1, D2
   *
   * @return a list of response records for the just-processed batch
   */
  public List<Record> getResponseRecords();

}
