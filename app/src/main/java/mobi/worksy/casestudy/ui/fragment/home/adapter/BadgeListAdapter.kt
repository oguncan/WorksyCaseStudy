package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.databinding.LayoutSingleBadgeItemBinding

class BadgeListAdapter(private var badgeList: List<BadgeGroupModel>) : RecyclerView.Adapter<BadgeListAdapter.BadgeListViewHolder>() {

    inner class BadgeListViewHolder(private val binding: LayoutSingleBadgeItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(playingMovie: BadgeGroupModel) {
            binding.badgeItem = playingMovie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeListViewHolder {
        val binding = LayoutSingleBadgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeListViewHolder(binding)
    }

    override fun getItemCount(): Int = badgeList.size

    override fun onBindViewHolder(holder: BadgeListViewHolder, position: Int) {
        holder.bind(badgeList[position])
    }

    fun updateData(newData: List<BadgeGroupModel>) {
        badgeList = newData
        notifyDataSetChanged()
    }
}