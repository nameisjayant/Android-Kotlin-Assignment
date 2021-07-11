package com.codingwithjks.assignment.di

import android.app.Application
import android.service.autofill.UserData
import androidx.room.Room
import com.codingwithjks.assignment.data.dao.MedicineDao
import com.codingwithjks.assignment.data.dao.UserDao
import com.codingwithjks.assignment.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): UserDatabase =
        Room.databaseBuilder(application, UserDatabase::class.java, "userdatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesUserDao(db: UserDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun providesMedicineDao(db: UserDatabase): MedicineDao = db.medicineDao()
}