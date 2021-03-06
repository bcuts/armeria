/*
 * Copyright 2015 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.server;

import java.util.Optional;

final class ExactPathMapping extends AbstractPathMapping {

    private final String exactPath;
    private final String loggerName;
    private final Optional<String> exactPathOpt;
    private final String strVal;

    ExactPathMapping(String exactPath) {
        this.exactPath = ensureAbsolutePath(exactPath, "exactPath");
        exactPathOpt = Optional.of(exactPath);
        loggerName = loggerName(exactPath);
        strVal = "exact: " + exactPath;
    }

    @Override
    protected String doApply(String path) {
        return exactPath.equals(path) ? path : null;
    }

    @Override
    public String loggerName() {
        return loggerName;
    }

    @Override
    public String metricName() {
        return exactPath;
    }

    @Override
    public Optional<String> exactPath() {
        return exactPathOpt;
    }

    @Override
    public int hashCode() {
        return strVal.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExactPathMapping &&
               (this == obj || exactPath.equals(((ExactPathMapping) obj).exactPath));
    }

    @Override
    public String toString() {
        return strVal;
    }
}
