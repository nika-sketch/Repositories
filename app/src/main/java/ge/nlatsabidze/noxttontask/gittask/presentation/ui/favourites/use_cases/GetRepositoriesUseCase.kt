package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository

class GetRepositoriesUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(): Flow<List<Item>> {
        return roomRepository.getRepositories()
    }

}