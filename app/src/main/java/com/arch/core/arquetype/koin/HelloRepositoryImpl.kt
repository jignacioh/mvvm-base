package com.arch.core.arquetype.koin

class HelloRepositoryImpl : HelloRepository{
    override fun userText() = "Nombre de Usuario"

    override fun passText() = "Contraseña"

    override fun buttonText() = "Iniciar Sesion"

    override fun giveHello() = "Hello Koin"
}


interface HelloRepository {
    fun giveHello() : String
    fun userText() : String
    fun passText() : String
    fun buttonText() : String
}