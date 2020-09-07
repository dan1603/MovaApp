package com.kalashnyk.denys.movaapp.utils.logger

import android.util.Log
import kotlin.reflect.KClass

object Logger {

    private const val PREFIX_START = "["
    private const val PREFIX_END = "]"

    private fun getTag(callerClass: Class<*>): String = callerClass.simpleName

    private fun getTag(callerClass: KClass<*>): String = callerClass.simpleName.toString()

    private fun getPrefix(prefix: String): String = "$PREFIX_START$prefix$PREFIX_END "

    fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun d(callerClass: Class<*>, message: Any) {
        Log.d(getTag(callerClass), message.toString())
    }

    fun d(callerClass: KClass<*>, message: Any) {
        Log.d(getTag(callerClass), message.toString())
    }

    fun d(callerClass: Class<*>, prefix: String, message: Any) {
        Log.d(getTag(callerClass), getPrefix(prefix) + message.toString())
    }

    fun d(callerClass: KClass<*>, prefix: String, message: Any) {
        Log.d(getTag(callerClass), getPrefix(prefix) + message.toString())
    }

    fun e(callerClass: Class<*>, message: Any) {
        Log.e(getTag(callerClass), message.toString())
    }

    fun e(callerClass: KClass<*>, message: Any) {
        Log.e(getTag(callerClass), message.toString())
    }

    fun e(callerClass: Class<*>, t: Throwable) {
        Log.e(getTag(callerClass), t.message.toString())
        t.printStackTrace()
    }

    fun e(callerClass: KClass<*>, t: Throwable) {
        Log.e(getTag(callerClass), t.message.toString())
        t.printStackTrace()
    }

    fun <E: java.lang.Exception?> e(callerClass: Class<*>, e: E) {
        Log.e(getTag(callerClass), e?.message.toString())
        e?.printStackTrace()
    }

    fun <E: java.lang.Exception?> e(callerClass: KClass<*>, e: E) {
        Log.e(getTag(callerClass), e?.message.toString())
        e?.printStackTrace()
    }

    fun <E: java.lang.Exception?> e(callerClass: Class<*>, prefix: String, e: E) {
        Log.e(getTag(callerClass), getPrefix(prefix) + e?.message.toString())
        e?.printStackTrace()
    }

    fun <E: java.lang.Exception?> e(callerClass: KClass<*>, prefix: String, e: E) {
        Log.e(getTag(callerClass), getPrefix(prefix) + e?.message.toString())
        e?.printStackTrace()
    }

    fun e(callerClass: Class<*>, prefix: String, t: Throwable) {
        Log.e(getTag(callerClass), getPrefix(prefix) + t.message.toString())
        t.printStackTrace()
    }

    fun e(callerClass: KClass<*>, prefix: String, t: Throwable) {
        Log.e(getTag(callerClass), getPrefix(prefix) + t.message.toString())
        t.printStackTrace()
    }

    fun e(callerClass: Class<*>, prefix: String, message: Any) {
        Log.e(getTag(callerClass), getPrefix(prefix) + message.toString())
    }

    fun e(callerClass: KClass<*>, prefix: String, message: Any) {
        Log.e(getTag(callerClass), getPrefix(prefix) + message.toString())
    }

    fun i(callerClass: Class<*>, message: Any) {
        Log.i(getTag(callerClass), message.toString())
    }

    fun i(callerClass: KClass<*>, message: Any) {
        Log.i(getTag(callerClass), message.toString())
    }
}