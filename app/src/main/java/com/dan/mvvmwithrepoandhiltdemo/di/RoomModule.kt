package com.dan.mvvmwithrepoandhiltdemo.di

import android.content.Context
import androidx.room.Room
import com.dan.mvvmwithrepoandhiltdemo.room.BlogDao
import com.dan.mvvmwithrepoandhiltdemo.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Dan Kim
 */
@Module
@InstallIn(Singleton::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(database: BlogDatabase): BlogDao {
        return database.blogDao()
    }
}