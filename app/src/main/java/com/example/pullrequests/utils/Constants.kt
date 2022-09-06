package com.example.pullrequests.utils

const val HEADER_ACCEPT = "Accept"
const val HEADER_AUTHORIZATION = "Authorization"
const val BASE_URL = "https://api.github.com"
const val PAGE_SIZE = 10

object ErrorMessages {
    const val UNAUTHORIZED = "You are not logged in! Please login again."
    const val NO_INTERNET = "Please check your internet connected!"
    const val DEFAULT_MESSAGE = "Something went wrong! Please try again later."
}
