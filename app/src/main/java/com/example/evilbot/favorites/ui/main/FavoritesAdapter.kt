package com.example.evilbot.favorites.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.R

class FavoritesAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    var favorite_list = mutableListOf<String>()

    init {
        this.favorite_list = dataSet as MutableList<String>
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.favorites_textView)

        }
    }

    fun del(position: Int) {
        favorite_list.removeAt(position)
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.favorites_recyclerview_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    //TODO: private inner class Favorites_Viewholder(val view: TextView): RecyclerView.ViewHolder(view)

}