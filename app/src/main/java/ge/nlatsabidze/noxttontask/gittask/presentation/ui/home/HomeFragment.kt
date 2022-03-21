package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import android.graphics.Color
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import ge.nlatsabidze.noxttontask.connection.CheckLiveConnection
import ge.nlatsabidze.noxttontask.connection.CheckStableConnection
import ge.nlatsabidze.noxttontask.databinding.FragmentHomeBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.collectFlow
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.onSnack
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.showDialogError
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.home.repositoryAdapter.UsersRepositoryAdapter
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import ge.nlatsabidze.noxttontask.gittask.presentation.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var userAdapter: UsersRepositoryAdapter

    @Inject
    lateinit var checkConnection: CheckLiveConnection

    @Inject
    lateinit var checkStableConnections: CheckStableConnection


    override fun start() {
        checkLiveConnectionOnStart()
        setUpRecycler()

        binding.search.doAfterTextChanged {
            if (checkStableConnections.isOnline(requireContext())) {
                if (it.toString().isNotEmpty()) {
                    homeViewModel.searchCase(it.toString(), 100)
                }
            } else {
                onSnack(binding.root, "No Internet Connection", Color.RED)
            }
        }

        userAdapter.onItemClick = {
            binding.search.text?.clear()
            val action = HomeFragmentDirections.actionNavigationHomeToDetailRepositoriesFragment(it)
            findNavController().navigate(action)
        }

        userAdapter.onFavouriteItemClicked = {
            homeViewModel.insertRepository(it)
            showDialogError("Repository Saved to Favourites", requireContext())
        }
    }

    private fun setUpRecycler() {
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            binding.rvItems.adapter = userAdapter
        }
    }

    private fun checkLiveConnectionOnStart() {
        viewLifecycleOwner.lifecycleScope.launch {
            checkConnection.asFlow().collect {
                if (!it) {
                    onSnack(binding.root, "No Internet Connection!", Color.RED)
                }
            }
        }
    }

    override fun observes() {
        if (userAdapter.repositories.size > 0) {
            binding.pbLoading.visibility = View.GONE
        }


        collectFlow(homeViewModel.stateResult) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    userAdapter.repositories = mutableListOf()
                }

                is Resource.Success -> {
                    binding.pbLoading.visibility = View.INVISIBLE
                    userAdapter.repositories = it.data?.items as MutableList<Item>
                }

                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    userAdapter.repositories.clear()
                    userAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}