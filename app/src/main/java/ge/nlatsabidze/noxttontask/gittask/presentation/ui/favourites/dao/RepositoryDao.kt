package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.dao

import androidx.room.*
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getReposisotires(): Flow<List<Item>>

    @Query("SELECT * FROM repositories WHERE id = :id")
    suspend fun getRepositoryById(id: Int): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(repo: Item)

    @Delete
    suspend fun deleteRepository(repo: Item)
}