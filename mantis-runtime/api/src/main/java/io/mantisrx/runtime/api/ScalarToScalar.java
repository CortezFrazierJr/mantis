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

package io.mantisrx.runtime.api;


import java.util.Collections;
import java.util.List;

import io.mantisrx.runtime.api.computation.ScalarComputation;
import io.mantisrx.runtime.api.parameter.ParameterDefinition;
import io.mantisrx.runtime.common.codec.Codec;


public class ScalarToScalar<T, R> extends StageConfig<T, R> {

    private InputStrategy inputStrategy;
    private ScalarComputation<T, R> computation;
    private List<ParameterDefinition<?>> parameters;


    ScalarToScalar(ScalarComputation<T, R> computation,
                   Config<T, R> config, Codec<T> inputCodec) {
        super(config.description, inputCodec, config.codec, config.inputStrategy, config.parameters, config.concurrency);
        this.computation = computation;
        this.inputStrategy = config.inputStrategy;
        this.parameters = config.parameters;
    }

    public ScalarComputation<T, R> getComputation() {
        return computation;
    }

    public InputStrategy getInputStrategy() {
        return inputStrategy;
    }

    @Override
    public List<ParameterDefinition<?>> getParameters() {
        return parameters;
    }

    public static class Config<T, R> {

        private Codec<R> codec;
        private String description;
        // default input type is serial for 'collecting' use case
        private InputStrategy inputStrategy = InputStrategy.SERIAL;
        private volatile int concurrency = StageConfig.DEFAULT_STAGE_CONCURRENCY;

        private List<ParameterDefinition<?>> parameters = Collections.emptyList();

        public Config<T, R> codec(Codec<R> codec) {
            this.codec = codec;
            return this;
        }

        public Config<T, R> serialInput() {
            this.inputStrategy = InputStrategy.SERIAL;
            return this;
        }

        public Config<T, R> concurrentInput() {
            this.inputStrategy = InputStrategy.CONCURRENT;
            return this;
        }

        public Config<T, R> concurrentInput(final int concurrency) {
            this.inputStrategy = InputStrategy.CONCURRENT;
            this.concurrency = concurrency;
            return this;
        }

        public Config<T, R> description(String description) {
            this.description = description;
            return this;
        }

        public Codec<R> getCodec() {
            return codec;
        }

        public Config<T, R> withParameters(List<ParameterDefinition<?>> params) {
            this.parameters = params;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public InputStrategy getInputStrategy() {
            return inputStrategy;
        }

        public int getConcurrency() {
            return concurrency;
        }
    }
}
