package com.arch.core.arquetype.koin

class MySimplePresenter(val repo : HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"
}