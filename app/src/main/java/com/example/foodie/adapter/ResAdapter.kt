package com.example.foodie.adapter

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.foodie.R
import com.example.foodie.activity.RestaurantDetailsActivity
import com.example.foodie.database.Database
import com.example.foodie.database.ResEntities
import com.example.foodie.database.ResEntities
import com.example.foodie.model.ResDetails
import com.squareup.picasso.Picasso

class ResAdapter(val context : Context, private val resList: ArrayList<ResDetails>): RecyclerView.Adapter<ResAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgFood: ImageView = view.findViewById(R.id.imgFood)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val btnFav: ImageView = view.findViewById(R.id.btnFav)
        val llContent: CardView = view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_res, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = resList[position]
        holder.txtName.text = restaurant.resName
        holder.txtPrice.text = "₹${restaurant.resPrice}/person"
        holder.txtRating.text = restaurant.resRating
        Picasso.get().load(restaurant.resImg).error(R.drawable.color_logo).into(holder.imgFood)

        val resEntities = ResEntities(
            restaurant.resId,
            restaurant.resName,
            restaurant.resRating,
            restaurant.resPrice,
            restaurant.resImg
        )

        //val listOfFavourites = GetAllFavAsyncTask(context).execute().get()

      /*  if (listOfFavourites.isNotEmpty() && listOfFavourites.contains(restaurant.resId)) {
            holder.btnFav.setImageResource(R.drawable.ic_favourite)
        } else {
            holder.btnFav.setImageResource(R.drawable.ic_nt_favourite)
        }*/
        holder.btnFav.setOnClickListener{
            if (!DBAsyncTask(context,resEntities, 1).execute().get()) {
                val async =
                    DBAsyncTask(context, resEntities, 2).execute()
                val result = async.get()
                if (result) {
                    holder.btnFav.setImageResource(R.drawable.ic_favourite)
                }
            } else {
                val async = DBAsyncTask(context, resEntities, 3).execute()
                val result = async.get()
                if (result) {
                    holder.btnFav.setImageResource(R.drawable.ic_nt_favourite)
                }
            }
        }

        holder.llContent.setOnClickListener{
            val intent = Intent(context, RestaurantDetailsActivity::class.java)
            intent.putExtra("Id", restaurant.resId)
            intent.putExtra("Name", restaurant.resName)
            intent.putExtra("Rating", restaurant.resRating)
            intent.putExtra("Price", restaurant.resPrice)
            intent.putExtra("Img", restaurant.resImg)
            context.startActivity(intent)
        }
    }

    class DBAsyncTask(val context: Context, val favEntities: ResEntities, val mode: Int):
        AsyncTask<Void, Void, Boolean>() {

        // mode1 = check DB if database is favorites or not
        // mode2 = save the books in DB as favourites
        // mode3 = delete the book from favourites

        private val db = Room.databaseBuilder(context, Database::class.java, "db").build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when (mode) {
                1 -> {
                    val food: ResEntities? = db.resDao().getResById(favEntities.res_Id)
                    db.close()
                    return food != null
                }
                2 -> {
                    db.resDao().insertRes(favEntities)
                    db.close()
                    return true
                }
                3 -> {
                    db.resDao().deleteRes(favEntities)
                    db.close()
                    return true
                }
            }
            return false
        }
    }



}