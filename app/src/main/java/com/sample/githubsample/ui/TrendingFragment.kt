package com.sample.githubsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sample.githubsample.AppExecutors
import com.sample.githubsample.R
import com.sample.githubsample.binding.FragmentDatabindingComponent
import com.sample.githubsample.databinding.TrendingFragmentBinding
import javax.inject.Inject

class TrendingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val trendingViewModel: TrendingViewModel by lazy {
        viewModelFactory.create(TrendingViewModel::class.java)
    }

    var dataBindingComponent: DataBindingComponent = FragmentDatabindingComponent(this)
    private lateinit var binding:TrendingFragmentBinding
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
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.repo = trendingViewModel.results
        val adapter = TrendingRepoAdapter(dataBindingComponent, appExecutors)
        this.adapter = adapter
        binding.repoList.adapter = adapter
    }
}