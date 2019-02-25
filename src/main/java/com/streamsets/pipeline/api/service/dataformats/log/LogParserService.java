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

package com.streamsets.pipeline.api.service.dataformats.log;

import com.streamsets.pipeline.api.service.dataformats.DataParser;
import com.streamsets.pipeline.api.service.dataformats.DataParserException;

import java.io.Reader;
import java.io.StringReader;

/**
 * Service for parsing logs from input streams
 */
public interface LogParserService {

  default DataParser getLogParser(String id, String data) throws DataParserException {
    return getLogParser(id, new StringReader(data));
  }

  default DataParser getLogParser(String id, Reader reader) throws DataParserException {
    return getLogParser(id, reader, 0);
  }

  /**
   * Returns a parser to be used to parse logs
   * @param id The reader id
   * @param reader The reader to be used to read the log to parse
   * @param offset The offset from which the reader will start reading the log
   * @return A {@link DataParser} to be used to parse the log read by the reader
   * @throws DataParserException Exception thrown if DataParser cannot be built either bedause offset is wrong or reader
   * cannot correctly read the log
   */
  DataParser getLogParser(String id, Reader reader, long offset) throws DataParserException;

}
