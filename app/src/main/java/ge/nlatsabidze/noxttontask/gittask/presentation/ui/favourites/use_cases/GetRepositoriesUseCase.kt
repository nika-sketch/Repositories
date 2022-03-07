package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(): Flow<List<Item>> {
        return roomRepository.getRepos()
    }

}