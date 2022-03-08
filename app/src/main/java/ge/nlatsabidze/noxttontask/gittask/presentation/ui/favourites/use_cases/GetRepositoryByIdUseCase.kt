package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.roomRepo.RoomRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import javax.inject.Inject

class GetRepositoryByIdUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {

    suspend operator fun invoke(id: Int): Item? {
        return roomRepository.getRepositoriesById(id)
    }
}