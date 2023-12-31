package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.PraiseItemModel
import mobi.worksy.casestudy.databinding.LayoutPraiseItemBinding

class PraiseListAdapter : RecyclerView.Adapter<PraiseListAdapter.PraiseListViewHolder>() {

    inner class PraiseListViewHolder(private val binding: LayoutPraiseItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(praise: PraiseItemModel) {
            binding.praiseItem = praise
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PraiseListViewHolder {
        val binding = LayoutPraiseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PraiseListViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: PraiseListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val differCallback = object : DiffUtil.ItemCallback<PraiseItemModel>() {
        override fun areItemsTheSame(oldItem: PraiseItemModel, newItem: PraiseItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PraiseItemModel, newItem: PraiseItemModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun updateData(newPraiseList : List<PraiseItemModel>){
        val oldList = ArrayList(differ.currentList)

        val combinedList = ArrayList<PraiseItemModel>(oldList)
        combinedList.addAll(newPraiseList)

        val diffUtilCallBack = PraiseDiffCallback(oldList, combinedList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)

        differ.submitList(combinedList)
        diffResult.dispatchUpdatesTo(this)
    }

    class PraiseDiffCallback(
        private val oldList: List<PraiseItemModel>,
        private val newList: List<PraiseItemModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}