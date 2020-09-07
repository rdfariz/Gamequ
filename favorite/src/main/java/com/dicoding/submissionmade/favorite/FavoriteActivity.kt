package com.dicoding.submissionmade.favorite

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.ui.GamesAdapter
import com.dicoding.submissionmade.detail.DetailGameActivity
import com.dicoding.submissionmade.favorite.di.favoriteModule
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24_white)
        getFavoriteData()
    }

    private fun getFavoriteData () {
        val gamesAdapter = GamesAdapter()
        gamesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGames.observe(this, { dataGame ->
            gamesAdapter.setData(dataGame)
            view_empty.visibility = if (dataGame.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_games) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gamesAdapter
        }
    }

    private fun clearFavorite () {
        val alertDialogBuilder = MaterialAlertDialogBuilder(this)
        alertDialogBuilder.setTitle("Clear All Favorite")
        alertDialogBuilder.setMessage("Are you sure want to clear all favorite data?")
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton("Yes, Clear all") { _: DialogInterface, _: Int ->
            favoriteViewModel.clearFavoriteGames()
        }
        alertDialogBuilder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.to_clearFavorite -> clearFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}