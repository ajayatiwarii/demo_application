package com.demo.application.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.graphics.drawable.Drawable
import android.util.Log

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.demo.application.data.models.PopularNews
import com.demo.application.data.models.Result
import com.demo.application.databinding.ItemPopularNewsBinding
import com.bumptech.glide.request.target.Target


class PopularNewsAdapter(private val listener: NewsItemListener) : RecyclerView.Adapter<PopularNewsAdapter.ViewHolder>() {


  interface NewsItemListener {
    fun onClickedItem(news: Result,isFavorite:Boolean,isRoot:Boolean)
  }
  private val items = ArrayList<Result>()

  fun setItems(items: List<Result>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding: ItemPopularNewsBinding = ItemPopularNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding,listener)
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items.get(position))


  class ViewHolder(private val itemBinding: ItemPopularNewsBinding,private val listener: PopularNewsAdapter.NewsItemListener) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

    private lateinit var popularNews: Result

    init {
      itemBinding.root.setOnClickListener(this)
    }


    fun bind(item: Result) {
      this.popularNews = item
      itemBinding.title.text = item.title
      itemBinding.author.text= item.source.toUpperCase()
      itemBinding.date.text= item.published_date
      itemBinding.progressBar.visibility = View.VISIBLE

      Glide.with(itemBinding.root)
         .load(item.media.get(0).mediametadata.get(0).url)
        .circleCrop()
         .listener(object : RequestListener<Drawable> {
           override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
             itemBinding.progressBar.visibility = View.GONE
             return true
           }
           override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
             itemBinding.progressBar.visibility = View.GONE
             return false
           }
         })
         .into(itemBinding.image)
    }

    override fun onClick(v: View?) {

      listener.onClickedItem(popularNews,false,true)

    }

  }
}
