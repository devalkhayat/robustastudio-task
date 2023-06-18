package com.photoweather.app.features.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.app.R
import com.photoweather.app.databinding.FragmentHomeBinding
import com.photoweather.app.features.home.adapters.PostsAdapter
import com.photoweather.app.features.home.viewmodels.HomeViewModel
import com.photoweather.app.util.eventListners.RecycleViewEventListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding: FragmentHomeBinding get()=_binding!!
    private  val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var onPostClickListener: RecycleViewEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        events()
        init()
        return binding.root
    }



    private fun init() {
        postsAdapter = PostsAdapter(onPostClickListener)
        binding.rvResults.adapter = postsAdapter
        binding.rvResults.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        //
        lifecycleScope.launch {
            homeViewModel.getPosts().collectLatest {
                postsAdapter.submitData(it)
            }
        }
    }

    private fun events(){
        binding.apply {
            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_formFragment)
            }
        }
        onPostClickListener=object:RecycleViewEventListener{
            override fun onClick(post: PostsEntity) {
               val action=HomeFragmentDirections.actionHomeFragmentToPreviewFragment(post)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}