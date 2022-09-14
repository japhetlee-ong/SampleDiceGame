package com.auf.cea.diceroller2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.auf.cea.diceroller2.databinding.ActivityDiceBinding

class DiceActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDiceBinding
    private val imageViews by lazy {
        arrayListOf(
            binding.imgDice1,
            binding.imgDice2,
            binding.imgDice3,
            binding.imgDice4,
            binding.imgDice5,
            binding.imgDice6
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRollDice.setOnClickListener(this)
        binding.btnViewStats.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_roll_dice) ->{
                val diceResult = DiceHelper.rollDice()
                updateDisplay(diceResult)
                val sResult = DiceHelper.evaluateDice(this,diceResult)
                binding.txtResults.text = sResult
            }
            (R.id.btn_view_stats) ->{
                val intent = Intent(this,UserStatsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateDisplay(intArray: IntArray){
        for(i in imageViews.indices){
            val drawableId = when(intArray[i]){
                1 -> R.drawable.die_1
                2 -> R.drawable.die_2
                3 -> R.drawable.die_3
                4 -> R.drawable.die_4
                5 -> R.drawable.die_5
                6 -> R.drawable.die_6
                else -> R.drawable.die_1
            }
            imageViews[i].setImageResource(drawableId)
        }
    }
}