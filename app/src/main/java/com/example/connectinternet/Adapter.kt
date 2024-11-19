import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectinternet.databinding.ItemBookBinding
import com.example.connectinternet.databinding.ItemHouseBinding
import com.example.connectinternet.databinding.ItemHeaderBinding
import com.example.connectinternet.json.Book
import com.example.connectinternet.json.House

class Adapter : ListAdapter<Any, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Book -> VIEW_TYPE_BOOK
            is House -> VIEW_TYPE_HOUSE
            is String -> VIEW_TYPE_HEADER
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_BOOK -> {
                val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BookViewHolder(binding)
            }
            VIEW_TYPE_HOUSE -> {
                val binding = ItemHouseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HouseViewHolder(binding)
            }
            VIEW_TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookViewHolder -> holder.bind(getItem(position) as Book)
            is HouseViewHolder -> holder.bind(getItem(position) as House)
            is HeaderViewHolder -> holder.bind(getItem(position) as String)
        }
    }

    override fun getItemCount(): Int = currentList.size

    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()

            Glide.with(binding.coverImageView.context)
                .load(book.cover)
                .into(binding.coverImageView)
        }
    }

    inner class HouseViewHolder(private val binding: ItemHouseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(house: House) {
            binding.house = house
            binding.executePendingBindings()
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(header: String) {
            binding.headerText.text = header
        }
    }

    companion object {
        private const val VIEW_TYPE_BOOK = 0
        private const val VIEW_TYPE_HOUSE = 1
        private const val VIEW_TYPE_HEADER = 2
    }

    class DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}
