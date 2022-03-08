package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.dao

import androidx.room.*
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getRepos(): Flow<List<Item>>

    @Query("SELECT * FROM repositories WHERE id = :id")
    suspend fun getRepoById(id: Int): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: Item)

//    @Query("DELETE FROM repos WHERE id = :id")
//    suspend fun deleteRepoById(id: Int)

    @Delete
    suspend fun deleteRepository(repo: Item)
}