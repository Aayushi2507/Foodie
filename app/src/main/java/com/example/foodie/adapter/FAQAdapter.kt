package com.example.foodie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.FAQ

class FAQAdapter(val context: Context, val faqList: ArrayList<FAQ>): RecyclerView.Adapter<FAQAdapter.viewHolder>() {

    class viewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtQues: TextView = view.findViewById(R.id.txtQues)
        val txtAns: TextView = view.findViewById(R.id.txtAns)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_faq, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return faqList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val faq = faqList[position]
        holder.txtQues.text = "Ques: ${faq.Question}?"
        holder.txtAns.text = faq.Answer
    }
}
