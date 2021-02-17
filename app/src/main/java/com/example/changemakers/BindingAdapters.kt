package com.example.changemakers

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.recyclerview.widget.RecyclerView
import com.example.changemakers.network.CmProperty
import com.example.changemakers.overview.PhotoGridAdapter
import com.example.changemakers.overview.CmApiStatus
import android.view.View

@BindingAdapter("imageLinkUrl")
fun bindImage(imgView: ImageView, imgLinkUrl: String?) {
    imgLinkUrl?.let {
        val imgLinkUri =
            imgLinkUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgLinkUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<CmProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}

@BindingAdapter("CmApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: CmApiStatus?) {
    when (status) {
        CmApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        CmApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        CmApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}