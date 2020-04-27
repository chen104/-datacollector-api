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

/**
 * The <code>ConnectionDef</code> annotation is used to designate Config Beans as being Connection Definitions too.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConnectionDef {

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
   * The {@link ConnectionVerifier} to use for verifying a Connection's configuration.
   */
  Class<? extends ConnectionVerifier> verifier();

  /**
   * The possible execution engines that a Connection can support.
   */
  ConnectionEngine[] supportedEngines();
}
