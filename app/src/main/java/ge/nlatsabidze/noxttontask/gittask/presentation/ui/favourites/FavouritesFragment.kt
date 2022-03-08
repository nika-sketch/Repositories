package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.noxttontask.databinding.FragmentFavouritesBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.favouritesAdapter.UsersFavouritesAdapter
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.home.repositoryAdapter.UsersRepositoryAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment :
    BaseFragmentBinding<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    private val userAdapter = UsersFavouritesAdapter()
    private val favouritesViewModel: FavouritesViewModel by viewModels()

    override fun start() {
        initRecyclerView()
        deleteItemFromLocalDataBase()
    }

    override fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            favouritesViewModel.state.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest {
                userAdapter.roomRepositories = it
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvFavourite.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            binding.rvFavourite.adapter = userAdapter
        }
    }

    private fun deleteItemFromLocalDataBase() {
        userAdapter.onDeleteClicked = {
            favouritesViewModel.deleteCurrentRepository(it)
        }
    }
}