package com.example.diet_tracker

import android.content.ContentValues.TAG
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

    // private lateinit var firstName: EditText
    // private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password : EditText
    private lateinit var retypePassword : EditText

    private lateinit var registerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        getUIFields()



        registerButton.setOnClickListener{

            var fieldResult: String = checkFields()

            if(fieldResult.isEmpty()) {
                auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
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

    private fun getUIFields() {
        // firstName = findViewById(R.id.registerFirstName)
        // lastName = findViewById(R.id.registerLastName)
        email = findViewById(R.id.registerEmailAddress)
        password = findViewById(R.id.registerPassword)
        retypePassword = findViewById(R.id.registerRetypePassword)

        registerButton = findViewById(R.id.registerSubmit)

    }

    private fun checkFields(): String {
        var e = email.toString()
        var p = password.toString()
        var rp = password.toString()

        if( p != rp) {
            return "Passwords don't match"
        }

        if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
            return "Fields are empty"
        }

        return ""
    }


}