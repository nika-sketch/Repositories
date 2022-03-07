package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.GetRepositoriesUseCase
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val getRepositoriesUseCase: GetRepositoriesUseCase) : ViewModel() {

    private val _state =
        MutableStateFlow<List<Item>>(emptyList())
    val state get() = _state.asStateFlow()

    private var job: Job? = null

    init {
        getReposFromRoom()
    }

    private fun getReposFromRoom() {
        job?.cancel()
        job = viewModelScope.launch {
            getRepositoriesUseCase()
                .collectLatest {
                    _state.value = it
                }
        }
    }
}