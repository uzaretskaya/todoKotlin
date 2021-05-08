package ru.uzaretskaya.todo.business.util;

import java.util.logging.Level
import java.util.logging.Logger

class AllExecutedMethodsLogger {
    companion object {
        private val log = Logger.getLogger(AllExecutedMethodsLogger::class.java.name)

        fun loggingMethodName(text: String) {
            println()
            log.log(Level.INFO, text)
        }
    }
}
