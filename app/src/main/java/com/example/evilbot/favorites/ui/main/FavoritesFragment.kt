package com.example.evilbot.favorites.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.R
import kotlinx.android.synthetic.main.favorites_fragment.*

class FavoritesFragment : Fragment() {

    lateinit var favoritesAdapter: FavoritesAdapter
    var favorite_insults = mutableListOf<String>(
        "Her skulle man egentlig kunne lagre fornærmelsene man liker best. Dette fikk vi dessverre ikke til. Beklager det! Swipe to delete disse meldingene.",
        "DENNE SIDEN ER UNDER KONSTRUKSJON",
        "For eksmepel:",
        "Jeg bryr meg ikke hva andre synes om deg, jeg synes du er kul.",
        "Det ser ut som du trenger litt søvn.",
    )


    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter = FavoritesAdapter(favorite_insults)
        favorites_recyclerview.adapter = favoritesAdapter
        favorites_recyclerview.layoutManager = LinearLayoutManager(context)


        val item = object : SwipeToDelete(context, 0, ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                favoritesAdapter.del(viewHolder.adapterPosition)
                Toast.makeText(requireContext(), "DELETED", Toast.LENGTH_LONG).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(favorites_recyclerview)

        //TODO: save_insultButton eller swipe to save på chat side
    }

}