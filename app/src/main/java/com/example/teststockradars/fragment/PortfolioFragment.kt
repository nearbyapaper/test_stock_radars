package com.example.teststockradars.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teststockradars.MainActivity
import com.example.teststockradars.R
import com.example.teststockradars.adapter.PortfolioAdapter

class PortfolioFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: PortfolioAdapter
    private lateinit var rcv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_portfolio, container, false)
        binding(view)
        return view
    }

    private fun binding(view: View) {
        rcv = view.findViewById(R.id.rcvPortfolio)
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
        mainActivity.viewModel.callPortfolio()
    }

    private fun setSubscriber() {
        mainActivity.viewModel.portfolio.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()){
                adapter = PortfolioAdapter(
                    requireContext(),
                    it
                )
                rcv.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rcv.adapter = adapter

                val dividerItemDecoration = DividerItemDecoration(rcv.context, RecyclerView.VERTICAL)
                val dividerDrawable = ColorDrawable(Color.BLACK)
                dividerItemDecoration.setDrawable(dividerDrawable)

                rcv.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PortfolioFragment().apply {
            }
    }
}