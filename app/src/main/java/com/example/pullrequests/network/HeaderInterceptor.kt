package com.example.pullrequests.network

import com.example.pullrequests.utils.HEADER_ACCEPT
import com.example.pullrequests.utils.HEADER_AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        // Note: I usually keep the token in SharedPrefs or
        // gradle.properties so that it can't be extracted from the apk
        val nRequest = request.newBuilder()
            .addHeader(HEADER_ACCEPT, "application/vnd.github+json")
            .addHeader(HEADER_AUTHORIZATION, "Bearer ")
            .build()
        return chain.proceed(nRequest)
    }
}
