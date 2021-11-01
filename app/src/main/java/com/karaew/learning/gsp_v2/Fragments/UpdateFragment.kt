package com.karaew.learning.gsp_v2.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.karaew.learning.gsp_v2.Model.ModelEntity
import com.karaew.learning.gsp_v2.R
import com.karaew.learning.gsp_v2.ViewModel.gViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var updateShop: EditText
    lateinit var updateAdress: EditText
    lateinit var updateGrade: EditText
    lateinit var updateSamsung: EditText
    lateinit var updateOthers: EditText
    lateinit var updateButton: Button
    private lateinit var viewModel: gViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        viewModel = ViewModelProvider(this).get(gViewModel::class.java)
        updateShop = view.findViewById(R.id.update_shop) as EditText
        updateAdress = view.findViewById(R.id.update_adress) as EditText
        updateGrade = view.findViewById(R.id.update_grade) as EditText
        updateSamsung = view.findViewById(R.id.update_size_samsung) as EditText
        updateOthers = view.findViewById(R.id.update_others) as EditText
        updateButton = view.findViewById(R.id.update_info)
        updateShop.setText(args.currentShop.shop_name)
        updateAdress.setText(args.currentShop.shop_adress)
        updateGrade.setText(args.currentShop.shop_grade)
        updateSamsung.setText(args.currentShop.size_samsung.toString())
        updateOthers.setText(args.currentShop.size_other.toString())


        updateButton.setOnClickListener {

            updateShopItem()

        }


        setHasOptionsMenu(true)


        return view
    }


    private fun updateShopItem() {
        val update_name = updateShop.text.toString()
        val update_adress = updateAdress.text.toString()
        val update_grade = updateGrade.text.toString()
        val update_samsung = Integer.parseInt(updateSamsung.text.toString())
        val update_others = Integer.parseInt(updateOthers.text.toString())

        when {
            inputCheck(
                update_name,
                update_adress,
                update_grade,
                updateSamsung.text,
                updateOthers.text
            ) -> {

                val updateShop = ModelEntity(
                    args.currentShop.id,
                    update_name,
                    update_adress,
                    update_grade,
                    update_samsung,
                    update_others
                )
                viewModel.update(updateShop)
                Toast.makeText(requireContext(), "Запись обновлена", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_indexFragment)
            }
            else -> Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_LONG).show()

        }


    }

    private fun inputCheck(
        update_name: String,
        update_adress: String,
        update_grade: String,
        update_samsung: Editable,
        update_others: Editable
    ): Boolean {

        return !(TextUtils.isEmpty(update_name) && TextUtils.isEmpty(update_adress) && TextUtils.isEmpty(
            update_grade
        ) && update_samsung.isEmpty() && update_others.isEmpty())

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when {

            item.itemId == R.id.delete_shop -> {

                deleteShop()


            }


        }




        return super.onOptionsItemSelected(item)
    }


    private fun deleteShop() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->

            viewModel.deleteShop(args.currentShop)
            findNavController().navigate(R.id.action_updateFragment_to_indexFragment)
            Toast.makeText(requireContext(),"Запись: ${args.currentShop.shop_name},${args.currentShop.shop_adress}. удалена",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить запись ${args.currentShop.shop_name},${args.currentShop.shop_adress}?")
        builder.setMessage("После удаления данные невозможно будет вернуть")
        builder.create().show()
    }


}