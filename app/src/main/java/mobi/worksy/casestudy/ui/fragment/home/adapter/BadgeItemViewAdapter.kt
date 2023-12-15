package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.databinding.LayoutSingleBadgeItemBinding

class BadgeItemViewAdapter(private var badge: List<BadgeGroupModel>) : RecyclerView.Adapter<BadgeItemViewAdapter.BadgeItemViewHolder>() {

    inner class BadgeItemViewHolder(private val binding: LayoutSingleBadgeItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(badge: BadgeGroupModel) {
            binding.badgeItem = badge
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeItemViewHolder {
        val binding = LayoutSingleBadgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = badge.size

    override fun onBindViewHolder(holder: BadgeItemViewHolder, position: Int) {
        holder.bind(badge[position])
    }

    fun updateData(newData: List<BadgeGroupModel>) {
        badge = newData
        notifyDataSetChanged()
    }
}