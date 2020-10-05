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
package com.streamsets.pipeline.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;

/**
 * The <code>ConnectionDef</code> annotation is used to designate Config Beans as being Connection Definitions too.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConnectionDef {

  class Constants {
    /**
     * Expected {@link Dependency#triggeredByValues} value for {@link ConfigDef#connectionType()} field which
     * denotes manual entry for {@link ConnectionDef} object attributes.
     */
    public static final String CONNECTION_SELECT_MANUAL = "MANUAL";

    /**
     * {@link ChooserValues} for the {@link ConfigDef#connectionType()} field.
     */
    public static class ConnectionChooserValues implements ChooserValues {

      private static final List<String> VALUES = Collections.singletonList(CONNECTION_SELECT_MANUAL);
      private static final List<String> LABELS = Collections.singletonList("None");

      @Override
      public String getResourceBundle() {
        return null;
      }

      @Override
      public List<String> getValues() {
        return VALUES;
      }

      @Override
      public List<String> getLabels() {
        return LABELS;
      }
    }
  }

  /**
   * A user-facing label or name for this Connection Definition.
   */
  String label();

  /**
   * An internal, unique, and static name or ID for this Connection Definition.
   */
  String type();

  /**
   * A user-facing description for this Connection Definition.
   */
  String description();

  /**
   * The current version of this Connection Definition.  It should be incremented whenever a change is made that
   * requires the {@link #upgraderDef()} to be run.
   */
  int version();

  /**
   * The YAML-based upgrader definition associated with this Connection Definition that knows how to upgrade a
   * Connection configuration when the {@link #version()} is incremented.
   */
  String upgraderDef();

  /**
   * The possible execution engines that a Connection can support.
   */
  ConnectionEngine[] supportedEngines();
}
