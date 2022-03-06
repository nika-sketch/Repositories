package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl


import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.GithubRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource

interface GitRepository {

    suspend fun searchRepository(
        query: String
    ): Resource<GithubRepository>
}