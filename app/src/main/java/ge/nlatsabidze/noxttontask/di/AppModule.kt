package ge.nlatsabidze.noxttontask.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl.GitRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl.GithubRepositoryImpl
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl.GithubRepositoryService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitCurrency(): GithubRepositoryService =
        Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(GithubRepositoryService::class.java)


    @Provides
    @Singleton
    fun provideCurrencyRepository(api: GithubRepositoryService): GitRepository {
        return GithubRepositoryImpl(api)
    }


}