package com.app.mytix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytix.data.local.TicketDbHelper

class DibatalkanFragment : Fragment() {

    private lateinit var recyclerTicket: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_ticket_list,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        recyclerTicket =
            view.findViewById(R.id.recyclerTicket)

        recyclerTicket.layoutManager =
            LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()

        val dbHelper =
            TicketDbHelper(requireContext())

        val ticketList =
            ArrayList(
                dbHelper.getAllTickets().filter {
                    it.status == "Dibatalkan"
                }
            )

        recyclerTicket.adapter =
            TicketAdapter(

                ticketList,

                { ticket ->

                    dbHelper.deleteTicket(ticket.id)

                    ticketList.remove(ticket)

                    recyclerTicket.adapter
                        ?.notifyDataSetChanged()
                },

                { }
            )
    }
}