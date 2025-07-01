package soft.divan.moodtracker.core.network.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Named
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import soft.divan.moodtracker.core.domain.repository.GigaChatRepository
import soft.divan.moodtracker.core.network.network.api.GigaChatApiService
import soft.divan.moodtracker.core.network.network.api.GigaChatAuthService
import soft.divan.moodtracker.core.network.network.interceptor.AuthInterceptor
import soft.divan.moodtracker.core.network.network.interceptor.GigaChatTokenProvider
import soft.divan.moodtracker.core.network.network.interceptor.RetryInterceptor
import soft.divan.moodtracker.core.network.network.repository.GigaChatRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("authOkHttp")
    fun provideAuthOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY })
            .build()

    @Provides
    @Named("authRetrofit")
    fun provideAuthRetrofit(@Named("authOkHttp") client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ngw.devices.sberbank.ru:9443/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun provideGigaChatAuthService(@Named("authRetrofit") retrofit: Retrofit): GigaChatAuthService =
        retrofit.create(GigaChatAuthService::class.java)

    @Provides
    fun provideTokenProvider(authService: GigaChatAuthService): GigaChatTokenProvider =
        GigaChatTokenProvider(authService)

    @Provides
    fun provideMainOkHttp(
        tokenProvider: GigaChatTokenProvider,
        retry: RetryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenProvider))
        .addInterceptor(retry)
        .addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    fun provideMainRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gigachat.devices.sberbank.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun provideGigaChatApi(retrofit: Retrofit): GigaChatApiService =
        retrofit.create(GigaChatApiService::class.java)

    @Provides
    fun provideRetryInterceptor(): RetryInterceptor = RetryInterceptor()


    @Provides
    fun provideGigaChatRepositoryRepository(impl: GigaChatRepositoryImpl): GigaChatRepository = impl

}
