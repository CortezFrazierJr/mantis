/*
 * Copyright 2022 Netflix, Inc.
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
package io.mantisrx.server.worker;

import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.fs.FileSystem;

public interface FileSystemFactory {
  /** Gets the scheme of the file system created by this factory. */
  String getScheme();

  /**
   * Creates a new file system for the given file system URI. The URI describes the type of file
   * system (via its scheme) and optionally the authority (for example the host) of the file
   * system.
   *
   * @param fsUri The URI that describes the file system.
   * @return A new instance of the specified file system.
   * @throws IOException Thrown if the file system could not be instantiated.
   */
  FileSystem create(URI fsUri) throws IOException;
}
