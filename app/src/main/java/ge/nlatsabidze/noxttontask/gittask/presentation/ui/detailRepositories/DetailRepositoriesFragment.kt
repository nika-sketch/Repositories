package ge.nlatsabidze.noxttontask.gittask.presentation.ui.detailRepositories

import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.navArgs
import ge.nlatsabidze.noxttontask.databinding.DetailRepositoriesFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.dateFormatter
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.setImage
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.base.BaseFragmentBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

class DetailRepositoriesFragment : BaseFragmentBinding<DetailRepositoriesFragmentBinding>(DetailRepositoriesFragmentBinding::inflate) {

    private lateinit var currentMarketItem: Item

    private val args: DetailRepositoriesFragmentArgs by navArgs()

    override fun start() {
        currentMarketItem = args.argItem
        setDetailsRepositories()
        openRepositoryLink()
    }

    private fun setDetailsRepositories() {
        binding.apply {
            with (currentMarketItem) {
                repoImage.setImage(owner?.avatar_url)
                tvCreatedAt.text = created_at!!.dateFormatter()
                tvDefault.text = default_branch
                tvForks.text = forks_count.toString()
                tvFullname.text = full_name
                tvIssues.text = open_issues_count.toString()
                tvRepoLink.text = html_url
                tvStars.text = stargazers_count.toString()
            }
        }
    }

    private fun openRepositoryLink() {
        binding.tvRepoLink.setOnClickListener {
            openRepoLink(currentMarketItem.html_url.toString())
        }
    }

    private fun openRepoLink(repositoryLink: String) {
        val uri: Uri = Uri.parse(repositoryLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}