package com.dsa.dndicecompanion

val String.esEmailValid: Boolean
    get() = this.contains('@')

val String.esTelefonValid: Boolean
    get() = this.length == 9 && this.all { it.isDigit() }

val String.esContrasenyaValida: Boolean
    get() = this.length >= 8 &&
            this.any { it.isUpperCase() } &&
            this.any { it.isLowerCase() } &&
            this.any { !it.isLetter() && !it.isWhitespace() }
