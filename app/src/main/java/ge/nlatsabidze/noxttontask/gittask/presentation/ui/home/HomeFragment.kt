package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home

import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.noxttontask.databinding.FragmentHomeBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding

@AndroidEntryPoint
class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun start() {
        println("sdadasdasasdasdasasdasdas")
    }
}