package com.example.lab_pers_1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)

        val usernameValue = sharedPreference.getString("PREF_USERNAME", "")
        Log.d("SHARE_PREF_AULA","Found shared pref: $usernameValue")

        if(usernameValue!!.isNotEmpty()){
            showWelcome(usernameValue)
        }
    }

    private fun showWelcome(usernameValue: String?) {
        findViewById<TextView>(R.id.tv1).text = "Welcome, $usernameValue"
        findViewById<EditText>(R.id.et1).isEnabled = false
        val button = findViewById<Button>(R.id.btn1)

        button.text="Logout"
        button.setOnClickListener {
            logout(it)
        }
    }


    fun login(view: View) {
        val editTextUsername = findViewById<EditText>(R.id.et1)
        if(editTextUsername.text.isNotEmpty()){
            val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
            sharedPreference.edit().putString("PREF_USERNAME", editTextUsername.text.toString()).apply()
        }

        showWelcome(editTextUsername.text.toString())
        Log.d("SHARE_PREF_AULA","Found shared pref: ${editTextUsername.text}")
    }

    private fun logout(view: View) {
        val sharedPreference: SharedPreferences = getSharedPreferences("FILE_1", Context.MODE_PRIVATE)
        sharedPreference.edit().clear().apply()

        showLogin()

        Log.d("SHARE_PREF_AULA","Found shared pref: ")

    }

    private fun showLogin() {
        findViewById<TextView>(R.id.tv1).text = "Login"
        findViewById<EditText>(R.id.et1).isEnabled = true
        val button = findViewById<Button>(R.id.btn1)

        button.text="Login"
        button.setOnClickListener {
            login(it)
        }
    }
}