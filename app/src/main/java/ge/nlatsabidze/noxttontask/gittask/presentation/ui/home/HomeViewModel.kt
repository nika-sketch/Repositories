package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.InsertCurrentRepositoryUseCase
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

    private val _state = MutableStateFlow<MutableList<Item>>(mutableListOf())
    val state get() = _state.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading.asStateFlow()

    private val _errorMessage = MutableStateFlow(false)
    val errorMessage get() = _errorMessage.asStateFlow()

    fun searchCase(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(delayJob)
            searchRepositoryUseCase(query).collect {
                when (it) {
                    is Resource.Success -> {
                        _loading.value = false
                        _state.value = it.data?.items!! as MutableList<Item>
                    }
                    is Resource.Loading -> {
                        _state.value = mutableListOf()
                        _loading.value = true
                    }
                    is Resource.Error -> {
                        _errorMessage.value = true
                    }
                }
            }
        }
    }

    fun insertRepository(currentRepository: Item) {
        viewModelScope.launch {
            insertCurrentRepositoryUseCase(currentRepository)
        }
    }
}