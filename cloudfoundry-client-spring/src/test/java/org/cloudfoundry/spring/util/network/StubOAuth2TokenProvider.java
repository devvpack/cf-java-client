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

package org.cloudfoundry.spring.util.network;

import reactor.core.publisher.Mono;

public final class StubOAuth2TokenProvider implements OAuth2TokenProvider {

    private final Mono<String> token;

    public StubOAuth2TokenProvider(String token) {
        this.token = Mono.just(token);
    }

    @Override
    public Mono<String> getToken() {
        return this.token;
    }

}
