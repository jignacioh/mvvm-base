package com.arch.core.arquetype.login

interface LoginInterface {

    interface Activity {

    }

    interface Model {
        fun procesarUsuario(user:String, pass:String)
    }
}