package mobi.worksy.casestudy.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        praiseListAdapter = PraiseListAdapter()
        badgeListAdapter = BadgeSliderAdapter(emptyList())
        binding.apply {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            (activity as AppCompatActivity).supportActionBar?.apply {
                title = null
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_back_arrow)
            }
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

            praiseRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    viewModel.onRecyclerViewScrolled(visibleItemCount, lastVisibleItemPosition, totalItemCount)
                }
            })
        }
    }

    private fun setObservers(){
        viewModel.praiseList.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is BadgeSliderUiState.Success -> {
                    binding.apply {
                        hideShimmerViews()
                        resource.data?.let {
                            //BADGE
                            var badgeList = viewModel.calculateBadgeGroups(it)
                            badgeListAdapter.run {
                                updateData(badgeList.chunked(4))
                            }
                            val (numberOfBadges, averageRating) = viewModel.calculateBadgeTotalAvg(it)
                            badgeAverageText.text = averageRating.formatOneFloatNumber().formatToComma()
                            badgeAverageRatingBar.rating = averageRating.toFloat()
                            badgeTotalText.text = getString(R.string.quantity_string, numberOfBadges)
                        }
                    }
                }
                is BadgeSliderUiState.Loading -> {
                    binding.apply {
                        showShimmerViews()
                    }
                }
                is BadgeSliderUiState.Error -> {
                    binding.apply {
                        hideShimmerViews().also {
                            badgeTopLayout.hide()
                            viewPagerLayout.hide()
                            praiseRecyclerView.hide()
                            errorLayout.root.show()
                            errorLayout.lottieAnimationView.playAnimation()
                            errorLayout.errorRetryButton.setOnClickListener {
                                showShimmerViews()
                                viewModel.retryRequest()
                            }
                        }
                    }
                }
            }
        }
        viewModel.praisePaginatedList.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is PraiseListUiState.Success -> {
                    binding.apply {
                        resource.data?.let {
                            //PRAISE
                            praiseListAdapter.updateData(it)
                        }
                    }
                }
                is PraiseListUiState.Loading -> {

                }
                is PraiseListUiState.Error -> {

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

            errorLayout.root.hide()
            errorLayout.lottieAnimationView.cancelAnimation()
        }
    }

}