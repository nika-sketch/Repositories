package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import javax.inject.Inject
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository

class GetRepositoryByIdUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {

    suspend operator fun invoke(id: Int): Item? {
        return roomRepository.getRepositoriesById(id)
    }
}