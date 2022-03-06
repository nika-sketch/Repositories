package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository

import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.GithubRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepository {

    @GET("search/repositories")
    suspend fun searchForRepository(
        @Query("q") query: String = "App",
        @Query("per_page") perPage: Int? = 20,
        @Query("page") page: Int? = 1
    ): Response<GithubRepository>
}