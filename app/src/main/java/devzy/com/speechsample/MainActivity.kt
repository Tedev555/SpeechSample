package devzy.com.speechsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.speech.RecognizerIntent
import android.widget.Toast
import android.R.attr.data
import android.app.Activity
import android.app.Fragment
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.MenuItem


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById(R.id.navigation) as BottomNavigationView

        bottomNavigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: android.support.v4.app.Fragment? = null
            when (item.itemId) {
                R.id.action_item1 -> selectedFragment = SpeechToTextFragment.newInstance()
                R.id.action_item2 -> selectedFragment = TextToSpeechFragment.newInstance()
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, selectedFragment)
            transaction.commit()

            true
        })
    }
}
