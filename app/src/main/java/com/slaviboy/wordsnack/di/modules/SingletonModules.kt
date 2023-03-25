package com.slaviboy.wordsnack.di.modules

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object SingletonModules {

    //@Singleton
    @Provides
    fun provideAssets(
        @ApplicationContext context: Context
    ): AssetManager {
        return context.assets
    }

    //@Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}