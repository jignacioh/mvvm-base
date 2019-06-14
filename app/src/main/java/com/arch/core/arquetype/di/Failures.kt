package com.arch.core.arquetype.di

sealed class GetTasksFailure {
    class NetworkConnection: GetTasksFailure()
}


sealed class GetLoginFailure{
    class NetworkConnection: GetLoginFailure()
}