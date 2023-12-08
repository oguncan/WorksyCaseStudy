package mobi.worksy.casestudy.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mobi.worksy.casestudy.base.BaseFragment
import mobi.worksy.casestudy.databinding.FragmentHomeBinding
import mobi.worksy.casestudy.ui.fragment.home.adapter.BadgeListAdapter
import mobi.worksy.casestudy.ui.fragment.home.viewModel.HomeViewModel
import mobi.worksy.casestudy.util.Resource

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private var badgeListAdapter : BadgeListAdapter? = null
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
            badgesRecyclerView.apply {
                var gridLayoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL,false)
                layoutManager = gridLayoutManager
                badgeListAdapter = BadgeListAdapter(emptyList())
                adapter = badgeListAdapter
            }
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
                    Log.i(TAG, "setObservers: "+resource.message)
                }
                else -> {

                }
            }

        }

        viewModel.praiseList.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is Resource.Success -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    Log.i(TAG, "setObservers: "+resource.message)
                }
                else -> {

                }
            }

        }
    }

}