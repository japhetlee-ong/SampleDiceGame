package com.auf.cea.diceroller2

import android.content.Context
import kotlin.random.Random

class DiceHelper {

    companion object{
        private fun getDice(): Int{
            return Random.nextInt(1,7)
        }

        fun rollDice(): IntArray{
            return intArrayOf(
                getDice(),
                getDice(),
                getDice(),
                getDice(),
                getDice(),
                getDice()
            )
        }

        fun evaluateDice(context: Context,dice: IntArray): String{

            val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

            val result = mutableMapOf(
                Pair(1,0),
                Pair(2,0),
                Pair(3,0),
                Pair(4,0),
                Pair(5,0),
                Pair(6,0),
            )

            for(i in dice.indices){
                val currentCount = result.getOrElse(dice[i]){0}
                result[dice[i]] = currentCount + 1
            }

            var eval = ""
            val editor = sharedPreferences.edit()

            when{
                result.containsValue(5) ->{
                    eval = "Five of a kind"
                    val fiveStats = sharedPreferences.getInt(FIVE_KIND,0)
                    editor.putInt(FIVE_KIND, fiveStats + 1)

                }
                result.containsValue(4) ->{
                    eval = "Four of a kind"
                    val fourStats = sharedPreferences.getInt(FOUR_KIND,0)
                    editor.putInt(FOUR_KIND, fourStats + 1)
                }
                isFullHouse(result) ->{
                    eval = "Full House"
                    val fullStats = sharedPreferences.getInt(FULL_HOUSE,0)
                    editor.putInt(FULL_HOUSE, fullStats + 1)
                }
                isStraight(dice) -> {
                    eval = "Straight"
                    val straightStats = sharedPreferences.getInt(STRAIGHT,0)
                    editor.putInt(STRAIGHT,straightStats + 1)
                }
                result.containsValue(3) -> {
                    eval = "Three of a Kind"
                    val threeStats = sharedPreferences.getInt(THREE_KIND,0)
                    editor.putInt(THREE_KIND, threeStats + 1)
                }
                is2Pairs(result.values) -> {
                    eval  = "Two pairs"
                    val twoStats = sharedPreferences.getInt(TWO_PAIRS,0)
                    editor.putInt(TWO_PAIRS, twoStats + 1)

                }
                result.containsValue(2) ->{
                    eval = "Pair"
                    val pairStats = sharedPreferences.getInt(PAIR,0)
                    editor.putInt(PAIR,pairStats + 1)
                }
                else ->{
                   eval = "Nothing Special"
                    val notSpecialStats = sharedPreferences.getInt(NOT_SPECIAL,0)
                    editor.putInt(NOT_SPECIAL, notSpecialStats + 1)
                }
            }

            editor.apply()
            return eval

        }

        private fun isFullHouse(result: MutableMap<Int,Int>): Boolean{
            return result.containsValue(3) && result.containsValue(2)
        }

        private fun is2Pairs(results: MutableCollection<Int>) : Boolean{
            var foundPair = false
            for(result in results){
                if(result == 2){
                    if(foundPair){
                        return true
                    }else{
                        foundPair = true
                    }
                }
            }

            return false
        }

        private fun isStraight(dice: IntArray):Boolean{
            return (dice.contains(1) || dice.contains(6)) &&
                    dice.contains(2) &&
                    dice.contains(3) &&
                    dice.contains(4) &&
                    dice.contains(5)
        }

    }

}