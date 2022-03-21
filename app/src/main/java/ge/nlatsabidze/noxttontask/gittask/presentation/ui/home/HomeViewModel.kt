package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.InsertCurrentRepositoryUseCase
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.GithubRepository
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.use_cases.SearchRepositoryUseCase
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRepositoryUseCase: SearchRepositoryUseCase,
    private val insertCurrentRepositoryUseCase: InsertCurrentRepositoryUseCase
) :
    ViewModel() {

    private var searchJob: Job? = null
    private val delayJob: Long = 350

    private val _stateResult = MutableStateFlow<Resource<GithubRepository>>(Resource.EmptyData())
    val stateResult get() = _stateResult

    fun searchCase(query: String, perPage: Int) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(delayJob)
            searchRepositoryUseCase(query, perPage).collectLatest {
                _stateResult.value = it
            }
        }
    }

    fun insertRepository(currentRepository: Item) {
        viewModelScope.launch {
            insertCurrentRepositoryUseCase(currentRepository)
        }
    }
}