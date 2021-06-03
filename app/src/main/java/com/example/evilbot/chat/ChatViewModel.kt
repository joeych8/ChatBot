package com.example.evilbot.chat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.evilbot.utils.Constants
import com.google.gson.Gson



class ChatViewModel : ViewModel() {


    fun getInsult(
        context: Context?,
        insultInterface: InsultInterface,
    ) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://evilinsult.com/generate_insult.php?lang=en&type=json"
        val gson = Gson()

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->

                val insult: ChatObject = gson.fromJson<ChatObject>(response, ChatObject::class.java)
                insult.id = Constants.RECEIVE_ID
                insult.insult = insult.insult.replace("&quot;", "'")
                insult.insult = insult.insult.replace("--&gt;", "-")

                Log.d("LOG_MESSAGE", insult.insult)
                insultInterface.onInsultReceived(insult)

            },
            { error ->
                Log.d("LOG_MESSAGE", error.message.toString())
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}