package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.dao.RepositoryDao
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

@Database(entities = [Item::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun userDao(): RepositoryDao
}