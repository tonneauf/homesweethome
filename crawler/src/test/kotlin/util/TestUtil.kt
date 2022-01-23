package util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise

fun <T> testAsync(body: suspend CoroutineScope.() -> T): dynamic = MainScope().promise(block = body)