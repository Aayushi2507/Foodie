package com.example.foodie.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.foodie.R
import com.example.foodie.adapter.ResAdapter
import com.example.foodie.model.ResDetails
import com.example.foodie.util.ConnectionManager
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: ResAdapter
    val  resList = ArrayList<ResDetails>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerHome)
        layoutManager = LinearLayoutManager(activity)
        progressLayout= view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)
        progressLayout.visibility=View.VISIBLE

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"
        if(ConnectionManager().checkConnectivity(activity as Context)) {
            val jsonObjectRequest=object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {

                ConnectionManager().checkConnectivity(activity as Context)
                try {

                    val success=it.getJSONObject("data").getBoolean("success")
                    if (success){
                        progressLayout.visibility=View.GONE
                        val  data=it.getJSONObject("data").getJSONArray("data")
                        for (i in 0 until data.length()){
                            val resJSONObject = data.getJSONObject(i)
                            val restaurant=ResDetails(
                                resJSONObject.getString("id"),
                                resJSONObject.getString("name"),
                                resJSONObject.getString("rating"),
                                resJSONObject.getString("cost_for_one"),
                                resJSONObject.getString("image_url")
                            )
                            resList.add(restaurant)

                            recyclerAdapter = ResAdapter(activity as Context, resList)
                            recyclerView.adapter = recyclerAdapter
                            recyclerView.layoutManager = layoutManager

                        }
                    } else {
                        Toast.makeText(activity, "Some Error Occurred!!!", Toast.LENGTH_LONG).show()
                    }
                }catch(e: JSONException){
                    Toast.makeText(activity, "Some Error Occurred!!!", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener {
                progressBar.visibility=View.GONE
                if (activity != null) {
                    Toast.makeText(activity, "Some Unexpected Error Occurred!!!", Toast.LENGTH_LONG)
                        .show()
                }
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val header = HashMap<String, String>()
                    header["Content-Type"] = "application/json"
                    header["token"] = "6ae0eab4bfd10e"
                    return header
                }
            }

            queue.add(jsonObjectRequest)
        }else{
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection Not Found")
            dialog.setPositiveButton("Open Settings"){text, listener->
                val openSettings = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(openSettings)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit App"){text,listener->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }

        return view
    }


}
