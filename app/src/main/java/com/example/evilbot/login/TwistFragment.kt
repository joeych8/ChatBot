package com.example.evilbot.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.evilbot.R

class TwistFragment : Fragment() {


    private lateinit var viewModel: TwistViewModel
    private lateinit var askMeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.twist_fragment, container, false)

        askMeButton = view.findViewById(R.id.ask_me_button)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TwistViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setButtonListeners()

    }

    fun setButtonListeners() {
        askMeButton.setOnClickListener {
            (activity as LoginActivity).goToChatFragment()
        }

    }

}