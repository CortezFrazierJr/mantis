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

package io.mantisrx.master.resourcecluster.proto;

import io.mantisrx.server.master.resourcecluster.TaskExecutorID;
import io.mantisrx.shaded.com.google.common.base.Joiner;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Builder
@Value
public class ScaleResourceRequest {
    String clusterId;

    String skuId;

    String region;

    Optional<MantisResourceClusterEnvType> envType;

    int desireSize;

    @Singular
    List<TaskExecutorID> idleInstances;

    public String getScaleRequestId() {
        return Joiner.on('-').join(
            this.clusterId,
            this.region,
            this.envType.isPresent() ? this.getEnvType().get().name() : "",
            this.skuId,
            this.desireSize);
    }

}
