package com.karaew.learning.gsp_v2.Fragments.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.karaew.learning.gsp_v2.Fragments.IndexFragmentDirections
import com.karaew.learning.gsp_v2.Model.ModelEntity
import com.karaew.learning.gsp_v2.R

class ListFragmentAdapter : RecyclerView.Adapter<ListFragmentAdapter.MyViewHolder>() {

    private var shopList = emptyList<ModelEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val shop_name: TextView
        val shop_adress: TextView
        val shop_grade: TextView
        val shop_samsung: TextView
        val shop_others: TextView
        val rowLayout: View
        init {
            shop_name = itemView.findViewById(R.id.shop_name)
            shop_adress = itemView.findViewById(R.id.shop_adress)
            shop_grade = itemView.findViewById(R.id.shop_grade)
            shop_samsung = itemView.findViewById(R.id.shop_samsung)
            shop_others = itemView.findViewById(R.id.shop_others)
            rowLayout = itemView.findViewById(R.id.rowLayout)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = shopList[position]
        holder.shop_name.text = "Партнер: ${currentItem.shop_name}"
        holder.shop_adress.text = "Адрес: ${currentItem.shop_adress}"
        holder.shop_grade.text = " Грейд: ${currentItem.shop_grade}"
        holder.shop_samsung.text = "Samsung: ${currentItem.size_samsung.toString()}"
        holder.shop_others.text = "Others:${currentItem.size_other.toString()}"


        holder.rowLayout.setOnClickListener {

            val action = IndexFragmentDirections.actionIndexFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }

    }

    override fun getItemCount() = shopList.size

    fun setData(shop: List<ModelEntity>) {

        this.shopList = shop
        notifyDataSetChanged()
    }
}