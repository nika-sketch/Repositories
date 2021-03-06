package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home.repositoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.noxttontask.databinding.RepositoryItemBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.findName
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.findRepositoryName
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.setImage
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item
import javax.inject.Inject

class UsersRepositoryAdapter @Inject constructor() : RecyclerView.Adapter<UsersRepositoryAdapter.RepositoryItemViewHolder>() {

    var repositories: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((Item) -> Unit)? = null
    var onFavouriteItemClicked: ((Item) -> Unit)? = null

    inner class RepositoryItemViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentItem: Item

        fun onBind() {
            currentItem = repositories[bindingAdapterPosition]
            binding.tvFullname.text = findName(currentItem.fullName.toString())
            binding.tvRepositoryName.text = findRepositoryName(currentItem.fullName.toString())

            if (currentItem.language.toString() == "null") {
                binding.tvLanguage.text = "not specified"
            } else {
                binding.tvLanguage.text = currentItem.language.toString()
            }

            binding.repoImage.setImage(currentItem.owner?.ownerAvatarUrl)
            binding.tvScore.text = currentItem.watchersCount.toString()
            binding.tvVisibility.text = currentItem.owner?.ownerLogin.toString()

            binding.repoImage.setOnClickListener {
                onItemClick?.invoke(currentItem)
            }

            binding.ivFavorite.setOnClickListener {
                onFavouriteItemClicked?.invoke(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder =
        RepositoryItemViewHolder(
            RepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = repositories.size

}