package com.example.youtubeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView



lateinit var videosRecyclerView: RecyclerView
lateinit var videoPlayerView: YouTubePlayerView
lateinit var videoPlayer: YouTubePlayer
var videosList = arrayListOf(
                        Video("ZYaZ6Odbx_Y", "Al-Fatihah (The Opener)"),
                        Video("uv3VXLVK6FQ", "Al-'Ikhlas (The Sincerity)"),
                        Video("8IQn1ZZYewc", "Al-Falaq (The Daybreak)"),
                        Video("G8h_2bvkHa0", "An-Nas (The Mankind)")
                        )

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoPlayerView = findViewById(R.id.youtube_player_view)
        videoPlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                videoPlayer = youTubePlayer
                videoPlayer.loadVideo(videosList[0].id, 0f)

                videosRecyclerView = findViewById(R.id.videos_rv)
                videosRecyclerView.adapter = RecyclerViewAdapter(videosList, videoPlayer)
                videosRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        })

        checkConnection()
    }

    private fun checkConnection() {
        val connectionLinearLayout = findViewById<LinearLayout>(R.id.connection_LL)
        val noConnectionLinearLayout = findViewById<LinearLayout>(R.id.no_connection_LL)
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting == true){
            noConnectionLinearLayout.visibility = View.INVISIBLE
            connectionLinearLayout.visibility = View.VISIBLE
        }
        else {
            connectionLinearLayout.visibility = View.INVISIBLE
            noConnectionLinearLayout.visibility = View.VISIBLE
            val refreshImageButton = findViewById<ImageButton>(R.id.refresh_btn)
            refreshImageButton.setOnClickListener {
                checkConnection()
            }
        }
    }
}