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

/**
 * Record Field is a wrapper on top of field describing where the field is located (e.g. the path and record associated).
 */
public interface RecordField {
  /**
   * Field path of the filed that the cursor is currently pointing to.
   *
   * @return Path string or null if the cursor is invalid.
   */
  String getFieldPath();

  /**
   * Field name - the last part of field path.
   *
   * Few examples:
   * * For /map/inner, the name will be 'inner'
   * * For /list[2], the name will be only 'list' (e.g. the list name)
   *
   * @return String name or null if the cursor is invalid.
   */
  String getFieldName();

  /**
   * Field that the cursor is currently pointing to.
   *
   * @return Field or null if the cursor is invalid.
   */
  Field getField();

  /**
   * Parent field of the field that the cursor is currently pointing to, if applicable.  May not be available in all
   * contexts.
   *
   * @return Parent field or null if there is no such parent field, or if it is not available
   */
  default Field getParentField() {
    return null;
  }

  /**
   * Path of the parent field of the field that the cursor is currently pointing to, if applicable.  May not be
   * available in all contexts.
   *
   * @return Path of the parent field or null if there is no such parent field path, or if it is not available
   */
  default String getParentFieldPath() {
    return null;
  }

  /**
   * Index of the field that the cursor is currently pointing to, within a parent LIST type field, if applicable.  If
   * not, then an invalid index will be returned (-1).
   *
   * @return the index of this field within the parent LIST, or -1 if not applicable or not available
   */
  default int getIndexInParent() {
    return -1;
  }

  /**
   * Return record associated with the field.
   *
   * @return Record where the field is from.
   */
   Record getRecord();

}
