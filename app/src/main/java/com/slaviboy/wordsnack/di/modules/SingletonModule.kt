package com.slaviboy.wordsnack.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAssets(
        @ApplicationContext context: Context
    ): AssetManager {
        return context.assets
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}