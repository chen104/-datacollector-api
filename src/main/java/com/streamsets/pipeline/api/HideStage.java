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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark stage that should not be displayed on the pipeline canvas. Hidden stages have type instructing
 * the framework where else the stage should be used (be visible).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HideStage {
  /**
   * Type of the hidden stage declaring where else the stage should be used in StreamSets infrastructure.
   *
   * One hidden stage can have multiple types (be available in multiple places).
   */
  public static enum Type {
    /**
     * Field process is special kind of processor that works on per-field basis rather then per-record basis. They
     * are used for specific purpose in few places primarily in StreamSets Control Hub.
     */
    FIELD_PROCESSOR

    // In the future we will transition also @StatsAggregatorStage and @ErrorStage annotations here
  }

  /**
   * Type of the hidden stage, see {@Type} for more details.
   *
   * @return Types of the hidden stage
   */
  Type[] value();
}
