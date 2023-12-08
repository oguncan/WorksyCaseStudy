package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.databinding.LayoutSingleBadgeItemBinding

class BadgeListAdapter(private val badgeList: List<BadgeModel>) : RecyclerView.Adapter<BadgeListAdapter.BadgeListViewHolder>() {

    inner class BadgeListViewHolder(private val binding: LayoutSingleBadgeItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(playingMovie: BadgeModel) {

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
}