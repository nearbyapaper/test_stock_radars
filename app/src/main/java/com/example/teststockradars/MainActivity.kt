package com.example.teststockradars

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teststockradars.fragment.IndicesFragment
import com.example.teststockradars.fragment.PortfolioFragment
import com.example.teststockradars.viewmodel.StocksViewModel
import com.example.teststockradars.viewmodel.StocksViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var tvToolbar: TextView
    private lateinit var ivLeftToolbar: ImageView
    private lateinit var ivRightToolbar: ImageView
    lateinit var viewModel: StocksViewModel
    private lateinit var factory: StocksViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tvToolbar = findViewById(R.id.tvToolbar)
        ivLeftToolbar = findViewById(R.id.ivLeftToolbar)
        ivRightToolbar = findViewById(R.id.ivRightToolbar)
        setTitleToolbar(R.string.toolbar_indices)

        setUpBottomNav()
        setUpViewModel()

        if (savedInstanceState == null) {
            replaceFragment(IndicesFragment(), IndicesFragment::class.java.simpleName)
        }
    }

    private fun setTitleToolbar(toolbarTitle: Int) {
        tvToolbar.text = getString(toolbarTitle)
    }

    override fun onStart() {
        super.onStart()
        viewModel.callIndices()
    }

    private fun setUpViewModel() {
        factory = StocksViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory)[StocksViewModel::class.java]
    }

    private fun setUpBottomNav() {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.indices -> {
                    replaceFragment(IndicesFragment.newInstance(), IndicesFragment::class.java.simpleName)
                    setTitleToolbar(R.string.toolbar_indices)
                    toolbar.setBackgroundColor(getColor(R.color.blue))
                    ivLeftToolbar.visibility = View.GONE
                    true
                }
                R.id.portfolio -> {
                    replaceFragment(PortfolioFragment.newInstance(), PortfolioFragment::class.java.simpleName)
                    setTitleToolbar(R.string.toolbar_portfolio)
                    toolbar.setBackgroundColor(getColor(R.color.orange))
                    ivLeftToolbar.visibility = View.VISIBLE
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentActivity, fragment)
            .addToBackStack(tag)
            .commit()
    }
}
