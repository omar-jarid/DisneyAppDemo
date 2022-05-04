package omarjarid.disneyappdemo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import omarjarid.data.NetworkDatasource
import omarjarid.disneyappdemo.framework.network.NetworkDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun provideNetworkDatasource(ds: NetworkDatasourceImpl):NetworkDatasource
}