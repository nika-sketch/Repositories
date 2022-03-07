package ge.nlatsabidze.noxttontask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.localDb.LocalDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LocalDataBase::class.java,
        "REPOSITORY_DB"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDataBase) = db.userDao()
}