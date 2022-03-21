package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.use_cases

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl.GitRepository

class SearchRepositoryUseCase @Inject constructor(
    private val githubrepository: GitRepository
) {
    suspend operator fun invoke(
        query: String,
        perPage: Int
    ): Flow<Resource<GithubRepository>> = flow {
        emit(Resource.Loading())
        emit(githubrepository.searchRepository(query, perPage))
    }
}