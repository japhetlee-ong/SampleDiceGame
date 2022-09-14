package com.auf.cea.diceroller2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.auf.cea.diceroller2.databinding.ActivityUserStatsBinding

class UserStatsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserStatsBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

        binding.txtUsername.text = String.format("Username: %s", sharedPreferences.getString(USERNAME,""))
        binding.txtFiveRolled.text = String.format("5 of a kind rolled: %s",sharedPreferences.getInt(
            FIVE_KIND,0))
        binding.txtFourRolled.text = String.format("4 of a kind rolled: %s",sharedPreferences.getInt(
            FOUR_KIND,0))
        binding.txtFullHouse.text = String.format("Full house rolled: %s",sharedPreferences.getInt(
            FULL_HOUSE,0))
        binding.txtStraight.text = String.format("Straight rolled: %s",sharedPreferences.getInt(
            STRAIGHT,0))
        binding.txtThreeRolled.text = String.format("3 of a kind rolled: %s",sharedPreferences.getInt(
            THREE_KIND,0))
        binding.txtTwoPairs.text = String.format("2 pairs rolled: %s",sharedPreferences.getInt(
            TWO_PAIRS,0))
        binding.txtPair.text = String.format("Pair rolled: %s",sharedPreferences.getInt(
            PAIR,0))
        binding.txtNothingSpecial.text = String.format("Nothing special rolled: %s",sharedPreferences.getInt(
            NOT_SPECIAL,0))

        binding.btnResetStats.setOnClickListener(this)
        binding.btnGoBack.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_reset_stats)->{
                sharedPreferences.edit().clear().apply()
                val intent = Intent(this,SplashScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            (R.id.btn_go_back) ->{
                finish()
            }
        }
    }
}