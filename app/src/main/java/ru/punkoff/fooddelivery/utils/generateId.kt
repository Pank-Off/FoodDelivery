package ru.punkoff.fooddelivery.utils

import java.util.*

val uniqueId: String
    get() = UUID.randomUUID().toString().replace("-", "")