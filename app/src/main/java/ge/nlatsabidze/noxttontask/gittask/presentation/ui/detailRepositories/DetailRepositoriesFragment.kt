package ge.nlatsabidze.noxttontask.gittask.presentation.ui.detailRepositories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ge.nlatsabidze.noxttontask.R
import ge.nlatsabidze.noxttontask.databinding.DetailRepositoriesFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

class DetailRepositoriesFragment : BaseFragmentBinding<DetailRepositoriesFragmentBinding>(DetailRepositoriesFragmentBinding::inflate) {

    private lateinit var currentMarketItem: Item

    private val args: DetailRepositoriesFragmentArgs by navArgs()

    override fun start() {
        currentMarketItem = args.argItem
        d("sdadasdasdasda", currentMarketItem.toString())
    }

}