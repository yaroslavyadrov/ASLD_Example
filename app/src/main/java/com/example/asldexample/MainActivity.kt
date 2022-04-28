package com.example.asldexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.asldexample.view.AccountImageView
import com.example.asldexample.view.AccountViewState
import com.example.asldexample.view.FancyImageView
import com.example.asldexample.view.FancyViewState

class MainActivity : AppCompatActivity() {
    lateinit var accountImage: AccountImageView
    lateinit var fancyImage: FancyImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val walletRadio = findViewById<RadioButton>(R.id.walletRadio)
        val balanceRadio = findViewById<RadioButton>(R.id.balanceRadio)
        val addCardRadio = findViewById<RadioButton>(R.id.addCardRadio)
        val addRadio = findViewById<RadioButton>(R.id.addRadio)
        val forwardRadio = findViewById<RadioButton>(R.id.forwardRadio)
        accountImage = findViewById(R.id.accountImage)
        fancyImage = findViewById(R.id.fancyImage)
        walletRadio.setOnClickListener { onCommonRadioClick(it) }
        balanceRadio.setOnClickListener { onCommonRadioClick(it) }
        addCardRadio.setOnClickListener { onCommonRadioClick(it) }
        addRadio.setOnClickListener { onFancyRadioClick(it) }
        forwardRadio.setOnClickListener { onFancyRadioClick(it) }
    }

    private fun onCommonRadioClick(view: View) {
        val state = when (view.id) {
            R.id.walletRadio -> AccountViewState.WALLET
            R.id.balanceRadio -> AccountViewState.BALANCE
            R.id.addCardRadio -> AccountViewState.ADD_CARD
            else -> AccountViewState.NOTHING
        }
        accountImage.status = state
    }

    private fun onFancyRadioClick(view: View) {
        when (view.id) {
            R.id.addRadio -> FancyViewState.ADD
            R.id.forwardRadio -> FancyViewState.FORWARD
            else -> null
        }?.let {
            fancyImage.status = it
        }
    }
}
