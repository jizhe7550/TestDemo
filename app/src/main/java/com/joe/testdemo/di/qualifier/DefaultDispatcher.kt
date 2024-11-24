package com.joe.testdemo.di.qualifier

import javax.inject.Qualifier

/**
 * Default dispatcher
 * refer to Dispatchers.Default
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher