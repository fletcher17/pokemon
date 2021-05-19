package com.decadev.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decadev.pokemonapp.adapter.PokemonListAdapter
import com.decadev.pokemonapp.datamodel.Result
import com.decadev.pokemonapp.itemclicklistener.ItemClickListener
import com.decadev.pokemonapp.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(),ItemClickListener {

    var database : List<Result> = listOf()
    val CompositeDisposable =  CompositeDisposable()
    lateinit var pokemonListRecyclerView : RecyclerView
    lateinit var Pokemonadapter: PokemonListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonListRecyclerView = findViewById(R.id.pokemon_recyclerview)
      //  pokemonListRecyclerView.layoutManager = GridLayoutManager(this, 2)

        //This (composite disposable) subcribes on a new thread to fetch the data using a retrofit while the app keeps running,
        // then the data is subscribed on the main thread
        CompositeDisposable.add(RetrofitClient.apiservice.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                database = it.results
                println(database)
                Pokemonadapter = PokemonListAdapter(this@MainActivity, database,this)
                pokemonListRecyclerView.layoutManager = GridLayoutManager(this, 2)
                pokemonListRecyclerView.adapter = Pokemonadapter

            }
        )
    }

    //onclick function takes the position of th clicked pokemon to the next activity
    override fun onNode(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("id", position)
                    startActivity(intent)
    }


}