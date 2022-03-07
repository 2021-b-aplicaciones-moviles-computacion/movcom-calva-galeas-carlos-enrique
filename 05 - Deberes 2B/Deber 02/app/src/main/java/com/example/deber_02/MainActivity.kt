package com.example.deber_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layoutHeader: ViewGroup
        var layoutBody: ViewGroup


        layoutHeader = findViewById(R.id.header_layout)
        val inflater = LayoutInflater.from(this)
            .inflate(R.layout.cabecera_inicio,layoutHeader,false)
        layoutHeader.removeAllViews();
        layoutHeader.addView(inflater)


        layoutBody = findViewById(R.id.principal_layout)
        val inflater2 = LayoutInflater.from(this)
            .inflate(R.layout.videos_view,layoutBody,false)
        layoutBody.removeAllViews();
        layoutBody.addView(inflater2)

        val recyclerViewVideosTikTok = inflater2.findViewById<RecyclerView>(R.id.rcv_videos)
        val listaVideos = arrayListOf<Videos>()
        listaVideos
            .add(Videos("https://media2.giphy.com/media/SRrbiFCfEzFPaKsXMA/giphy.gif?cid=ecf05e47rl6sfawxsaphlwbgt4wkyrv78utxnofzf3emztuz&rid=giphy.gif&ct=g",
                "@Juan","#parati #spot #fail ","♫ sonido original - it´s your time"
            ))
        listaVideos
            .add(Videos("https://media4.giphy.com/media/gGqoSJTgyBUgNp7LeC/giphy.gif?cid=ecf05e478quviilbelbago6zsrf7lfq7pek3rtjsvspapg68&rid=giphy.gif&ct=g",
                "@Funnycats","#cats #kiss","♫ sonido original - friends"))
        listaVideos
            .add(Videos("https://media4.giphy.com/media/dzCmqUIeROWtdwaHeO/giphy.gif?cid=ecf05e47plropkpodtl0wxyka9ojuxbhl71nick21wsfkxgp&rid=giphy.gif&ct=g",
                "@NFLSports","#sports #nfl #2022","♫ sonido original - on fire"))

        iniciarRecyclerView(
            listaVideos,
            this,
            recyclerViewVideosTikTok
        )


        val menuBar = findViewById<BottomNavigationView>(
            R.id.nav_bar
        )
        menuBar.setItemIconTintList(null)
        menuBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            MenuItem ->
            when(MenuItem.itemId){
                R.id.mnu_home -> {

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.videos_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.cabecera_inicio,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val recyclerViewvideos = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_videos
                    )

                    iniciarRecyclerView(
                        listaVideos,
                        this,
                        recyclerViewvideos
                    )


                }
                R.id.mnu_buscar -> {

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.cabecera_tendencia,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.tendencias_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val recyclerViewTendencias = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_tendencias
                    )

                    val listaTendencias = arrayListOf<Tendencias>()

                    listaTendencias
                        .add(Tendencias("Recuerdos","Hashtag popular","4.3B"))
                    listaTendencias
                        .add(Tendencias("TemporadDePremios","Hashtag popular","22.8M"))
                    listaTendencias
                        .add(Tendencias("Serenata","Hashtag popular","24.2M"))
                    listaTendencias
                        .add(Tendencias("EuphoriaT2","Hashtag popular","110.9M"))
                    listaTendencias
                        .add(Tendencias("StoryTime","Hashtag popular","149.8B"))
                    listaTendencias
                        .add(Tendencias("EspirituOlimpico","Hashtag popular","1.0M"))
                    listaTendencias
                        .add(Tendencias("TikTokAutos","Hashtag popular","922.5M"))
                    listaTendencias
                        .add(Tendencias("GamerLove","Hashtag popular","111.5M"))

                    iniciarRecyclerViewBuscar(
                        listaTendencias,
                        this,
                        recyclerViewTendencias
                    )

                }

                R.id.mnu_bandeja -> {

                    val inflater = LayoutInflater.from(this)
                        .inflate(R.layout.cabecera_bandeja,layoutHeader,false)
                    layoutHeader = findViewById(R.id.header_layout)
                    layoutHeader.removeAllViews();
                    layoutHeader.addView(inflater)

                    val inflater2 = LayoutInflater.from(this)
                        .inflate(R.layout.bandeja_view,layoutBody,false)
                    layoutBody = findViewById(R.id.principal_layout)
                    layoutBody.removeAllViews();
                    layoutBody.addView(inflater2)

                    val recyclerViewBandeja = inflater2.findViewById<RecyclerView>(
                        R.id.rcv_bandeja
                    )

                    val listaBandeja = arrayListOf<Bandeja>()

                    listaBandeja
                        .add(Bandeja("Maria Diaz","Es amigo/a de Juan Perez"))
                    listaBandeja
                        .add(Bandeja("Juan Enriquez","Es amigo/a de Juan Perez"))
                    listaBandeja
                        .add(Bandeja("Luis Montaleza","Gente que quizás conozcas"))
                    listaBandeja
                        .add(Bandeja("Luisa Maria","Gente que quizás conozcas"))
                    listaBandeja
                        .add(Bandeja("Esteban Ortiz","Es amigo/a de Carlos Saenz"))
                    listaBandeja
                        .add(Bandeja("Gissela Bolivar","Gente que quizás conozcas"))
                    listaBandeja
                        .add(Bandeja("Lupe Galeas","Gente que quizás conozcas"))
                    listaBandeja
                        .add(Bandeja("Berta Peña","Es amigo/a de Mateo Morales"))
                    listaBandeja
                        .add(Bandeja("Lisa Suarez","Es amigo/a de Carlos Saenz"))
                    listaBandeja
                        .add(Bandeja("Juan Enriquez","Gente que quizás conozcas"))


                    iniciarRecyclerViewBandeja(
                        listaBandeja,
                        this,
                        recyclerViewBandeja
                    )
                }

            }
            return@OnItemSelectedListener true
        })
    }

    fun iniciarRecyclerView(
        lista: List<Videos>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = VideosAdapter(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewBuscar(
        lista: List<Tendencias>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = TendenciasAdapter(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewBandeja(
        lista: List<Bandeja>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = BandejaAdapter(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }


}
