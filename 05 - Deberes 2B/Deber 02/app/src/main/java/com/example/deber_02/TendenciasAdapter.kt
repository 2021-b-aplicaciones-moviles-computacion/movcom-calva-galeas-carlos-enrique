package com.example.deber_02

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage


class TendenciasAdapter(
    private val context: MainActivity,
    private val listaTendencias: List<Tendencias>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<TendenciasAdapter.MyViewHolder>() {
    inner class  MyViewHolder(view: View,viewType: Int ): RecyclerView.ViewHolder(view){

        val list = mutableListOf<CarouselItem>()
        lateinit var carouselPrincipal:ImageCarousel
        lateinit var titulo: TextView
        lateinit var icHash: ImageView
        lateinit var hash: TextView
        lateinit var numero: TextView
        lateinit var image1: ImageView
        lateinit var image2: ImageView
        lateinit var image3: ImageView
        lateinit var image4: ImageView
        lateinit var image5: ImageView
        lateinit var image6: ImageView
        var view_Type: Int = 0
        var contador: Int = 0

        init {

            Log.i("init","Entro viewType${viewType}")

            if(viewType == 1){
                carouselPrincipal = view.findViewById(R.id.carrusel)
                view_Type = 1
                Log.i("init","viewType${viewType}")
                contador = contador+1
            }

            if(viewType == 0){
                titulo = view.findViewById(R.id.txtTitulo)
                hash = view.findViewById(R.id.hashtextView)
                icHash = view.findViewById(R.id.icHash)
                numero = view.findViewById(R.id.NumerotextView)
                image1 = view.findViewById(R.id.image1)
                image2 = view.findViewById(R.id.image2)
                image3 = view.findViewById(R.id.image3)
                image4 = view.findViewById(R.id.image4)
                image5 = view.findViewById(R.id.image5)
                image6 = view.findViewById(R.id.image6)
                view_Type = 0
                contador = contador+1
                Log.i("init","viewType${viewType}")
            }
            Log.i("init","Paso viewType${viewType}")

            Log.i("init","Salio viewType${viewType}")


        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return 1
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

       var conut:Int = 0
        var layout = R.layout.carrusel_tendencias
        if (viewType == 0){
            layout = R.layout.item_tendencias_view
            Log.i("onCreateViewHolder","conut${conut}")
            Log.i("onCreateViewHolder","viewType${viewType}")
        }
        if (viewType == 1){
            layout = R.layout.carrusel_tendencias
            conut = 1
            Log.i("onCreateViewHolder","conut${conut}")
            Log.i("onCreateViewHolder","viewType${viewType}")
        }
        var itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                layout, //Definimos la vista de nuestro recyvler
                parent,
                false
            )

        return MyViewHolder(itemView,conut)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        Log.i("onBindViewHolder","position ${position}")
        if(holder.view_Type == 1){
            Log.i("onBindViewHolder","Entro holder ${holder.view_Type}")
            holder.list.add(CarouselItem(imageUrl = "https://seguidoresdeinstagram.com/wp-content/uploads/2021/12/Cierre-de-tik-tok-en-2022.jpg"))
            holder.list.add(CarouselItem(imageUrl = "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2018/02/287047-frases-imagenes-whatsapp-san-valentin.jpg?itok=veAUccoa"))
            holder.list.add(CarouselItem(imageUrl = "https://p16-amd-va.tiktokcdn.com/obj/musically-maliva-obj/43de28e3e5615db6503465f9ed029ae8.jpeg"))
            holder.list.add(CarouselItem(imageUrl = "https://www.muycomputer.com/wp-content/uploads/2020/12/Top-100-TikTok-2020-e1606995460894.jpg"))
            holder.carouselPrincipal.addData(holder.list)

            Log.i("onBindViewHolder","Es la Pos${holder.view_Type}")

        }
        if(holder.view_Type == 0){

            val vid = listaTendencias[position -1]
            Log.i("onBindViewHolder","position ${listaTendencias[position-1]}")
            holder.titulo.text = vid.titulo
            holder.hash.text = vid.hash
            //holder.icHash.setImage(CarouselItem("https://cdn-icons.flaticon.com/png/512/3423/premium/3423288.png?token=exp=1644533237~hmac=f3ad66e21c0e3b95b7904a0573461364"))
            holder.numero.text = vid.numero
            holder.image1.setImage(CarouselItem("https://p16-sign-va.tiktokcdn.com/tos-maliva-p-0068/56027ef3feef4656959e9e2454396a62_1640894441~tplv-dmt-logom:tos-useast2a-v-0068/30423f63145f4722842f20f0a4d323c8.image?x-expires=1644548400&x-signature=4PikjAnkp0ZeSa9LMyRZ0UMeZ6g%3D"))
            holder.image2.setImage(CarouselItem("https://imgmedia.elpopular.pe/640x345/elpopular/original/2020/12/13/5fd641b5fd04640ad7266a56.webp"))
            holder.image3.setImage(CarouselItem("https://as.com/epik/imagenes/2020/04/16/videos/1587052442_441293_1587053020_noticia_normal.jpg"))
            holder.image4.setImage(CarouselItem("https://depor.com/resizer/xjZkkZ00UVgoIihf7A7KyAbUJYY=/980x0/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/6NJOCIEVWZAY3M7FMBWJZX5Z6E.jpg"))
            holder.image5.setImage(CarouselItem("https://www.mdzol.com/u/fotografias/m/2021/7/10/f608x342-1080203_1109926_0.jpeg"))
            holder.image6.setImage(CarouselItem("https://cdn.andro4all.com/files/2020/12/Los-videos-mas-vistos-de-TikTok-de-2020-son-virales.jpg"))
            Log.i("onBindViewHolder","Es la Pos${holder.view_Type}")
        }

    }

    override fun getItemCount(): Int {
       return (listaTendencias.size +1)
    }

}