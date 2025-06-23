package com.apptikar.gymondo.core.utils.hiltAnnotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: HiltDispatchers)

enum class HiltDispatchers {
    Default,
    IO,
    Main,
    Unconfined
}