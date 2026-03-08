package com.example.favoritesearchapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val dictionary = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val searchButton = findViewById<Button>(R.id.searchButton)

        loadDictionary()

        searchButton.setOnClickListener {

            val word = searchInput.text.toString().lowercase()

            var result = dictionary[word]

            if (result == null) {
                for ((eng, mac) in dictionary) {
                    if (mac == word) {
                        result = eng
                        break
                    }
                }
            }

            if (result != null) {
                resultText.text = result
            } else {
                resultText.text = "Word not found"
            }
        }
    }

    private fun loadDictionary() {

        val reader = BufferedReader(
            InputStreamReader(assets.open("dictionary.txt"))
        )

        reader.forEachLine {

            val parts = it.split(",")

            if (parts.size == 2) {

                val english = parts[0].trim().lowercase()
                val macedonian = parts[1].trim().lowercase()

                dictionary[english] = macedonian
            }
        }

        reader.close()
    }
}