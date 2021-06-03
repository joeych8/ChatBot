package com.example.evilbot.login

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.evilbot.R
import com.example.evilbot.SHARED_PREFS_NAME
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var submitButton: Button
    private lateinit var loginPageAnimation: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        submitButton = view.findViewById(R.id.submit_button)
        loginPageAnimation = view.findViewById(R.id.login_figure)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListener()


    }

    fun setButtonListener() {


        submitButton.setOnClickListener {

            val name = editText_name.text

            val sharedPreferences =
                requireActivity().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString(SHARED_PREFS_NAME, name.toString())

            editor.apply()

            fun toast(toast: String) {
                val toaster = Toast.makeText(
                    context,
                    Html.fromHtml("<font color='#ffffff' ><b>" + toast + "</b></font>"),
                    Toast.LENGTH_SHORT
                )
                toaster.setGravity(Gravity.CENTER or Gravity.CENTER, 120, -220)

                val toastView: View? = toaster.getView()
                if (toastView != null) {
                    toastView.setBackgroundResource(R.drawable.toast_color)
                }
                toaster.show()
            }


            if (name.toString() == name.isEmpty().toString() || name.isBlank()) {
                toast("You must enter a name!")
            } else if (name.isDigitsOnly()) {
                toast("Name cannot only be a digit!")
            } else if (name.length <= 2) {
                toast("Name must be longer than that!")
            } else {
                (activity as LoginActivity).goToTwistFragment()
            }
        }
    }
}

