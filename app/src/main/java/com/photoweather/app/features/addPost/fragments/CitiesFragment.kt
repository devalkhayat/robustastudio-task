package com.photoweather.app.features.addPost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.photoweather.domain.addPost.local.models.City
import com.photoweather.app.databinding.FragmentCitiesBinding
import com.photoweather.app.features.addPost.adapters.CitiesAdapter
import com.photoweather.app.features.addPost.viewmodels.AddPostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CitiesFragment : BottomSheetDialogFragment() {

    private val TAG = "CitiesFragment"
    private var _binding: FragmentCitiesBinding?=null
    private val binding: FragmentCitiesBinding get()=_binding!!
    private  val addPostViewModel by viewModel<AddPostViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter
    private val args: CitiesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        init()
        observers()
        events()
        return binding.root
    }

    private fun events() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun observers() {
        addPostViewModel.citiesResponseLive.observe(viewLifecycleOwner, Observer {
            showData(it)
        })
    }

    private fun showData(items: ArrayList<City>?) {
        citiesAdapter= CitiesAdapter(this,args.onCityClickListener)
        citiesAdapter.setItemsList(items!!)
        binding.apply {
            rvResult.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            rvResult.adapter=citiesAdapter
        }


    }

    private fun init() {
        addPostViewModel.getCities()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

