package ge.nlatsabidze.noxttontask.gittask.presentation.ui.home.repositoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.noxttontask.databinding.RepositoryItemBinding
import ge.nlatsabidze.noxttontask.gittask.presentation.ui.model.data.repositories.Item

class UsersRepositoryAdapter : RecyclerView.Adapter<UsersRepositoryAdapter.RepositoryItemViewHolder>() {

    var cryptoExchanges: List<Item> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((Item) -> Unit)? = null

    inner class RepositoryItemViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentItem: Item

        fun onBind() {
            currentItem = cryptoExchanges[bindingAdapterPosition]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder =
        RepositoryItemViewHolder(RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = cryptoExchanges.size

}