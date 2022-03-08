package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRepos(): Flow<List<Item>>
    suspend fun getRepoById(id: Int): Item?
    suspend fun insertRepo(repo: Item)
    suspend fun deleteRepo(id: Int)
    suspend fun deleteRepoFromList(repo: Item)
}