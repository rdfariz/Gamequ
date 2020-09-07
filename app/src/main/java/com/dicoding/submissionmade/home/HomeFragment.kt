package com.dicoding.submissionmade.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.data.Resource
import com.dicoding.core.ui.GamesAdapter
import com.dicoding.submissionmade.R
import com.dicoding.submissionmade.detail.DetailGameActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val gamesAdapter = GamesAdapter()
            gamesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.games.observe(viewLifecycleOwner, { games ->
                if (games != null) {
                    when (games) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            gamesAdapter.setData(games.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = games.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(rv_games) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gamesAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.to_aboutCreator -> {
                val alertDialogBuilder = context?.let { MaterialAlertDialogBuilder(it) }
                alertDialogBuilder?.setTitle("About Creator")
                alertDialogBuilder?.setMessage("Raden Fariz Insan P\n\nSUBMISSION 1 - DICODING MADE")
                alertDialogBuilder?.setCancelable(true)
                alertDialogBuilder?.show()
            }
            R.id.to_toggleTheme -> toggleTheme()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleTheme () {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
