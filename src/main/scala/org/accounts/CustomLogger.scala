package org.accounts

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.core.util.StatusPrinter
import org.slf4j.LoggerFactory

/**
  * Created by tkacz- on 25.06.16.
  */
object CustomLogger {
  def logger = LoggerFactory.getLogger("AccountServiceLogging")

  StatusPrinter.print(LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext])
}