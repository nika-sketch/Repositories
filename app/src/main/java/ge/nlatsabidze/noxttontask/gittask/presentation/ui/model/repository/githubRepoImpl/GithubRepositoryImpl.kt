package ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.repository.githubRepoImpl


import javax.inject.Inject
import retrofit2.Response
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository

class GithubRepositoryImpl @Inject constructor(
    private var apiService: GithubRepositoryService,
) : GitRepository {

    override suspend fun searchRepository(
        query: String,
        perPage: Int
    ): Resource<GithubRepository> = handleResponse {
        apiService.searchForRepository(query,perPage)
    }


    private suspend fun <T> handleResponse(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return Resource.Success(body)
            }
            return Resource.Error(response.errorBody().toString())

        } catch (e: Exception) {
            return Resource.Error("exception")
        }
    }
}