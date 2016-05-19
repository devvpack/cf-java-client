/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.client.v2.serviceinstances;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.cloudfoundry.Nullable;
import org.cloudfoundry.QueryParameter;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

/**
 * The request payload for the Create Service Instance
 */
@Value.Immutable
abstract class AbstractCreateServiceInstanceRequest {

    /**
     * The accept incomplete flag
     */
    @Nullable
    @QueryParameter("accepts_incomplete")
    abstract Boolean getAcceptsIncomplete();

    /**
     * The name
     */
    @JsonProperty("name")
    abstract String getName();

    /**
     * Key/value pairs of all arbitrary parameters to pass along to the service broker
     */
    @JsonProperty("parameters")
    @Nullable
    abstract Map<String, Object> getParameters();

    /**
     * The service plan id
     */
    @JsonProperty("service_plan_guid")
    abstract String getServicePlanId();

    /**
     * The space id
     */
    @JsonProperty("space_guid")
    abstract String getSpaceId();

    /**
     * A list of tags for the service instance
     */
    @JsonProperty("tags")
    @Nullable
    abstract List<String> getTags();

}
