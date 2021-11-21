package com.karaew.learning.gsp_v2.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.karaew.learning.gsp_v2.Model.ModelEntity
import com.karaew.learning.gsp_v2.ViewModel.gViewModel
import com.karaew.learning.gsp_v2.R



class AddShopFragment : Fragment() {
    private lateinit var viewmodels: gViewModel
    lateinit var addButton: Button
    lateinit var addName: EditText
    lateinit var addAdress: EditText
    lateinit var addGrade: EditText
    lateinit var addSamsung: EditText
    lateinit var addOthers: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_shop, container, false)
        addButton = view.findViewById(R.id.add_info) as Button
        addName = view.findViewById(R.id.add_shop) as EditText
        addAdress = view.findViewById(R.id.add_adress) as EditText
        addGrade = view.findViewById(R.id.add_grade) as EditText
        addSamsung = view.findViewById(R.id.add_size_samsung) as EditText
        addOthers = view.findViewById(R.id.add_others) as EditText

        addButton.setOnClickListener {

            insertDaoDatabase()


        }

        viewmodels = ViewModelProvider(this).get(gViewModel::class.java)

        return view
    }

    private fun insertDaoDatabase() {
        val shop_name = addName.text.toString()
        val shop_adress = addAdress.text.toString()
        val shop_grade = addGrade.text.toString()
        val shop_samsung = addSamsung.text
        val shop_others = addOthers.text


        when {

            inputCheck(shop_name, shop_adress, shop_grade, shop_samsung, shop_others) -> {

                val shops = ModelEntity(
                    0,
                    shop_name,
                    shop_adress,
                    shop_grade,
                    Integer.parseInt(shop_samsung.toString()),
                    Integer.parseInt(shop_others.toString())
                )

                viewmodels.addShop(shops)
                viewmodels.retrofit(shops)


                Toast.makeText(requireContext(),"Запись добавлена", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addShopFragment_to_indexFragment)

            }else -> Toast.makeText(requireContext(),"Пожалуйста заполните все данные", Toast.LENGTH_LONG).show()



        }


    }

    private fun inputCheck(
        shop_name: String,
        shop_adress: String,
        shop_grade: String,
        shop_samsung: Editable,
        shop_others: Editable
    ): Boolean {

        return !(TextUtils.isEmpty(shop_name) && TextUtils.isEmpty(shop_adress) && TextUtils.isEmpty(
            shop_grade
        ) && shop_samsung.isEmpty() && shop_others.isEmpty())

    }


}