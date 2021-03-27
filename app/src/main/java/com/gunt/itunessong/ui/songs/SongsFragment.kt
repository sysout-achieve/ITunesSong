package com.gunt.itunessong.ui.songs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunt.itunessong.BR
import com.gunt.itunessong.R
import com.gunt.itunessong.data.domain.Song
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.databinding.FragmentSongsBinding
import com.gunt.itunessong.ui.bind.TrackListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsFragment : Fragment() {

    private lateinit var binding: FragmentSongsBinding
    private val viewModel: SongsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_songs, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.songsViewModel, viewModel)
        binding.executePendingBindings()

        setupListAdapter()
        setupLoadingObserver()
        setupPagedListObserver()
        setupSwipeRefreshListener()

        return binding.root
    }


    private fun onClickStar(song: Track?) {

    }

    private fun setupListAdapter() {
        binding.recyclerTrack.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerTrack.adapter = TrackListAdapter {
            onClickStar(it)
        }
    }

    private fun setupPagedListObserver() {
        viewModel.trackList.observe(
                this.viewLifecycleOwner,
                {
                    (binding.recyclerTrack.adapter as TrackListAdapter).submitList(it)
                }
        )
    }

    private fun setupLoadingObserver() {
        viewModel.loading.observe(
                this.viewLifecycleOwner,
                { binding.layoutRefresh.isRefreshing = it }
        )
    }

    private fun setupSwipeRefreshListener() {
        binding.layoutRefresh.setOnRefreshListener {
            swipeRefresh()
        }
    }

    private fun swipeRefresh() {
        (binding.recyclerTrack.adapter as TrackListAdapter).currentList?.dataSource?.invalidate()
    }


}