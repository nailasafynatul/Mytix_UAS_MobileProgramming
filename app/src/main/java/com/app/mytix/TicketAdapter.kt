package com.app.mytix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketAdapter(
    private val listTicket: ArrayList<Ticket>,
    private val onDelete: (Ticket) -> Unit,
    private val onEdit: (Ticket) -> Unit
) : RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgTicket: ImageView =
            itemView.findViewById(R.id.imgTicket)

        val txtConcert: TextView =
            itemView.findViewById(R.id.txtConcert)

        val txtCategory: TextView =
            itemView.findViewById(R.id.txtCategory)

        val txtPayment: TextView =
            itemView.findViewById(R.id.txtPayment)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStatus)

        val txtDate: TextView =
            itemView.findViewById(R.id.txtDate)

        val txtPrice: TextView =
            itemView.findViewById(R.id.txtPrice)

        val btnEditPayment: Button =
            itemView.findViewById(R.id.btnEditPayment)

        val btnCancelTicket: Button =
            itemView.findViewById(R.id.btnCancelTicket)

        val btnDelete: ImageButton =
            itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTicket.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val ticket = listTicket[position]

        holder.imgTicket.setImageResource(ticket.image)

        holder.txtConcert.text =
            ticket.concert

        holder.txtCategory.text =
            ticket.category

        holder.txtPayment.text =
            "Pembayaran: ${ticket.paymentMethod}"

        holder.txtStatus.text =
            "Status: ${ticket.status}"

        holder.txtDate.text =
            ticket.date

        holder.txtPrice.text =
            ticket.price

        if (ticket.status == "Dibatalkan") {

            holder.btnDelete.visibility = View.VISIBLE

            holder.btnEditPayment.visibility = View.GONE

            holder.btnCancelTicket.visibility = View.GONE

        } else {

            holder.btnDelete.visibility = View.GONE

            holder.btnEditPayment.visibility = View.VISIBLE

            holder.btnCancelTicket.visibility = View.VISIBLE
        }

        holder.btnEditPayment.setOnClickListener {

            onEdit(ticket)
        }

        holder.btnCancelTicket.setOnClickListener {

            onDelete(ticket)
        }

        holder.btnDelete.setOnClickListener {

            onDelete(ticket)
        }
    }
}