package com.example.android.room.fragment.add

import android.text.TextUtils
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.room.R
import com.example.android.room.database.UserData
import com.example.android.room.databinding.FragmentAddBinding
import com.example.android.room.viewModel.viewMOdel

class AddFragment : Fragment() {

    private lateinit var binding : FragmentAddBinding
    private lateinit var viewModel: viewMOdel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_add, container, false)
        viewModel= ViewModelProvider(this).get(viewMOdel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()

        }

        return binding.root
    }

    private fun insertDataToDatabase() {
      val firstName = binding.editTextTextPersonName.text.toString()
        val lastName = binding.editTextTextPersonName2.text.toString()
        val age = binding.editTextTextPersonName3.text
        if(inputCheck(firstName, lastName, age)){
            //create user object
            val user = UserData(0,firstName,lastName, Integer.parseInt(age.toString()))
            // Add user to database
            viewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added",Toast.LENGTH_SHORT).show()
            //Navigation
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        } else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName : String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty((firstName)) && TextUtils.isEmpty((lastName)) &&TextUtils.isEmpty((age)))
    }


}