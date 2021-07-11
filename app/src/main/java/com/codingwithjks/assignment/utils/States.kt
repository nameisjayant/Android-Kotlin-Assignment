package com.codingwithjks.assignment.utils

import com.codingwithjks.assignment.data.model.Medicine

sealed class States{

    class Success(val data:Medicine) : States()
    class Failure(val msg:Throwable) : States()
    object Empty : States()
}
