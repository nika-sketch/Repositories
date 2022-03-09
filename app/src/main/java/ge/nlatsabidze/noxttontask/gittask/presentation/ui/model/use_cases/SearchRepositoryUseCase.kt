package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.use_cases

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl.GitRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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