package com.example.loginregister

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.util.Log

import com.google.android.gms.tasks.Task

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.auth.EmailAuthProvider

import com.google.firebase.auth.AuthCredential

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {


    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        registerListener()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        buttonSubmit = findViewById(R.id.button)

    }

    private fun registerListener() {

        buttonSubmit.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password2 = editTextPassword2.text.toString()

            if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "შეავსეთ ველები!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != password2) {
                Toast.makeText(this, "პაროლები ერთმანეთს უნდა ემთხვეოდეს!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (!email.contains("@") or !email.contains(".")) {
                Toast.makeText(this, "მეილი არასწორადაა შეყვანილი!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password.length < 9) {
                Toast.makeText(this, "პაროლი უნდა იყოს მინიმუმ 9 სიმბოლო!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }else if (email.length<10) {
                Toast.makeText(this, "მეილი არასწორადაა შეყვანილი!", Toast.LENGTH_SHORT).show()
            }



            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "თქვენ წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }



        }



    }





}


