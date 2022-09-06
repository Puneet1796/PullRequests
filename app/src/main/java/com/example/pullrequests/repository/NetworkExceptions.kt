package com.example.pullrequests.repository

import com.example.pullrequests.utils.ErrorMessages

sealed class NetworkExceptions(errorMessage: String) : Exception(errorMessage) {
    object Unauthorized : NetworkExceptions(ErrorMessages.UNAUTHORIZED)
    object NoInternet : NetworkExceptions(ErrorMessages.NO_INTERNET)
    object Generic : NetworkExceptions(ErrorMessages.DEFAULT_MESSAGE)
}
