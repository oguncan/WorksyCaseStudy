package mobi.worksy.casestudy.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import mobi.worksy.casestudy.R
import mobi.worksy.casestudy.base.BaseFragment
import mobi.worksy.casestudy.databinding.FragmentHomeBinding
import mobi.worksy.casestudy.ui.fragment.home.adapter.BadgeSliderAdapter
import mobi.worksy.casestudy.ui.fragment.home.viewModel.HomeViewModel
import mobi.worksy.casestudy.util.Resource
import mobi.worksy.casestudy.util.formatToComma

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private var badgeListAdapter : BadgeSliderAdapter? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        setObservers()
    }

    private fun setViews(){
        binding.apply {
            badgesViewPager.apply {
                badgeListAdapter = BadgeSliderAdapter(emptyList())
                adapter = badgeListAdapter
            }
            TabLayoutMediator(tabLayout, badgesViewPager) { tab, position ->

            }.attach()
            praiseShimmerItem.startShimmer()
            badgeTotalShimmer.startShimmer()

        }
    }

    private fun setObservers(){
        viewModel.badgeList.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is Resource.Success -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
                else -> {

                }
            }

        }

        viewModel.praiseList.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is Resource.Success -> {
                    binding.apply {
                        hideShimmerViews()
                        resource.data?.let {
                            var badgeList = viewModel.calculateBadgeGroups(it.Row)
                            badgeListAdapter?.run {
                                updateData(badgeList.chunked(4))
                            }
                            val (totalRating, averageRating) = viewModel.calculateBadgeTotalAvg(it.Row)
                            badgeAverageText.text = formatToComma(String.format("%.1f", averageRating))
                            badgeAverageRatingBar.rating = averageRating.toFloat()
                            badgeTotalText.text = getString(R.string.quantity_string, totalRating.toInt())
                        }
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        showShimmerViews()
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        hideShimmerViews()
                    }
                }
                else -> {

                }
            }

        }
    }

    private fun hideShimmerViews() {
        with(binding) {
            praiseShimmerItem.visibility = View.GONE
            praiseShimmerItem.stopShimmer()
            badgeTotalShimmer.visibility = View.GONE
            badgeTotalShimmer.stopShimmer()
            badgeSingleItemShimmer.visibility = View.GONE
            badgeSingleItemShimmer.stopShimmer()
            badgeFlagLoading.visibility = View.GONE

            badgeTopLayout.visibility = View.VISIBLE
            viewPagerLayout.visibility = View.VISIBLE
        }
    }

    private fun showShimmerViews() {
        with(binding) {
            badgeTopLayout.visibility = View.GONE
            viewPagerLayout.visibility = View.GONE

            badgeFlagLoading.visibility = View.VISIBLE
            praiseShimmerItem.visibility = View.VISIBLE
            praiseShimmerItem.startShimmer()
            badgeTotalShimmer.visibility = View.VISIBLE
            badgeTotalShimmer.startShimmer()
            badgeSingleItemShimmer.visibility = View.VISIBLE
            badgeSingleItemShimmer.startShimmer()
        }
    }

}