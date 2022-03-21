package ge.nlatsabidze.noxttontask.gittask.presentation.ui.detailRepositories

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.use_cases.GetRepositoryByIdUseCase

@HiltViewModel
class DetailRepositoriesViewModel @Inject constructor(
    private val getRepositoryByIdUseCase: GetRepositoryByIdUseCase
) : ViewModel() {

    private val _checkRepositoryExists = MutableStateFlow(false)
    val checkRepositoryExists get() = _checkRepositoryExists.asStateFlow()

    fun checkRepository(repo: Item) {
        viewModelScope.launch {
            _checkRepositoryExists.value = getRepositoryByIdUseCase(repo.id!!) != null
        }
    }
}