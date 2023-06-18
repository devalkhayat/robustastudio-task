package com.photoweather.app.features.addPost.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.app.databinding.FragmentCardBinding
import com.photoweather.app.features.addPost.viewmodels.AddPostViewModel
import com.photoweather.app.util.Contstants.BASE_FOLDER_NAME
import com.photoweather.app.util.helper
import com.photoweather.app.util.models.ImageData
import org.koin.androidx.viewmodel.ext.android.viewModel


class CardFragment : Fragment() {

    private  val TAG = "CardFragment"
    private var _binding: FragmentCardBinding?=null
    private val binding: FragmentCardBinding get()=_binding!!
    private  val addPostViewModel by viewModel<AddPostViewModel>()
    private val args: CardFragmentArgs by navArgs()
    private val imageData:ImageData= ImageData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCardBinding.inflate(inflater,container,false)
        init()
        observers()
        events()
        return binding.root
    }
    private fun init() {

        binding.apply {
            args.cardInfo.let {
                tvDegree.text = it.degree
                tvDescription.text = it.description
                tvCity.text = it.city
                tvPlaceName.text = it.placeName
                img.setImageBitmap(helper.loadImageFromLocal(it.imageLocation))
            }


        }

    }
    private fun events() {

        binding.apply {

            btnSave.setOnClickListener {
                val converted= helper.viewToImage(binding.cardContainer)!!
                imageData.largeConvertedBitMap=converted
                imageData.largeImageLocation=saveImageOnDevice(bitmap = converted,isThumb = false)
                //
                imageData.smallConvertedBitMap=converted
                imageData.smallImageLocation=saveImageOnDevice(bitmap = converted,isThumb = true)
                //
                savePostOnDB()
            }
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }
    private fun observers() {
       addPostViewModel.addPostResponseLive.observe(viewLifecycleOwner, Observer {
           when(it) {
               true -> helper.openShareDialog(requireContext(),imageData.largeImageLocation!!)
               false -> Log.d(TAG, "observers: error")
           }
       })
    }

    private fun saveImageOnDevice(bitmap:Bitmap,isThumb:Boolean):String{
        var file=helper.prepareImage(requireContext(),bitmap,BASE_FOLDER_NAME,isThumb)
        return file?.absolutePath.toString()!!
    }
    private fun savePostOnDB(){

        args.cardInfo.let {
            addPostViewModel.save(
                PostsEntity(
                    placeName = it.placeName,
                    degree = it.degree,
                    city = it.city,
                    description = it.description,
                    largeImageLocation = imageData.largeImageLocation!!,
                    smallImageLocation =imageData.smallImageLocation!!
                )
            )
        }



    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}