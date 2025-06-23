package com.apptikar.gymondo.data.httpClient

import io.ktor.client.HttpClient

interface GymondoHttpClient {
    val httpClient: HttpClient
}