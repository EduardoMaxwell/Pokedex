package com.eduardomaxwell.pokedex.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardomaxwell.pokedex.R
import com.eduardomaxwell.pokedex.databinding.PokemonItemBinding
import com.eduardomaxwell.pokedex.domain.Pokemon
import com.eduardomaxwell.pokedex.utils.TypeColor

class PokemonAdapter(
    private val pokemons: List<Pokemon?>,
    private val onItemClickListener: ((pokemon: Pokemon, imageView: View) -> Unit)
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.viewBind(pokemon)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.right_to_left_anim)

    }

    override fun getItemCount() = pokemons.size

    class ViewHolder(
        itemView: PokemonItemBinding?,
        private val onItemClickListener: ((pokemon: Pokemon, view: View) -> Unit)
    ) : RecyclerView.ViewHolder(itemView!!.root) {


        private val backgroundType = itemView?.pokemonBackgroundType
        private val image = itemView?.ivPokemon
        private val name = itemView?.tvName
        private val number = itemView?.tvNumber
        private val type = itemView?.tvType1
        private val type2 = itemView?.tvType2


        @SuppressLint("SetTextI18n")
        fun viewBind(item: Pokemon?) {

            item?.let {

                TypeColor.typeColor(itemView.context, item.types[0].name)?.let {
                    backgroundType?.setBackgroundResource(it)
                }

                if (image != null) {
                    Glide.with(itemView.context)
                        .load(item.imageURl)
                        .centerCrop()
                        .into(image)


                }

                name?.text = item.name.capitalize()
                number?.text = "Nº ${item.formmatedNumber}"
                type?.text = item.types[0].name.capitalize()
                if (item.types.size > 1) {
                    type2?.visibility = View.VISIBLE
                    type2?.text = item.types[1].name.capitalize()
                } else {
                    type2?.visibility = View.GONE

                }


            }

            itemView.rootView.setOnClickListener {
                item?.let { pokemon ->
                    onItemClickListener.invoke(pokemon, image as ImageView)
                }
            }


        }
    }
}