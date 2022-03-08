package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRepositoryUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(repo: Item) {
        return roomRepository.deleteRepoFromList(repo)
    }

}