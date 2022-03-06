package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import android.util.Log.d
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.noxttontask.databinding.FragmentHomeBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    override fun start() {
        println("sdadasdasasdasdasasdasdas")
        homeViewModel.searchCase("github")

        binding.search.doAfterTextChanged {
            homeViewModel.searchCase(it.toString())
        }
    }

    override fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.state.collect {
                d("Sdasdas", it.toString())
            }
        }
    }
}