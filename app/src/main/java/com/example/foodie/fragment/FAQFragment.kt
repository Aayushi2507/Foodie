package com.example.foodie.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.foodie.R
import com.example.foodie.adapter.FAQAdapter
import com.example.foodie.model.FAQ

/**
 * A simple [Fragment] subclass.
 */
class FAQFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: FAQAdapter
    lateinit var  faqList: ArrayList<FAQ>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_faq, container, false)

        recyclerView = view.findViewById(R.id.recyclerFAQ)
        layoutManager = LinearLayoutManager(activity)

        faqList = arrayListOf<FAQ>(
            FAQ("Can I change the delivery address", "Yes, in the navigation bar, you can change the delivery address and other details."),
            FAQ("How can I edit my app content","Your application setting allows you to easily add, edit and delete content from your dashboard within minutes. You can update your app content everyday if you want to.For custom changes, our mobile app development team will assist you depending on the type of change you want in the app."),
            FAQ("Do coupons work via app","Yes, coupons, discounts, offers and loyalty programs can be updated in the app for the customers to avail them."),
            FAQ("Are there security measures taken for online ordering software","Yes, we have reliable security measures taken for our online ordering software. We provide security at the database level and at the api end, which is very important."),
            FAQ("Can I order from 2 or more restaurants at the same time","No,\n" +
                    "It's not possible to order from two restaurants together, however you can place order from two different restaurants one after another."),
            FAQ("Why do you charge delivery charges", "Each restaurant gives us a small percentage of the order amount. But for small orders we cannot afford to deliver within the commission we receive. Instead of declining such orders, we give you the option of delivering it to you, if you are prepared to pay the delivery charges.")
        )


        recyclerAdapter = FAQAdapter(activity as Context, faqList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )
        return view
    }

}
