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
package com.streamsets.pipeline.api.impl;

import com.streamsets.pipeline.api.ErrorCode;

/**
 * Thrown by {@link TypeSupport} implementations when a value conversion is not possible.
 */
public class TypeSupportConversionException extends IllegalArgumentException {
  public final ErrorCode errorCode;
  public final Object[] params;

  public TypeSupportConversionException(final ErrorCode errorCode, final Object... params) {
    super(Utils.format(errorCode.getMessage(), params));

    this.errorCode = errorCode;
    this.params = params;
  }
}
