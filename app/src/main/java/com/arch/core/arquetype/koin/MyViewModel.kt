package com.arch.core.arquetype.koin

import androidx.lifecycle.ViewModel

class MyViewModel(val repo : HelloRepository) : ViewModel() {

    fun sayHello() = "${repo.giveHello()} from $this"
}