/*
 * Copyright 2019 Netflix, Inc.
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

package io.mantisrx.server.worker.config;

import io.mantisrx.server.core.CoreConfiguration;
import io.mantisrx.shaded.com.google.common.base.Splitter;
import java.io.File;
import java.net.URI;
import java.util.List;
import org.apache.flink.api.common.time.Time;
import org.skife.config.Config;
import org.skife.config.Default;


public interface WorkerConfiguration extends CoreConfiguration {

    @Config("mantis.agent.mesos.slave.port")
    @Default("5051")
    int getMesosSlavePort();

    @Config("mantis.classloader.parent-first-patterns")
    @Default("java.;scala.;org.apache.flink.;com.esotericsoftware.kryo;org.apache.hadoop.;javax.annotation.;org.xml;javax.xml;org.apache.xerces;org.w3c;org.slf4j;org.apache.log4j;org.apache.logging;org.apache.commons.logging;ch.qos.logback")
    String getAlwaysParentFirstLoaderPatternsString();

    @Config("mantis.cluster.storage-dir")
    URI getClusterStorageDir();

    @Config("mantis.local.storage-dir")
    File getLocalStorageDir();

    default List<String> getAlwaysParentFirstLoaderPatterns() {
        Splitter splitter = Splitter.on(';').omitEmptyStrings();
        return splitter.splitToList(getAlwaysParentFirstLoaderPatternsString());
    }

    @Config("mantis.cluster.id")
    String getClusterId();

    @Config("mantis.ports.metrics")
    @Default("5051")
    int getMetricsPort();

    @Config("mantis.ports.debug")
    @Default("5052")
    int getDebugPort();

    @Config("mantis.ports.console")
    @Default("5053")
    int getConsolePort();

    @Config("mantis.ports.custom")
    @Default("5054")
    int getCustomPort();

    @Config("mantis.ports.sink")
    @Default("5055")
    int getSinkPort();

    @Config("heartbeats.interval")
    @Default("1000")
    int heartbeatInternalInMs();

    @Config("heartbeats.tolerable_consecutive_hearbeat_failures")
    @Default("3")
    int getTolerableConsecutiveHeartbeatFailures();

    @Config("heartbeats.timeout.ms")
    @Default("100")
    int heartbeatTimeoutMs();

    default Time getHeartbeatTimeout() {
        return Time.milliseconds(heartbeatTimeoutMs());
    }

    default Time getHeartbeatInterval() {
        return Time.milliseconds(heartbeatInternalInMs());
    }
}
