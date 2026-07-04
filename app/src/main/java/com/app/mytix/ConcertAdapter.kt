package com.app.mytix

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity

class ConcertAdapter(
    private val listConcert: ArrayList<Concert>
) : RecyclerView.Adapter<ConcertAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgConcert: ImageView = itemView.findViewById(R.id.imgConcert)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val cardConcert: CardView = itemView as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_concert, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listConcert.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val concert = listConcert[position]

        holder.imgConcert.setImageResource(concert.image)
        holder.txtName.text = concert.name
        holder.txtPrice.text = concert.price

        holder.cardConcert.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                ConcertDetailActivity::class.java
            )

            intent.putExtra("ID", position + 1)

            holder.itemView.context.startActivity(intent)

            (holder.itemView.context as? AppCompatActivity)
                ?.overridePendingTransition(0, 0)
        }
    }
}