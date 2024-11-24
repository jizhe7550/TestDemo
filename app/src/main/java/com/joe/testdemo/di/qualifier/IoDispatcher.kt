package com.joe.testdemo.di.qualifier

import javax.inject.Qualifier

/**
 * Default dispatcher
 * refer to Dispatchers.IO
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher