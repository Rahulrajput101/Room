package com.example.android.room.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.room.R
import com.example.android.room.database.UserData
import com.example.android.room.databinding.FragmentUpdateBinding
import com.example.android.room.fragment.add.AddFragmentDirections
import com.example.android.room.viewModel.viewMOdel


class UpdateFragment : Fragment() {

    private lateinit var binding : FragmentUpdateBinding
    private lateinit var viewModel : viewMOdel
    private  val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        viewModel= ViewModelProvider(this)[viewMOdel::class.java]
        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updatEditTextTextPersonName2.setText(args.currentUser.lastName)
        binding.updatEditTextTextPersonName3.setText(args.currentUser.age.toString())


        binding.updatedButton.setOnClickListener { updateItem() }

        setHasOptionsMenu(true)

        return binding.root
    }



    private fun updateItem(){
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updatEditTextTextPersonName2.text.toString()
        val age = binding.updatEditTextTextPersonName3.text
        if(inputCheck(firstName, lastName, age)){
            //create user object
            val updatedUser = UserData(args.currentUser.id,firstName,lastName, Integer.parseInt(age.toString()))
            // Add user to database
            viewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"successfully added", Toast.LENGTH_SHORT).show()
            //Navigation
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
        } else{
            Toast.makeText(requireContext(),"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }



    private fun inputCheck(firstName : String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty((firstName)) && TextUtils.isEmpty((lastName)) && TextUtils.isEmpty((age)))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteUser(args.currentUser)
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())
            Toast.makeText(requireContext(),
                " ${args.currentUser.firstName} is deleted",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete: ${args.currentUser.firstName}?")
        builder.create().show()
    }
}