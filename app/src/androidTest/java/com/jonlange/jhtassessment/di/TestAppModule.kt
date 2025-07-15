package com.jonlange.jhtassessment.di

import com.jonlange.data.repository.LocalWorkoutRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        allowTrailingComma = true
    }

    @Provides
    @Singleton
    fun provideFakeLocalWorkoutRepo(): LocalWorkoutRepo =
        FakeLocalWorkoutRepo()


}