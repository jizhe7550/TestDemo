package com.joe.testdemo.di.qualifier

import javax.inject.Qualifier

/**
 * Main immediate dispatcher, it will run immediately
 * refer to Dispatchers.Main.immediate
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher