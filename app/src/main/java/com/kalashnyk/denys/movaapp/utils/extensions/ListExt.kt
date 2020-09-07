package com.kalashnyk.denys.movaapp.utils.extensions

import kotlin.random.Random

fun <E> List<E>.random(): E? = if (size > 0) get(Random.nextInt(size)) else null