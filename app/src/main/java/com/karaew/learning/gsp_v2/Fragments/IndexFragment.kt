package com.karaew.learning.gsp_v2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karaew.learning.gsp_v2.ViewModel.gViewModel
import com.karaew.learning.gsp_v2.R
import com.karaew.learning.gsp_v2.Fragments.Adapter.ListFragmentAdapter

class IndexFragment : Fragment() {
    private lateinit var recyler: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = ListFragmentAdapter()
        val view = inflater.inflate(R.layout.fragment_index, container, false)
        val viewModel = ViewModelProvider(this).get(gViewModel::class.java)




        recyler = view.findViewById(R.id.recylerView)
        recyler.adapter = adapter
        recyler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readListShop.observe(viewLifecycleOwner, Observer { shop ->

            adapter.setData(shop)

        })
floatingActionButton = view.findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_indexFragment_to_addShopFragment2)}

        return view
    }

}