/*
 * Copyright 2017 StreamSets Inc.
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

public class TextUtils {

  /**
   * Valid name is the one that passes the following regular expression.
   *
   * We don't use a regular expression for actual parsing as for this particular expression we will be faster with loop.
   */
  public static final String VALID_NAME= "[0-9A-Za-z_\\s]+";

  private TextUtils() {}

  public static boolean isValidName(String name) {
    if(name == null || name.isEmpty()) {
      return false;
    }

    for(int i = 0; i < name.length(); i++) {
      char c = name.charAt(i);
      if(!Character.isWhitespace(c) && !Character.isLetterOrDigit(c) && c != '_') {
        return false;
      }

    }

    return true;
  }

}
