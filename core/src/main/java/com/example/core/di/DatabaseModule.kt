package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.local.db.BlogDao
import com.example.core.data.local.db.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BlogDatabase {
        val passphrase: ByteArray = net.sqlcipher.database.SQLiteDatabase.getBytes("satrio".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java, "Blog.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieDao(database: BlogDatabase): BlogDao = database.blogDao()
}