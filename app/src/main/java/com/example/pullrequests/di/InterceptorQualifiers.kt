package com.example.pullrequests.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Header

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Logger
