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
        return calculateMaxOccurrences(param)
    }

    @VisibleForTesting
    fun calculateMaxOccurrences(text: String): String {
        val occurrencesMap = mutableMapOf<Char, Int>()
        val result = StringBuilder()
        val value = text.replace(" ", "")
        value.map { char ->
            val count = occurrencesMap[char]
            count?.let {
                occurrencesMap.put(char, count + 1)
            } ?: run {
                occurrencesMap.put(char, 1)
            }
        }
        val maxCount = Collections.max(occurrencesMap.values)

        occurrencesMap.map {
            if (maxCount == it.value) {
                result.append(" ${it.key} occurred maximum ${it.value}\n")
            }
        }
        return result.toString()
    }
}