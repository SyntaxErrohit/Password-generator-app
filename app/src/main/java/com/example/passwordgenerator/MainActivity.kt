package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateButton = findViewById<Button>(R.id.generate)
        val passwd = findViewById<TextView>(R.id.passwd)
        val lengthText = findViewById<EditText>(R.id.length)
        val numberRadio = findViewById<CheckBox>(R.id.has_numbers)
        val splRadio = findViewById<CheckBox>(R.id.has_spl)
        generateButton.setOnClickListener {
            val view = this.currentFocus

            if (view != null) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                val length = lengthText.text.toString().toInt()
                val numbers = numberRadio.isChecked
                val splChars = splRadio.isChecked
                val alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                val digits = "0123456789"
                val punc = """!"#${'$'}%&'()*+,-./:;<=>?@[\]^_`{|}~"""
                var characters = alpha
                if (numbers) characters += digits
                if (splChars) characters += punc

                var password = ""
                var metCondn = false
                var hasNum = false
                var hasSpl = false
                while (!metCondn or (password.length < length)) {
                    var c = characters.random()
                    while (c in password) {
                        c = characters.random()
                    }
                    password += c

                    if (c in digits) hasNum = true
                    if (c in punc) hasSpl = true

                    metCondn = true
                    if (numbers) metCondn = hasNum
                    if (splChars) metCondn = metCondn and hasSpl
                }
                passwd.text = password
            }
        }
    }

}