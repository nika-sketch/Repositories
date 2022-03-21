package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.GetRepositoriesUseCase
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.DeleteRepositoryUseCase

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val deleteRepositoryUseCase: DeleteRepositoryUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<List<Item>>(emptyList())
    val state get() = _state.asStateFlow()

    init {
        getRepositoriesFromRoom()
    }

    private fun getRepositoriesFromRoom() {
        viewModelScope.launch {
            getRepositoriesUseCase().collectLatest {
                _state.value = it
            }
        }
    }

    fun deleteCurrentRepository(repo: Item) {
        viewModelScope.launch {
            deleteRepositoryUseCase(repo)
        }
    }
}