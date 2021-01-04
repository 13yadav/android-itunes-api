package com.strangecoder.itunes_api.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.strangecoder.itunes_api.databinding.GridListItemBinding
import com.strangecoder.itunes_api.domain.SongItem

class SearchListAdapter :
    ListAdapter<SongItem, SearchListAdapter.ViewHolder>(ResultItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GridListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songItem = getItem(position)
        holder.bind(songItem)
    }

    /**
     * ViewHolder for holding views
     * **/
    class ViewHolder(private val binding: GridListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(songItem: SongItem) {
            binding.songTitle.text = songItem.trackName
            binding.artistName.text = songItem.artistName
            Glide.with(itemView.context)
                .load(songItem.artworkUrl100)
                .into(binding.coverImg)

            binding.executePendingBindings()
        }
    }
}

class ResultItemDiffCallback : DiffUtil.ItemCallback<SongItem>() {
    override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean {
        return oldItem.trackName == newItem.trackName
                && oldItem.trackId == newItem.trackId
                && oldItem.artistId == newItem.artistId
    }

    override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean {
        return oldItem == newItem
    }
}


