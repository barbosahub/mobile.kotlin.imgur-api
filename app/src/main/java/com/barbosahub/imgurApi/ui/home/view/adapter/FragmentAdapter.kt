package com.barbosahub.imgurApi.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barbosahub.imgurApi.databinding.ItemCatsBinding
import com.barbosahub.imgurApi.ui.home.view.model.CatsJson
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest

class GridAdapter(private var listCats: List<CatsJson>):
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemCatsBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            return ViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            //removing .mp4
            if (!listCats[position].link.contains("mp4")){
                animimage.controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(listCats[position].link))
                    .setAutoPlayAnimations(true)
                    .build()
            }
        }
    }

    override fun getItemCount(): Int = listCats.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    class ViewHolder(val binding: ItemCatsBinding) : RecyclerView.ViewHolder(binding.root)
}