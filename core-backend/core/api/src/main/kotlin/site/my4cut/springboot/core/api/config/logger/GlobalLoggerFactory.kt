package site.my4cut.springboot.core.api.config.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.getLogger(): Logger = LoggerFactory.getLogger(T::class.java)!!
