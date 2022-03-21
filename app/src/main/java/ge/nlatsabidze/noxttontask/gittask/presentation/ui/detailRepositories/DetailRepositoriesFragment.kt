package ge.nlatsabidze.noxttontask.gittask.presentation.ui.detailRepositories

import android.net.Uri
import android.content.Intent
import kotlinx.coroutines.launch
import ge.nlatsabidze.noxttontask.R
import kotlinx.coroutines.flow.collect
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.setImage
import ge.nlatsabidze.noxttontask.databinding.DetailRepositoriesFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.collectFlow
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.dateFormatter
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

@AndroidEntryPoint
class DetailRepositoriesFragment : BaseFragmentBinding<DetailRepositoriesFragmentBinding>(DetailRepositoriesFragmentBinding::inflate) {

    private lateinit var currentMarketItem: Item

    private val args: DetailRepositoriesFragmentArgs by navArgs()
    private val detailRepositoriesViewModel: DetailRepositoriesViewModel by viewModels()

    override fun start() {
        currentMarketItem = args.argItem
        detailRepositoriesViewModel.checkRepository(currentMarketItem)

        setDetailsRepositories()
        openRepositoryLink()
    }

    override fun observes() {
        checkIfRepositoryExists()
    }

    private fun setDetailsRepositories() {
        binding.apply {
            with (currentMarketItem) {
                repoImage.setImage(owner?.ownerAvatarUrl)
                tvCreatedAt.text = createdAt!!.dateFormatter()
                tvDefault.text = defaultBranch
                tvForks.text = forksCount.toString()
                tvFullname.text = fullName
                tvIssues.text = openIssuesCount.toString()
                tvRepoLink.text = htmlUrl
                tvStars.text = stargazersCount.toString()
            }
        }
    }

    private fun openRepositoryLink() {
        binding.tvRepoLink.setOnClickListener {
            openRepoLink(currentMarketItem.htmlUrl.toString())
        }
    }

    private fun openRepoLink(repositoryLink: String) {
        val uri: Uri = Uri.parse(repositoryLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun checkIfRepositoryExists() {
        collectFlow(detailRepositoriesViewModel.checkRepositoryExists) {
            if (it) {
                binding.ivFavouriteRepo.setImageResource(R.drawable.ic_filled)
            }
        }
    }
}