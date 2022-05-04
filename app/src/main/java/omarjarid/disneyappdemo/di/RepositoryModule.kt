package omarjarid.disneyappdemo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import omarjarid.data.PersonaggioRepositoryImpl
import omarjarid.domain.repository.PersonaggioRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providePersonaggioRepository(
        repo: PersonaggioRepositoryImpl
    ): PersonaggioRepository
}