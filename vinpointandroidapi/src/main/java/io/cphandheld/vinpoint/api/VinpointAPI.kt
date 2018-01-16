package io.cphandheld.vinpoint.api

import io.cphandheld.vinpoint.api.exceptions.NullEnvironmentException
import io.cphandheld.vinpoint.api.models.CPEnvironment

/* Object is better for singletons in Kotlin, needs to be hydrated on application init with parameters
    from the device XML environment */
object VinpointAPI {

    private var activeEnvironment: CPEnvironment? = null

    var Environment: CPEnvironment
        get() {
            if (activeEnvironment == null) {
                throw NullEnvironmentException("No instance of CPEnvironment was provided to VinpointAPI on application init!")
            }
            return activeEnvironment as CPEnvironment
        }
        set(environment) {
            activeEnvironment = environment
        }
}