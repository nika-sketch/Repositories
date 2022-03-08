package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRepositories(): Flow<List<Item>>
    suspend fun getRepositoriesById(id: Int): Item?
    suspend fun insertRepository(repo: Item)
    suspend fun deleteRepoFromList(repo: Item)
}