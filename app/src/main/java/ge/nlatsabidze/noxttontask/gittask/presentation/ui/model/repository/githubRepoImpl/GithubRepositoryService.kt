package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository

interface GithubRepositoryService {

    @GET("search/repositories")
    suspend fun searchForRepository(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
    ): Response<GithubRepository>
}