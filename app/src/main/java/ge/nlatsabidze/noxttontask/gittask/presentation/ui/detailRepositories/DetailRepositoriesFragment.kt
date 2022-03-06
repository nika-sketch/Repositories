package ge.nlatsabidze.noxttontask.gittask.presentation.ui.detailRepositories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ge.nlatsabidze.noxttontask.R

class DetailRepositoriesFragment : Fragment() {

    companion object {
        fun newInstance() = DetailRepositoriesFragment()
    }

    private lateinit var viewModel: DetailRepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_repositories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailRepositoriesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}