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

import java.util.List;

/**
 * Allows Connections to upgrade their configuration from previous versions.  Similar upgrades should also be done in
 * any Stages using a Connection type to ensure that the configs are also upgraded when a Connection is not being used.
 */
public interface ConnectionUpgrader {

  /**
   * Upgrades the connection configuration from a previous version to current version.
   *
   * @param fromVersion version recorded in the connection configuration to upgrade.
   * @param toVersion the version to upgrade the configuration to.
   * @param configs The configurations to upgrade.
   * @return The upgraded configuration.
   */
  List<Config> upgrade(int fromVersion, int toVersion, List<Config> configs);
}
