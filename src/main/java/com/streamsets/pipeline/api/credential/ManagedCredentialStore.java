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
package com.streamsets.pipeline.api.credential;

import com.streamsets.pipeline.api.StageException;

import java.util.List;

public interface ManagedCredentialStore extends CredentialStore {
  /**
   * Returns a credential value associated to the given name.
   * @param name reference name for the credential.
   * @param groups groups the user can belong to store this credential
   * @param credentialValue The vaule to store
   * @throws StageException thrown if the credential could not be stored because of permissions or other reason.
   */
  void store(List<String> groups, String name, String credentialValue) throws StageException;


  /**
   * Deletes a credential stored
   * @param name reference name for the credential.
   * @throws StageException thrown if the credential could not be stored because of permissions or other reason.
   */
  void delete(String name) throws StageException;

  /**
   * Returns a list of all reference names in the credential store
   * @return List of credential references
   * @throws StageException thrown if the credential names could not be read.
   */
  List<String> getNames() throws StageException;
}
