package com.sample.githubsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.githubsample.AppExecutors
import com.sample.githubsample.R
import com.sample.githubsample.binding.FragmentDatabindingComponent
import com.sample.githubsample.databinding.TrendingFragmentBinding
import com.sample.githubsample.di.Injectable
import javax.inject.Inject

class TrendingFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val trendingViewModel: TrendingViewModel by viewModels {
        viewModelFactory
    }

    var dataBindingComponent: DataBindingComponent = FragmentDatabindingComponent(this)
    private lateinit var binding: TrendingFragmentBinding
    private lateinit var adapter: TrendingRepoAdapter

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val databinding = DataBindingUtil.inflate<TrendingFragmentBinding>(
            inflater,
            R.layout.trending_fragment,
            container,
            false
        )
        binding = databinding
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setLifecycleOwner(viewLifecycleOwner)
        val adapter = TrendingRepoAdapter(dataBindingComponent, appExecutors)
        this.adapter = adapter
        binding.repoList.adapter = adapter
        initRepoList(trendingViewModel)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_name -> trendingViewModel.sortByName()
            R.id.sort_by_stars -> trendingViewModel.sortByStars()
        }
        return true
    }

    private fun initRepoList(trendingViewModel: TrendingViewModel) {
        trendingViewModel.results.observe(viewLifecycleOwner, Observer { repoList ->
            if (repoList != null) {
                adapter.submitList(repoList?.data)
                adapter.notifyItemRangeChanged(0, adapter.itemCount)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

}