package com.decadev.pokemonapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decadev.pokemonapp.adapter.PokemonTypeAdapter
import com.decadev.pokemonapp.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {

    lateinit var height : TextView
    lateinit var weight : TextView
    lateinit var type : TextView
    lateinit var image: ImageView
    lateinit var name : TextView

    lateinit var baseExperience: ProgressBar
    lateinit var baseExperienceHolder : TextView

    lateinit var gameIndexHolder : TextView
    lateinit var gameIndex : ProgressBar

    lateinit var base_statHolder : TextView
    lateinit var base_stat: ProgressBar

    lateinit var recyclerview_type : RecyclerView
    lateinit var typeAdapter : PokemonTypeAdapter

     var compositeDisposable =  CompositeDisposable()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        height = findViewById(R.id.inner_cardview_detail_height)
        weight = findViewById(R.id.inner_cardview_detail_weight)
        type = findViewById(R.id.inner_cardview_detail_type)
        image = findViewById(R.id.inner_cardview_detail_Image)
        name = findViewById(R.id.inner_cardview_detail_name)

        baseExperience = findViewById(R.id.base_experience_progressBar)
        baseExperienceHolder = findViewById(R.id.inner_cardview_detail_base_experience)

        gameIndex = findViewById(R.id.game_index_progressBar)
        gameIndexHolder = findViewById(R.id.inner_cardview_detail_game_index)

        base_statHolder = findViewById(R.id.inner_cardview_detail_base_stat)
        base_stat = findViewById(R.id.base_stat_progressBar)

        recyclerview_type = findViewById(R.id.inner_cardview_recyclerview_type)

        baseExperience.max = 3000
        gameIndex.max = 200
        base_stat.max = 100

        //The position of the clicked pokemon from the main activity is passed into the end point fetched by the retrofit.
        var position = intent.extras?.getInt("id")
        Glide.with(this).load("https://pokeres.bastionbot.org/images/pokemon/${position!!}.png").into(image)
        compositeDisposable.add(RetrofitClient.apiservice.getPokemon(position!!)
            .subscribeOn(Schedulers.io())//creates a new thread
            .observeOn(AndroidSchedulers.mainThread())//back to the main thread
            .subscribe{
                height.text = it.height.toString() + "Kg"
                weight.text = it.weight.toString() + "m"
//              type.text = it.abilities[0].slot.toString()
                name.text = it.name

                //The base experience data is passed into the progress bar and also displayed as a textview
                getString(R.string.base_experience, it.base_experience).also { baseExperienceHolder.text = it }
                val baseExperienceProgress = it.base_experience*10
                ObjectAnimator.ofInt(baseExperience, "progress", baseExperienceProgress)
                        .setDuration(2000)
                        .start()

                //The game index data is passed into the progress bar and also displayed as a textview
                gameIndexHolder.text = getString(R.string.game_index, it.game_indices[1].game_index)
                val gameIndexProgress = it.game_indices[1].game_index
                ObjectAnimator.ofInt(gameIndex, "progress", gameIndexProgress)
                        .setDuration(2000)
                        .start()

                //The Base stat progress is passed into the progress bar and also displayed as a textview
                base_statHolder.text = getString(R.string.base_stat, it.stats[2].base_stat)
                val baseStatProgress = it.stats[1].base_stat
                ObjectAnimator.ofInt(base_stat, "progress", baseStatProgress)
                        .setDuration(2000)
                        .start()

                recyclerview_type.layoutManager = LinearLayoutManager(this)
                typeAdapter = PokemonTypeAdapter(this, it.types)
                recyclerview_type.adapter = typeAdapter


            })



//        var position = intent.extras?.getString("id")
//        println("God help femi $position")

    }
}