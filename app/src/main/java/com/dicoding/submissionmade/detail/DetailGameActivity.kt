package com.dicoding.submissionmade.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Game
import com.dicoding.submissionmade.R
import kotlinx.android.synthetic.main.activity_detail_game.*
import kotlinx.android.synthetic.main.content_detail_game.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    private val detailGameViewModel: DetailGameViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_game)

        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        if (detailGame != null) {
            toolbar.title = detailGame.name
            getDetailGame(detailGame.id)
        }

//      inject toolbar to ActionBar after title was assign
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24_white)
    }

    private fun getDetailGame(id: String) {
        detailGameViewModel.getDetailGame(id).observe(this, Observer { game ->
            when (game) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    container_detail_content.visibility = View.GONE
                }
                is Resource.Success -> {
                    showDetailGame(game.data)
                    progress_bar.visibility = View.GONE
                    container_detail_content.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    view_error.visibility = View.VISIBLE
                    tv_error.text = game.message ?: getString(R.string.something_wrong)
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            tv_original_name.text = detailGame.name_original
            tv_rating.text = "${detailGame.rating} Star (${detailGame.ratings_count} people)"
            tv_release_date.text = detailGame.released
            tv_detail_description.text = detailGame.description

            Glide.with(this@DetailGameActivity)
                .load(detailGame.image)
                .into(text_detail_image)

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            fab.visibility = View.VISIBLE
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
                val toastMessage = if (statusFavorite) "${detailGame.name} added to Favorite" else "${detailGame.name} deleted from Favorite"
                showToast(toastMessage)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    private fun showToast(text: String = "", duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}
