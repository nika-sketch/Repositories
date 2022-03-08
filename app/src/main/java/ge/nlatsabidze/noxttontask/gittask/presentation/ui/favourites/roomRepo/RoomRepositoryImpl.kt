package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.dao.RepositoryDao
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val repositoryDao: RepositoryDao):
    RoomRepository {

    override fun getRepos(): Flow<List<Item>> {
        return repositoryDao.getRepos()
    }

    override suspend fun getRepoById(id: Int): Item? {
        return repositoryDao.getRepoById(id)
    }

    override suspend fun insertRepo(repo: Item) {
        return repositoryDao.insertRepo(repo)
    }

//    override suspend fun deleteRepo(id: Int) {
//        return repositoryDao.deleteRepoById(id)
//    }

    override suspend fun deleteRepoFromList(repo: Item) {
        return repositoryDao.deleteRepository(repo)
    }
}