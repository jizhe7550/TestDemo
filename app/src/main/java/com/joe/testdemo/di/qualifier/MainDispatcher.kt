package com.joe.testdemo.di.qualifier

import javax.inject.Qualifier

/**
 * Default dispatcher
 * refer to Dispatchers.Main
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher