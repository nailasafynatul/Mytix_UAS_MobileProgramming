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

class SemuaFragment : Fragment() {

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

        val dbHelper =
            TicketDbHelper(requireContext())

        val ticketList =
            ArrayList(
                dbHelper.getAllTickets().filter {
                    it.status != "Dibatalkan"
                }
            )

        recyclerTicket.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerTicket.adapter =
            TicketAdapter(

                ticketList,

                { ticket ->

                    AlertDialog.Builder(requireContext())
                        .setTitle("Batalkan Pesanan")
                        .setMessage("Yakin ingin membatalkan tiket ini?")
                        .setPositiveButton("Ya") { _, _ ->

                            dbHelper.updateStatus(
                                ticket.id,
                                "Dibatalkan"
                            )

                            val position =
                                ticketList.indexOf(ticket)

                            if (position != -1) {

                                ticketList.removeAt(position)

                                recyclerTicket.adapter
                                    ?.notifyItemRemoved(position)
                            }
                        }
                        .setNegativeButton("Tidak", null)
                        .show()
                },

                { ticket ->

                    val options = arrayOf(
                        "QRIS",
                        "Dana",
                        "ShopeePay",
                        "BCA VA"
                    )

                    AlertDialog.Builder(requireContext())
                        .setTitle("Pilih Pembayaran")
                        .setItems(options) { _, which ->

                            val selected =
                                options[which]

                            dbHelper.updatePaymentMethod(
                                ticket.id,
                                selected
                            )

                            ticketList.clear()

                            ticketList.addAll(
                                dbHelper.getAllTickets().filter {
                                    it.status != "Dibatalkan"
                                }
                            )

                            recyclerTicket.adapter?.notifyDataSetChanged()
                        }
                        .show()
                }
            )
    }
}