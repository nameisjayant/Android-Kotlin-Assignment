package com.codingwithjks.assignment.network

import com.codingwithjks.assignment.data.model.Medicine
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiService @Inject constructor() {

    private val client = HttpClient(Android) {

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        engine {
            socketTimeout = 100_000
            connectTimeout = 100_000
        }

    }

    suspend fun getData():Medicine {
        return client.get {
            url("https://run.mocky.io/v3/f469f924-5d21-4f53-b37e-94282bd90e82")
        }
    }

}