package com.demo.application.ui.details

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.demo.application.R
import com.demo.application.databinding.NewsDetailsFragmentBinding
import com.demo.application.utils.autoCleared

class NewsDetailsFragment : Fragment() {

  private var binding: NewsDetailsFragmentBinding by autoCleared()
  private val viewModel: NewsDetailsViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = NewsDetailsFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val title = arguments?.getString("title")
    val subTitle = arguments?.getString("sub_title")
    val imageUrl = arguments?.getString("image_url")
    binding.title.text = title
    binding.subTitle.text = subTitle


    Glide.with(binding.root)
      .load(imageUrl)
      .listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
          return true
        }
        override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
          return false
        }
      })
      .into(binding.image)

  //  setupObservers()
  }

}
