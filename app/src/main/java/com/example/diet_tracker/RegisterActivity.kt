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
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var email: EditText
    private lateinit var password : EditText
    private lateinit var retypePassword : EditText

    private lateinit var registerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth


        email = findViewById(R.id.registerEmailAddress)
        password = findViewById(R.id.registerPassword)
        retypePassword = findViewById(R.id.registerRetypePassword)

        registerButton = findViewById(R.id.registerSubmit)


        registerButton.setOnClickListener{

            var fieldResult: String = checkFields()

            if(fieldResult.isEmpty()) {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(baseContext, "Registered", Toast.LENGTH_SHORT).show()
                            val user = auth.currentUser

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
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
        var e = email.text.toString()
        var p = password.text.toString()
        var rp = password.text.toString()

        if( p != rp) {
            return "Passwords don't match"
        }

        if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            return "Fields are empty"
        }


        return ""
    }


}