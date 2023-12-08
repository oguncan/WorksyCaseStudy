package mobi.worksy.casestudy.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.databinding.LayoutPraiseItemBinding

class PraiseListAdapter(private val praiseList: List<PraiseModel>) : RecyclerView.Adapter<PraiseListAdapter.BadgeListViewHolder>() {

    inner class BadgeListViewHolder(private val binding: LayoutPraiseItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(playingMovie: PraiseModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeListViewHolder {
        val binding = LayoutPraiseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeListViewHolder(binding)
    }

    override fun getItemCount(): Int = praiseList.size

    override fun onBindViewHolder(holder: BadgeListViewHolder, position: Int) {
        holder.bind(praiseList[position])
    }
}