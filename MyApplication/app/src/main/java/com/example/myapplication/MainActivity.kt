package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var randomNumber: Int = 0
    private var attempts: Int = 0
    private val maxAttempts = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val instructionTextView: TextView = findViewById(R.id.instructionTextView)
        val guessEditText: EditText = findViewById(R.id.guessEditText)
        val guessButton: Button = findViewById(R.id.guessButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val restartButton: Button = findViewById(R.id.restartButton)

        startNewGame()

        guessButton.setOnClickListener {
            val userGuess = guessEditText.text.toString().toIntOrNull()
            if (userGuess != null) {
                attempts++
                if (userGuess == randomNumber) {
                    resultTextView.text = "Parabéns! Você acertou em $attempts tentativas."
                    resultTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
                    guessButton.isEnabled = false
                    restartButton.visibility = Button.VISIBLE
                } else {
                    if (attempts >= maxAttempts) {
                        resultTextView.text = "Você usou todas as $maxAttempts tentativas. O número era $randomNumber. Novo jogo iniciado!"
                        resultTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
                        startNewGame()
                    } else {
                        val hint = if (userGuess > randomNumber) "menor" else "maior"
                        resultTextView.text = "Errado! Tente um número $hint. Tentativas restantes: ${maxAttempts - attempts}"
                        resultTextView.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    }
                }
            } else {
                resultTextView.text = "Por favor, insira um número válido."
                resultTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
        }

        restartButton.setOnClickListener {
            startNewGame()
            resultTextView.text = ""
            guessButton.isEnabled = true
            restartButton.visibility = Button.GONE
        }
    }

    private fun startNewGame() {
        randomNumber = Random.nextInt(1, 11)
        attempts = 0
    }
}