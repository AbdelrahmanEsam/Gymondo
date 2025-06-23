package com.apptikar.gymondo.di

import android.content.Context
import com.apptikar.gymondo.core.utils.network.connectivity.ConnectivityObserver
import com.apptikar.gymondo.core.utils.network.connectivity.NetworkConnectivityObserver
import com.apptikar.gymondo.data.datastore.DataStoreUserPreferences
import com.apptikar.gymondo.data.datastore.DataStoreUserPreferencesImpl
import com.apptikar.gymondo.data.httpClient.GymondoHttpClient
import com.apptikar.gymondo.data.httpClient.GymondoHttpClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClientEngine = OkHttp.create()


    @Singleton
    @Provides
    fun provideGymondoHttpClient(httpEngine: HttpClientEngine): GymondoHttpClient =
        GymondoHttpClientImpl(httpEngine)


    @Singleton
    @Provides
    fun bindConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        NetworkConnectivityObserver(context)




    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStoreUserPreferences {
        return  DataStoreUserPreferencesImpl(applicationContext)
    }
}

