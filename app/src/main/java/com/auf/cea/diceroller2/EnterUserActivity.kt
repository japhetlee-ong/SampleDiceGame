package com.auf.cea.diceroller2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.auf.cea.diceroller2.databinding.ActivityEnterUserBinding

class EnterUserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEnterUserBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        binding.btnDiceGame.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(binding.edtUsername.text.isEmpty()){
            binding.edtUsername.error = "Required"
            return
        }

        val editor = sharedPreferences.edit()
        editor.putString(USERNAME,binding.edtUsername.text.toString())
        editor.apply()

        val intent = Intent(this,DiceActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}