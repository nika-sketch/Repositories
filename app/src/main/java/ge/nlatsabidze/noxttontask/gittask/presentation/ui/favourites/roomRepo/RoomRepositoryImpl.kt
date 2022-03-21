package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.dao.RepositoryDao

class RoomRepositoryImpl @Inject constructor(
    private val repositoryDao: RepositoryDao
) :
    RoomRepository {

    override fun getRepositories(): Flow<List<Item>> {
        return repositoryDao.getReposisotires()
    }

    override suspend fun getRepositoriesById(id: Int): Item? {
        return repositoryDao.getRepositoryById(id)
    }

    override suspend fun insertRepository(repo: Item) {
        return repositoryDao.insertRepository(repo)
    }

    override suspend fun deleteRepoFromList(repo: Item) {
        return repositoryDao.deleteRepository(repo)
    }
}