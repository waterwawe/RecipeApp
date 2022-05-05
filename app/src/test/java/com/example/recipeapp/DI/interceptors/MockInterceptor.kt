package com.example.recipeapp.DI.interceptors

import android.net.Uri
import android.util.Log
import com.example.recipeapp.DI.MockApi
import com.example.recipeapp.DI.NetworkConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.*
import java.io.ByteArrayInputStream
import java.io.IOException

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return process(chain.request())
    }

    private fun process(request: Request): Response {
        val uri = Uri.parse(request.url.toString())

        Log.d("Test Http Client", "URL call: $uri")

        val headers = request.headers

        val path = uri.path

        return when {
            path == null -> makeResponse(request, headers, 404, "Unknown")
            path.startsWith(NetworkConfig.ENDPOINT_PREFIX + "favrecipes") -> MockApi.process(request)
            else -> makeResponse(request, headers, 404, "Unknown")
        }
    }

    private fun makeResponse(request: Request, headers: Headers, code: Int, content: String): Response {
        val responseBody = object : ResponseBody() {
            override fun contentType(): MediaType? = "application/json".toMediaTypeOrNull()

            override fun contentLength(): Long = content.toByteArray().size.toLong()

            override fun source(): BufferedSource =
                ByteArrayInputStream(content.toByteArray()).source().buffer()
        }

        return Response.Builder()
            .protocol(Protocol.HTTP_2)
            .code(code)
            .request(request)
            .headers(headers)
            .message("")
            .body(responseBody)
            .build()
    }

    private fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            val body = copy.body ?: return ""
            body.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return ""
        }
    }

}