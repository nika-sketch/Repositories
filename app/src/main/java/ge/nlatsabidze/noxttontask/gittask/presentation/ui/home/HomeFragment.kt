package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import android.util.Log.d
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import ge.nlatsabidze.noxttontask.databinding.FragmentHomeBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.home.repositoryAdapter.UsersRepositoryAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val userAdapter = UsersRepositoryAdapter()
    private val homeViewModel: HomeViewModel by viewModels()

    companion object {
        const val currentRepository = "NoxttonTaskRepository"
    }

    override fun start() {
        setUpRecycler()

        homeViewModel.searchCase(currentRepository)
        binding.search.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                homeViewModel.searchCase(it.toString())
            } else {
                homeViewModel.searchCase(currentRepository)
            }
        }

        userAdapter.onItemClick = {
            binding.search.text?.clear()
            val action =
                HomeFragmentDirections.actionNavigationHomeToDetailRepositoriesFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun setUpRecycler() {
        binding.rvItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            binding.rvItems.adapter = userAdapter
        }
    }

    override fun observes() {
        if (userAdapter.repositories.size > 0) {
            binding.pbLoading.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.state.collect {
                if (it.isNotEmpty()) {
                    binding.pbLoading.visibility = View.GONE
                }
                userAdapter.repositories = it
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.loading.collect {
                if (it) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.INVISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.errorMessage.collect {
                if (it) {
                    binding.pbLoading.visibility = View.GONE
                    userAdapter.repositories.clear()
                    userAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}