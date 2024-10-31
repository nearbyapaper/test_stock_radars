package com.example.teststockradars.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teststockradars.MainActivity
import com.example.teststockradars.R
import com.example.teststockradars.adapter.IndicesAdapter
import java.text.SimpleDateFormat
import java.util.Locale

class IndicesFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: IndicesAdapter
    private lateinit var rcv: RecyclerView
    private lateinit var tvLastUpdate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_indices, container, false)
        binding(view)
        return view
    }

    private fun binding(view: View) {
        rcv = view.findViewById(R.id.rcvIndices)
        tvLastUpdate = view.findViewById(R.id.tvLastUpdate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isAdded && activity != null) {
            mainActivity = activity as MainActivity
            mainActivity.supportActionBar?.title = ""
        }
        setSubscriber()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        mainActivity.viewModel.callIndices()
    }

    @SuppressLint("SetTextI18n")
    private fun setSubscriber() {
        mainActivity.viewModel.indices.observe(viewLifecycleOwner) {
            tvLastUpdate.text = "Last Update : " + it.lastUpdate?.let { it1 ->
                formatLastUpdateDate(
                    it1
                )
            }

            it.data?.let { list ->
                adapter = IndicesAdapter(
                    requireContext(),
                    list
                )
                rcv.layoutManager = GridLayoutManager(context, 2)
                rcv.adapter = adapter

                val dividerItemDecoration = DividerItemDecoration(rcv.context, RecyclerView.VERTICAL)
                val dividerDrawable = ColorDrawable(Color.BLACK)
                dividerItemDecoration.setDrawable(dividerDrawable)

                rcv.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private fun formatLastUpdateDate(lastUpdate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
            val date = inputFormat.parse(lastUpdate)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            Log.e("IndicesFragment", "Date parsing error: ${e.message}")
            lastUpdate
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = IndicesFragment()
    }
}
