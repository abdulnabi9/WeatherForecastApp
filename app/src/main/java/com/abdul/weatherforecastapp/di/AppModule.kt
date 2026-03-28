package com.abdul.weatherforecastapp.di

import android.content.Context
import androidx.room.Room
import com.abdul.weatherforecastapp.BuildConfig
import com.abdul.weatherforecastapp.data.local.AppDatabase
import com.abdul.weatherforecastapp.data.local.WeatherDao
import com.abdul.weatherforecastapp.data.remote.WeatherApi
import com.abdul.weatherforecastapp.data.repository.WeatherRepositoryImpl
import com.abdul.weatherforecastapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)


    @Provides
    fun provideDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_db"
        ).build()


    @Provides
    fun provideDao(db: AppDatabase) = db.weatherDao()


    @Provides
    fun provideRepo(
        api: WeatherApi,
        dao: WeatherDao,
        @Named("api_key") apiKey: String
    ): WeatherRepository = WeatherRepositoryImpl(api, dao,apiKey)




    @Provides
    @Named("api_key")
    fun provideApiKey(): String = BuildConfig.WEATHER_API_KEY
}
