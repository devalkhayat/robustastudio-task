package com.photoweather.app.features.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.photoweather.app.databinding.FragmentPreviewBinding
import com.photoweather.app.util.helper


class PreviewFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPreviewBinding?=null
    private val binding: FragmentPreviewBinding get()=_binding!!
    private val args:PreviewFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPreviewBinding.inflate(inflater, container, false)
        init()
        events()
        return binding.root
    }


    private fun init(){
        binding.apply {
            args.post.let {
                helper.loadImage(requireContext(),it.largeImageLocation,img)
            }
        }
    }
    private fun events(){
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}