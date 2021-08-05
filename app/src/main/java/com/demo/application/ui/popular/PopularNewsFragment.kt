package com.demo.application.ui.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.application.R
import com.demo.application.data.models.PopularNews
import com.demo.application.data.models.Result
import com.demo.application.databinding.FragmentPopularNewsBinding
import com.demo.application.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularNewsFragment : Fragment(),PopularNewsAdapter.NewsItemListener {

  private var binding: FragmentPopularNewsBinding by autoCleared()
  private val popularViewModel: PopularViewModel by viewModels()
  private lateinit var adapter: PopularNewsAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentPopularNewsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.progressBar.visibility = View.VISIBLE
    setupRecyclerView()
    setupObservers()
  }

  private fun setupRecyclerView() {
    adapter = PopularNewsAdapter(this)
    binding.newsRv.layoutManager = LinearLayoutManager(requireContext())
    binding.newsRv.adapter = adapter
  }

  private fun setupObservers() {
    popularViewModel.newsData.observe(viewLifecycleOwner, Observer {
      it?.results?.let { it1 -> adapter.setItems(it1)
        binding.progressBar.visibility = View.GONE
      }
    })

  }

  override fun onClickedItem(popularNews: Result, isFavorite: Boolean, isRoot: Boolean) {

         findNavController().navigate(
        R.id.action_home_to_details,
           bundleOf(
             "title" to popularNews.title,
             "sub_title" to popularNews.subsection,
             "details_url" to popularNews.url,
             "image_url" to popularNews.media.get(0).mediametadata.get(2).url
           )
         )
    }

}
