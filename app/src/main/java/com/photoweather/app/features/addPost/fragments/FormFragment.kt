package com.photoweather.app.features.addPost.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.photoweather.domain.addPost.remote.models.SnapShotDetails
import com.photoweather.domain.addPost.remote.responses.CityDirectionResponse
import com.photoweather.domain.addPost.remote.responses.WeatherResponse
import com.photoweather.app.databinding.FragmentFormBinding
import com.photoweather.app.features.addPost.viewmodels.AddPostViewModel
import com.photoweather.app.util.Contstants.FILE_PROVIDER
import com.photoweather.app.util.eventListners.RecycleViewEventListener
import com.photoweather.app.util.helper
import com.photoweather.app.util.models.Card
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class FormFragment : Fragment() {

    private  val TAG = "FormFragment"
    private var _binding: FragmentFormBinding?=null
    private val binding: FragmentFormBinding get()=_binding!!
    private  val addPostViewModel by viewModel<AddPostViewModel>()
    private lateinit var onCityClickListener: RecycleViewEventListener
    private lateinit var selectedCity:String
    private val _requestAccessCode:Int=200
    private val _requestCaptureCode:Int=2002
    private val _authority:String=FILE_PROVIDER
    private var _selectedFile:File?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentFormBinding.inflate(inflater,container,false)
        init()
        events()
        observers()
        return binding.root
    }

    private fun init(){
        addPostViewModel.getCities()
    }
    private fun observers() {
        addPostViewModel.citiesResponseLive.observe(viewLifecycleOwner, Observer {
            selectedCity= it[0].name
           addPostViewModel.getCityLocation(selectedCity)
       })
        addPostViewModel.cityLocationResponseLive.observe(viewLifecycleOwner, Observer { 
            if(it.status==true){
                var rs=it.data as ArrayList<CityDirectionResponse>
                addPostViewModel.getWeather(rs[0].lat!!,rs[0].lon!!)
            }else{
                Log.d(TAG, "observers: Error${it.message}")
            }
        })
        addPostViewModel.weatherResponseLive.observe(viewLifecycleOwner, Observer {
            if(it.status==true){
                var rs=it.data as WeatherResponse
                showData(rs.list.get(0))
            }else{
                Log.d(TAG, "observers: Error${it.message}")
            }
        })
    }
    private fun showData(rs: SnapShotDetails) {
        binding.apply {
            tvDegree.text=rs.main?.temp.toString()
            tvDescription.text= rs.weather[0].main
            tvCity.text=selectedCity
        }
    }
    private fun events(){
        binding.apply {
            tvCity.setOnClickListener {
               val action=FormFragmentDirections.actionFormFragmentToCitiesFragment(onCityClickListener)
                findNavController().navigate(action)
            }
            btnOpenCamera.setOnClickListener {
                callTaskPhoto()
            }
            img.setOnClickListener {
                callTaskPhoto()
            }
            btnShow.setOnClickListener {
                showCard()
            }
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        onCityClickListener=object:RecycleViewEventListener{
            override fun onClick(name: String) {
                selectedCity=name
                binding.apply{
                    tvCity.text=name
                    tvDegree.text="0"
                    tvDescription.text=""
                }

                addPostViewModel.getCityLocation(selectedCity)
            }
        }
    }

    private fun callTaskPhoto() {
        if ( ContextCompat.checkSelfPermission( requireContext(), android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED ) {
            val permissions= arrayOf(Manifest.permission.CAMERA)
            ActivityCompat.requestPermissions( requireActivity(), permissions,_requestAccessCode)

        }else{
            takePhoto()
        }
    }

    private fun showCard() {

        binding.apply {

            if(!helper.checkCardDataIsValid(tvDegree.text,tvCity.text,etNewValue.text)){
                Toast.makeText(requireContext(),getString(com.photoweather.resources.R.string.error_message_invalid_data),Toast.LENGTH_LONG).show()
                return
            }

            if(_selectedFile==null){
                Toast.makeText(requireContext(),getString(com.photoweather.resources.R.string.error_message_invalid_photo),Toast.LENGTH_LONG).show()
                return
            }
            val card=Card(degree=tvDegree.text.toString(),
                city=tvCity.text.toString(),
                description=tvDescription.text.toString(),
                placeName=etNewValue.text.toString(),
                imageLocation=_selectedFile.toString())
            val action =FormFragmentDirections.actionFormFragmentToCardFragment(card)
            findNavController().navigate(action)
        }
    }

    //
    private fun takePhoto(){

        _selectedFile = File("${requireContext().getExternalFilesDir(null)}/imgShot")
        val photoURI = FileProvider.getUriForFile(requireContext(), _authority, _selectedFile!!)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply { putExtra(MediaStore.EXTRA_OUTPUT, photoURI) }

        startActivityForResult(intent, _requestCaptureCode)
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == _requestAccessCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto()
            } else {
                Log.d(TAG, "events: camera permission denied")
               // Helper.ShowErrorDialog(requireContext(),"camera permission denied")
            }

        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==_requestCaptureCode){
            binding.img.setImageBitmap(helper.loadImageFromLocal(_selectedFile.toString()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }





}