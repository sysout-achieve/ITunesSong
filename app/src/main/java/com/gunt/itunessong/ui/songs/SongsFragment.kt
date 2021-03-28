package com.gunt.itunessong.ui.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunt.itunessong.BR
import com.gunt.itunessong.R
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.databinding.FragmentSongsBinding
import com.gunt.itunessong.ui.MainViewModel.Companion.checkFavorite
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
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_title_songs)

        setupListAdapter()
        setupPagedListObserver()
        setupSwipeRefreshListener()
        setupUpdatedIdObserver()

        return binding.root
    }

    private fun onClickStar(track: Track, position: Int) {
        if (!checkFavorite(track)) viewModel.insertFavorite(track, position)
        else viewModel.deleteFavorite(track, position)
    }

    private fun setupListAdapter() {
        binding.recyclerTrack.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerTrack.adapter = TrackListAdapter { track, position ->
            onClickStar(track!!, position)
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

    private fun setupUpdatedIdObserver() {
        viewModel.updatedPosition.observe(
            this.viewLifecycleOwner,
            {
                (binding.recyclerTrack.adapter as TrackListAdapter).notifyItemChanged(it, Unit)
            }
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
