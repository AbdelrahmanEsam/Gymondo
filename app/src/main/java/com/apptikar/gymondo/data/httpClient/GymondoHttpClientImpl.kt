package com.apptikar.gymondo.data.httpClient

import com.apptikar.gymondo.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import javax.inject.Inject

class GymondoHttpClientImpl @Inject constructor(private val httpClientEngine: HttpClientEngine) :
    GymondoHttpClient {

    @OptIn(ExperimentalSerializationApi::class)
    override val httpClient: HttpClient = HttpClient(engine = httpClientEngine) {

        defaultRequest {
            url(BuildConfig.base_url)
            header(HttpHeaders.Accept, Json)
            header(HttpHeaders.ContentType, Json)
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                    ignoreUnknownKeys = true
                    explicitNulls = false
                },
            )
        }
    }

}