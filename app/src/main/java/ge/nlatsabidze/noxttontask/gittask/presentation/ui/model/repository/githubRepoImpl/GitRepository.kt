package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl


import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository

interface GitRepository {

    suspend fun searchRepository(
        query: String,
        perPage: Int
    ): Resource<GithubRepository>
}