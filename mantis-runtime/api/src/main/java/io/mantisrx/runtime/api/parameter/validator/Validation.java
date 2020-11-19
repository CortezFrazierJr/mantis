/*
 *
 * Copyright 2020 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.mantisrx.runtime.api.parameter.validator;

public class Validation {

    private boolean failedValidation;
    private String failedValidationReason;

    Validation(boolean failedValidation,
               String failedValidationReason) {
        this.failedValidation = failedValidation;
        this.failedValidationReason = failedValidationReason;
    }

    public static Validation failed(String reason) {
        return new Validation(true, reason);
    }

    public static Validation passed() {
        return new Validation(false, null);
    }

    public boolean isFailedValidation() {
        return failedValidation;
    }

    public String getFailedValidationReason() {
        return failedValidationReason;
    }
}
