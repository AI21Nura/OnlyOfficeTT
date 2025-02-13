package com.ainsln.core.network.utils

import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SessionInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = runBlocking {
            if (sessionManager.isAuthorized().first()) {
                val token = sessionManager.getToken().first()
                val portal = sessionManager.getPortal().first()

                val url = chain.request().url.newBuilder()
                    .scheme("https")
                    .host(portal)
                    .build()

                chain.request().newBuilder()
                    .url(url)
                    .addHeader("Authorization", token)
                    .build()
            } else chain.request()
        }
        return chain.proceed(newRequest)
    }
}
