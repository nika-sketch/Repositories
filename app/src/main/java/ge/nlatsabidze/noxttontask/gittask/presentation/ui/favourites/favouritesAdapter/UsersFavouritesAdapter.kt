package ge.nlatsabidze.noxttontask.gittask.presentation.ui.favourites.favouritesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.noxttontask.databinding.FavouritesItemBinding
import ge.nlatsabidze.noxttontask.databinding.RepositoryItemBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.findName
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.findRepositoryName
import ge.nlatsabidze.noxttontask.gittask.presentation.extensions.setImage
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

class UsersFavouritesAdapter : RecyclerView.Adapter<UsersFavouritesAdapter.RepositoryItemViewHolder>() {

    var roomRepositories: List<Item> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onDeleteClicked: ((Item) -> Unit)? = null

    inner class RepositoryItemViewHolder(private val binding: FavouritesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentItem: Item

        fun onBind() {
            currentItem = roomRepositories[bindingAdapterPosition]

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

            binding.ivFavorite.setOnClickListener {
                onDeleteClicked?.invoke(currentItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder =
        RepositoryItemViewHolder(FavouritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = roomRepositories.size

}