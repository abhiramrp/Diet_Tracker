package com.example.diet_tracker

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics

    private lateinit var auth: FirebaseAuth

    private lateinit var loginButton : Button
    private lateinit var registerButton : Button
    private lateinit var logoutButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        logoutButton = findViewById(R.id.logoutButton)
        registerButton = findViewById(R.id.registerButton)

        val user = auth.currentUser

        if(user == null) {
            loginButton.visibility = View.VISIBLE
            registerButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE

            loginButton.setOnClickListener{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            registerButton.setOnClickListener{
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }


        } else {
            loginButton.visibility = View.GONE
            registerButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE
            Log.d(TAG, "user logged in")

            logoutButton.setOnClickListener {
                Log.d(TAG, "user signed out")

                auth.signOut()

                Toast.makeText(baseContext, "Logged out!", Toast.LENGTH_SHORT).show()

                finish()
                startActivity(this.intent);
            }

        }

    }


}
