package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.databinding.LayoutBadgeRecyclerviewItemBinding

class BadgeSliderAdapter(private var badgeList: List<List<BadgeGroupModel>>) : RecyclerView.Adapter<BadgeSliderAdapter.BadgeListViewHolder>() {

    inner class BadgeListViewHolder(private val binding: LayoutBadgeRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(badge: List<BadgeGroupModel>) {
            binding.badgeRecyclerView.apply {
                layoutManager = GridLayoutManager(binding.root.context, 2)
                val recyclerViewAdapter = BadgeItemViewAdapter(badge)
                adapter = recyclerViewAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeListViewHolder {
        val binding = LayoutBadgeRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeListViewHolder(binding)
    }

    override fun getItemCount(): Int = badgeList.size

    override fun onBindViewHolder(holder: BadgeListViewHolder, position: Int) {
        holder.bind(badgeList[position])
    }

    fun updateData(newData: List<List<BadgeGroupModel>>) {
        badgeList = newData
        notifyDataSetChanged()
    }
}