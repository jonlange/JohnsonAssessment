package com.jonlange.jhtassessment.di

import android.content.Context
import com.jonlange.core.ResourceLoader
import com.jonlange.data.repository.LocalWorkoutRepo
import com.jonlange.data.repository.LocalWorkoutRepoImpl
import com.jonlange.jhtassessment.data.AndroidResourceLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        allowTrailingComma = true // Turning this on because data supplied is malformed
    }

    @Provides
    @Singleton
    fun provideResourceLoader(
        @ApplicationContext context: Context,
        json: Json
    ): ResourceLoader = AndroidResourceLoader(context, json)

    @Provides
    @Singleton
    fun providesLocalWorkoutRepo(resourceLoader: ResourceLoader) : LocalWorkoutRepo =
        LocalWorkoutRepoImpl(resourceLoader = resourceLoader)

    companion object {
        @Provides
        @Singleton
        fun provideAppContext(
            @ApplicationContext appContext: Context
        ) = appContext
    }
}