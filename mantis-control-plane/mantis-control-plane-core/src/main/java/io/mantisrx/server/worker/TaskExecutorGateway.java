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

import io.mantisrx.common.Ack;
import io.mantisrx.server.core.ExecuteStageRequest;
import io.mantisrx.server.core.domain.WorkerId;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.apache.flink.runtime.rpc.RpcGateway;

public interface TaskExecutorGateway extends RpcGateway {
  CompletableFuture<Ack> submitTask(ExecuteStageRequest request);

  CompletableFuture<Ack> cancelTask(WorkerId workerId);

  CompletableFuture<String> requestThreadDump();

  @RequiredArgsConstructor
  class TaskAlreadyRunningException extends Exception {
    private final WorkerId currentlyRunningWorkerTask;
  }

  class TaskNotFoundException extends Exception {

    public TaskNotFoundException(WorkerId workerId) {
      super(String.format("Task %s not found", workerId.toString()));
    }
  }
}
