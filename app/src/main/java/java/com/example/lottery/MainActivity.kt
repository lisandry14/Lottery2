package com.example.lottery

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var timeText: TextView
    private lateinit var chooseLotteryBtn: Button
    private val uiHandler = Handler(Looper.getMainLooper())
    private val timeFormat = SimpleDateFormat("h:mm:ss a", Locale.getDefault())

    private val tick = object : Runnable {
        override fun run() {
            timeText.text = timeFormat.format(Date())
            uiHandler.postDelayed(this, 1000L)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeText = findViewById(R.id.timeText)
        chooseLotteryBtn = findViewById(R.id.chooseLotteryBtn)

        // Mostrar hora en vivo
        uiHandler.post(tick)

        // Abrir diálogo para elegir sorteos
        chooseLotteryBtn.setOnClickListener {
            showLotteriesDialog()
        }
    }

    private fun showLotteriesDialog() {
        // Loterías base (puedes mover esto a strings.xml si quieres)
        val lotteries = arrayOf(
            "Anguila AM", "Anguila 12PM", "Anguila 5PM", "Anguila 8PM",
            "LA SUERTE 12:25 PM", "LA SUERTE 5:50 PM",
            "LA PRIMERA DÍA", "LA PRIMERA NOCHE",
            "LOTEDOM", "GANA MÁS", "LOTEKA", "NACIONAL",
            "QUINIELA PALE (LEIDSA)",
            "SUPER PALE NOCHE", "SUPER PALE TARDE", "SUPER PALE NY PM",
            "SUPER PALE NY AM", "SUPER PALE NY-FL AM",
            "King Lottery AM", "King Lottery PM",
            "GEORGIA MID AM", "GEORGIA EVENING", "GEORGIA NIGHT",
            "MARYLAND MIDDAY", "MARYLAND EVENING",
            "FLORIDA AM", "FLORIDA PM",
            "NEW YORK AM", "NEW YORK PM",
            "CONNECTICUT AM", "CONNECTICUT PM",
            "NEW JERSEY AM", "NEW JERSEY PM",
            "PENN MIDDAY", "PENN EVENING"
        )

        AlertDialog.Builder(this)
            .setTitle("Elegir sorteos")
            .setItems(lotteries) { dialog, which ->
                chooseLotteryBtn.text = lotteries[which].uppercase(Locale.getDefault())
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        uiHandler.removeCallbacks(tick)
    }
}
  
