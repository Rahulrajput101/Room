package com.example.android.room.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.room.R
import com.example.android.room.database.UserData
import com.example.android.room.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = arrayListOf<UserData>()

    fun setData(userData : List<UserData>){
        this.userList= userData as ArrayList<UserData>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser)
        holder.itemClick(currentUser)



    }

    override fun getItemCount(): Int {
         return userList.size
    }
    class MyViewHolder( private val binding : CustomRowBinding) :  RecyclerView.ViewHolder(binding.root) {

        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = CustomRowBinding.inflate(inflater, parent, false)
                return MyViewHolder(binding)


            }

        }


        fun bind(userData: UserData){
            binding.idTxt.text = userData.id.toString()
            binding.firstName.text =userData.firstName
            binding.LastNameTxt.text = userData.lastName
            binding.ageTxt.text = userData.age.toString()

        }

        fun itemClick(currentUser: UserData){
           binding.customRowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
                itemView.findNavController().navigate(action)
            }

        }


    }
}