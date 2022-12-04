package com.example.diet_tracker

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var email: EditText
    private lateinit var password : EditText

    private lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        auth = Firebase.auth

        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)

        loginButton = findViewById(R.id.loginSubmit)

        loginButton.setOnClickListener{
            var fieldResult: String = checkFields()

            if(fieldResult.isEmpty()) {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }

                    }
            } else {
                Toast.makeText(this, fieldResult, Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun checkFields(): String {
        var e = email.toString()
        var p = password.toString()

        if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            return "Fields are empty"
        }

        return ""
    }
}