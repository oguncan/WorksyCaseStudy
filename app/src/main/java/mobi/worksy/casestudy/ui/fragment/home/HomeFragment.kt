package mobi.worksy.casestudy.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import mobi.worksy.casestudy.R
import mobi.worksy.casestudy.base.BaseFragment
import mobi.worksy.casestudy.databinding.FragmentHomeBinding
import mobi.worksy.casestudy.ui.fragment.home.adapter.BadgeSliderAdapter
import mobi.worksy.casestudy.ui.fragment.home.adapter.PraiseListAdapter
import mobi.worksy.casestudy.ui.fragment.home.viewModel.HomeViewModel
import mobi.worksy.casestudy.util.Resource
import mobi.worksy.casestudy.util.formatOneFloatNumber
import mobi.worksy.casestudy.util.formatToComma
import mobi.worksy.casestudy.util.hide
import mobi.worksy.casestudy.util.show

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var badgeListAdapter : BadgeSliderAdapter
    private lateinit var praiseListAdapter: PraiseListAdapter
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
        praiseListAdapter = PraiseListAdapter(emptyList())
        badgeListAdapter = BadgeSliderAdapter(emptyList())
        binding.apply {
            badgesViewPager.apply {
                adapter = badgeListAdapter
            }
            praiseRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = praiseListAdapter
                setHasFixedSize(true)
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
                            //BADGE
                            var badgeList = viewModel.calculateBadgeGroups(it.praiseItem)
                            badgeListAdapter.run {
                                updateData(badgeList.chunked(4))
                            }
                            val (totalRating, averageRating) = viewModel.calculateBadgeTotalAvg(it.praiseItem)
                            badgeAverageText.text = averageRating.formatOneFloatNumber().formatToComma()
                            badgeAverageRatingBar.rating = averageRating.toFloat()
                            badgeTotalText.text = getString(R.string.quantity_string, totalRating.toInt())

                            //PRAISE
                            praiseListAdapter.updateData(it.praiseItem.subList(0, 10))
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
            }

        }
    }

    private fun hideShimmerViews() {
        with(binding) {
            praiseShimmerItem.hide()
            praiseShimmerItem.stopShimmer()
            badgeTotalShimmer.hide()
            badgeTotalShimmer.stopShimmer()
            badgeSingleItemShimmer.hide()
            badgeSingleItemShimmer.stopShimmer()
            badgeFlagLoading.hide()

            badgeTopLayout.show()
            viewPagerLayout.show()
            praiseRecyclerView.show()
        }
    }

    private fun showShimmerViews() {
        with(binding) {
            badgeTopLayout.hide()
            viewPagerLayout.hide()
            praiseRecyclerView.hide()

            badgeFlagLoading.show()
            praiseShimmerItem.show()
            praiseShimmerItem.startShimmer()
            badgeTotalShimmer.show()
            badgeTotalShimmer.startShimmer()
            badgeSingleItemShimmer.show()
            badgeSingleItemShimmer.startShimmer()
        }
    }

}