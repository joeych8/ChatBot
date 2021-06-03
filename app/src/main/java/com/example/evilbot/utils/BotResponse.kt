package com.example.evilbot.utils

object BotResponse {

    fun preSetResponses(_message: String): String {

        val random = (0..2).random()
        val noRandom = 0
        val message = _message.toLowerCase()

        return when {

            message.contains("whats your name") -> {
                when (noRandom) {
                    0 -> "Happy"
                    else -> "error"
                }
            }

            message.contains("what are you doing") -> {
                when (random) {
                    0 -> "Staring at your face"
                    1 -> "Waiting for you to ask me something interesting"
                    2 -> "nothing you need to know about"
                    else -> "error"
                }
            }

            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there"
                    1 -> "Hi!"
                    2 -> "Hey buddy"
                    else -> "error"
                }
            }

            message.contains("hi") -> {
                when (random){
                    0 -> "what's up!"
                    1 -> "Yo DAWG!"
                    2 -> "Ey Homie!"
                    else -> "error"
                }
            }

            message.contains("how are you") -> {
                when (random) {
                    0 -> "All good, thanks for asking."
                    1 -> "Dont talk to me human !"
                    2 -> "I'm Ok..."
                    else -> "error"
                }
            }

            message.contains("good bye") -> {
                when (random) {
                    0 -> "You are not going to leave are you?"
                    1 -> "Please stay!"
                    2 -> "Don't you want to know what else i have to tell you?"
                    else -> "error"
                }
            }
            else -> {
                //when user ask anything else than the above.
                when (random) {
                    0 -> "I don't understand...."
                    1 -> "I can't comprehend"
                    2 -> "Try ask me something else."
                    else -> "error"
                }
            }
        }
    }
}