package com.codechallenge.maxcharoccurrence.usecase

import androidx.annotation.VisibleForTesting
import java.util.*
import javax.inject.Inject

abstract class BaseUseCase<PARAM, RESULT> {
    abstract suspend fun execute(param: PARAM): RESULT

    suspend fun run(param: PARAM): RESULT = execute(param)
}

class CalculateMaxCharOccurrenceUseCase @Inject constructor() : BaseUseCase<String, String>() {
    override suspend fun execute(param: String): String {
        if (param.isEmpty()) return "Enter some text"
//        Approach 1 using hashmap
        return calculateMaxOccurrences(param)
//        Approach 2 using array
//        return getMaxOccurringChar(param.toCharArray())
    }

    //Approach 1
    @VisibleForTesting
    fun calculateMaxOccurrences(text: String): String {
        val occurrences = mutableMapOf<Char, Int>()
        val result = StringBuilder()
        val value = text.replace(" ", "")
        value.map { char ->
            val count = occurrences[char]
            count?.let {
                occurrences.put(char, count + 1)
            } ?: run {
                occurrences.put(char, 1)
            }
        }
        val maxCount = Collections.max(occurrences.values)

        occurrences.map {
            if (maxCount == it.value) {
                result.append(" ${it.key} occurred maximum ${it.value}\n")
            }
        }
        return result.toString()
    }

    //Approach 2
    private fun getMaxOccurringChar(str: CharArray): String {
        val freq = IntArray(26)
        var max = -1

        for (i in str.indices) {
            freq[str[i] - 'a']++
        }

        for (i in 0..25) {
            if (max < freq[i]) {
                max = freq[i]
            }
        }
        val strResult = StringBuilder()
        for (i in 0..25) {
            if (max == freq[i]){
                strResult.append(" ${(i + 'a'.code).toChar()} occurred maximum $max\n")
            }
        }
        return strResult.toString()
    }
}