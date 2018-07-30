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

import java.util.Set;
import java.util.regex.Pattern;

/**
 * General store for various data used by SDC and its various components.
 *
 * This store does not interpret the data in any way and deals with them as a black box. Storing and retrieving them
 * by various components. Agreeing on proper data format and schema is up to the writer and caller.
 *
 * The objects within the store are separated into namespaces - each namespace is independent of each other and can
 * contain objects with the same id. All objects are versioned and the store can contain the same object with multiple
 * versions. The store imposes structural requirements for namespace, object id and version - all those must comport to
 * exposed format. However the semantics of the values is not imposed and is left to the caller. Content itself is
 * not interpreted in any way.
 */
public interface BlobStore {

  /**
   * Validation pattern for namespace.
   */
  public static Pattern VALID_NAMESPACE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");


  /**
   * Validation pattern for id.
   */
  public static Pattern VALID_ID_PATTERN = Pattern.compile("[A-Za-z0-9_.:-]+");

  /**
   * Store a new object inside the blob store.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @param version Version of the object.
   * @param content The actual content.
   * @throws StageException
   */
  public void store(String namespace, String id, long version, String content) throws StageException;

  /**
   * Return latest version for given object.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @return Latest version (usual integer comparison)
   * @throws StageException
   */
  public long latestVersion(String namespace, String id) throws StageException;

  /**
   * Validates if given object exists on at least one version.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @return If given object in given namespace exists
   */
  public boolean exists(String namespace, String id);

  /**
   * Validates if given object exists on given version.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @param version Version of the object.
   * @return If given object on given in given namespace exists
   */
  public boolean exists(String namespace, String id, long version);

  /**
   * Return all versions associated with given object.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @return Set of all stored versions.
   * @throws StageException
   */
  public Set<Long> allVersions(String namespace, String id);

  /**
   * Retrieve given object.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @param version Version of the object.
   * @return Object itself
   * @throws StageException
   */
  public String retrieve(String namespace, String id, long version) throws StageException;

  /**
   * Sub-interface to encapsulate tuple of content with it's version.
   */
  public interface VersionedContent {
    /**
     * Version of the content.
     */
    long version();

    /**
     * Actual content
     */
    String content();
  }

  /**
   * Convenience method to return latest version for given object.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @return Object itself
   * @throws StageException
   */
  public VersionedContent retrieveLatest(String namespace, String id) throws StageException;

  /**
   * Delete given object from the store.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @param version Version of the object.
   * @throws StageException
   */
  public void delete(String namespace, String id, long version) throws StageException;

  /**
   * Delete all versions of given object.
   *
   * @param namespace Namespace of the object.
   * @param id Id of the object.
   * @throws StageException
   */
  public void deleteAllVersions(String namespace, String id) throws StageException;
}
