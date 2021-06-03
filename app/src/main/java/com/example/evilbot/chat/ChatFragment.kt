package com.example.evilbot.chat

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evilbot.utils.Constants.SEND_ID
import com.example.evilbot.R
import com.example.evilbot.SHARED_PREFS_NAME
import com.example.evilbot.utils.BotResponse
import com.example.evilbot.utils.Constants.RECEIVE_ID
import kotlinx.coroutines.*


class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var burgerWindow: ConstraintLayout
    private lateinit var favoritesButton: AppCompatButton
    private lateinit var signOutButton: AppCompatButton
    private lateinit var icBurger: ImageButton
    private lateinit var chatInputField: EditText
    private lateinit var sendMessageButton: ImageButton
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        favoritesButton = view.findViewById(R.id.favorites_button)
        signOutButton = view.findViewById(R.id.signOut_button)
        icBurger = view.findViewById(R.id.ic_burger)
        burgerWindow = view.findViewById(R.id.burger_window)
        burgerWindow.isVisible = false
        signOutButton = view.findViewById(R.id.signOut_button)
        chatRecyclerView = view.findViewById(R.id.chat_recyclerView)
        chatInputField = view.findViewById(R.id.chat_input_editText)
        sendMessageButton = view.findViewById(R.id.send_message_button)





        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListeners()
        recyclerView()


    }


    private fun recyclerView() {

        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val chatName = sharedPreferences.getString(SHARED_PREFS_NAME, "Øivind")

        chatAdapter = ChatAdapter(
            mutableListOf(),
            chatName ?: "Øyvind"
        )
        chatRecyclerView.adapter = chatAdapter
        chatRecyclerView.layoutManager = LinearLayoutManager(context)


    }

    private fun setButtonListeners() {

        icBurger.setOnClickListener {
            burgerWindow.isGone = !burgerWindow.isGone
        }

        favoritesButton.setOnClickListener {
            (activity as ChatActivity).goToFavorites()
        }

        signOutButton.setOnClickListener {
            (activity as ChatActivity).logUserOut()
        }

        sendMessageButton.setOnClickListener {

            val message = chatInputField.text.toString()

            if (message.isNotEmpty()) {
                chatInputField.setText("")

                chatAdapter.insertMessage(ChatObject("dw", message, SEND_ID))
                chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
                if (message.contains("?")) {
                    botRespondsFromApi()
                } else {
                    GlobalScope.launch {
                        delay(1000)
                        withContext(Dispatchers.Main) {
                            val botCustomResponse = BotResponse.preSetResponses(message)
                            val chatObject = ChatObject(botCustomResponse, "", RECEIVE_ID)
                            chatAdapter.insertMessage(chatObject)
                            chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)

                        }
                    }
                }
            }
        }
    }

    private fun botRespondsFromApi() {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {

                val answer = object : InsultInterface {
                    override fun onInsultReceived(insult: ChatObject) {
                        chatAdapter.insertMessage(insult)
                        chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
                        //TODO recyclerview needs a checkup
                        //TODO OK
                    }
                }
                viewModel.getInsult(context, answer)

            }
        }
    }
}


