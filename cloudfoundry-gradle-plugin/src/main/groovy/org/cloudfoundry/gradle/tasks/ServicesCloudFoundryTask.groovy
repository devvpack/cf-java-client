/*
 * Copyright 2012 the original author or authors.
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

package org.cloudfoundry.gradle.tasks

import org.cloudfoundry.client.lib.domain.CloudApplication
import org.cloudfoundry.client.lib.domain.CloudService
import org.cloudfoundry.gradle.text.FlexibleTableOutput
import org.gradle.api.tasks.TaskAction

/**
 * Task used to display service instances.
 */
class ServicesCloudFoundryTask extends AbstractCloudFoundryTask {
    ServicesCloudFoundryTask() {
        super()
        description = 'Displays information about service instances'
    }

    @TaskAction
    void showServices() {
        withCloudFoundryClient {
            List<CloudService> services = client.services

            if (services.isEmpty()) {
                log 'No services'
            } else {
                def servicesToApps = mapServicesToApps(services)

                FlexibleTableOutput output = new FlexibleTableOutput()

                services.each { CloudService service ->
                    output.addRow(name: service.name,
                            service: service.label,
                            provider: service.provider,
                            version: service.version,
                            plan: service.plan,
                            'bound apps': servicesToApps[service.name].join(', '))
                }
                log 'Services\n' + output.toString()
            }
        }
    }

    protected def mapServicesToApps(def services) {
        def servicesToApps = [:]
        services.each { servicesToApps[it.name] = [] }

        List<CloudApplication> apps = client.applications
        apps.each { app ->
            app.services.each { serviceName -> servicesToApps[serviceName] << app.name }
        }
        servicesToApps
    }
}