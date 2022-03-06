package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val searchRepositoryUseCase: SearchRepositoryUseCase
) :
    ViewModel() {

    private var job: Job? = null

    private val _state = MutableStateFlow<List<Item>>(listOf())
    val state get() = _state.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading get() = _loading.asStateFlow()

        fun searchCase(query:String) {
            job?.cancel()
            job = viewModelScope.launch {
                delay(250)
                searchRepositoryUseCase(query).collect {
                    when (it) {
                        is Resource.Success -> {
                            _state.emit(it.data?.items!!)
                        }
                    }
                }
            }
        }
}