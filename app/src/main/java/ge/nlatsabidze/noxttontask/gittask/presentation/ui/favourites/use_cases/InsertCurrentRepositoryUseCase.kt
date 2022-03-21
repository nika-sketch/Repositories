package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import javax.inject.Inject
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository

class InsertCurrentRepositoryUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(repository: Item) {
        roomRepository.insertRepository(repository)
    }

}