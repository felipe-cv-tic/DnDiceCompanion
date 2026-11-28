package com.dsa.dndicecompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ErrorsLogin(
    val usuari: Int? = null,
    val contrasenya: Int? = null
)

class LoginViewModel : ViewModel() {

    private val _errors = MutableLiveData(ErrorsLogin())
    val errors: LiveData<ErrorsLogin> = _errors

    private val _loginCorrecte = MutableLiveData(false)
    val loginCorrecte: LiveData<Boolean> = _loginCorrecte

    fun iniciarSessio(usuari: String, contrasenya: String) {
        val nousErrors = ErrorsLogin(
            usuari = validarUsuari(usuari.trim()),
            contrasenya = validarContrasenya(contrasenya)
        )
        _errors.value = nousErrors
        _loginCorrecte.value = nousErrors.usuari == null && nousErrors.contrasenya == null
    }

    private fun validarUsuari(usuari: String): Int? =
        if (usuari.isEmpty()) R.string.error_camp_buit else null

    private fun validarContrasenya(contrasenya: String): Int? = when {
        contrasenya.isEmpty() -> R.string.error_camp_buit
        !contrasenya.esContrasenyaValida -> R.string.error_contrasenya
        else -> null
    }
}
