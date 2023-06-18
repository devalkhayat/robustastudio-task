package com.photoweather.app.util.di



import androidx.room.Room
import com.photoweather.data.common.Constants.DATABASE_NAME
import com.photoweather.data.common.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val LocalModule= module {

    single {
        Room.databaseBuilder(
            context = androidContext(),
            LocalDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}