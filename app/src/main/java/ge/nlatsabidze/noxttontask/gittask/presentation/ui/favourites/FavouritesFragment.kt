package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager
import ge.nlatsabidze.noxttontask.databinding.FragmentFavouritesBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.collectFlow
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.showDialogError
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.favouritesAdapter.UsersFavouritesAdapter

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
        collectRoomRepositories()
    }

    private fun initRecyclerView() {
        binding.rvFavourite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            binding.rvFavourite.adapter = userAdapter
        }
    }

    private fun deleteItemFromLocalDataBase() {
        userAdapter.onDeleteClicked = {
            favouritesViewModel.deleteCurrentRepository(it)
            showDialogError("Repository removed from favourites", requireContext())
        }
    }

    private fun collectRoomRepositories() {
        collectFlow(favouritesViewModel.state) {
            userAdapter.roomRepositories = it
        }
    }
}