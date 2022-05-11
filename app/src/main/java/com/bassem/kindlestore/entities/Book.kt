package com.bassem.kindlestore.entities

import java.io.Serializable

data class Book(
    val title: String = "",
    val photo: String = "",
    var favorite: Boolean = false,
    val id: String? = null,
    val category: String? = null,
    val subCategory: String? = null,
    val link: String? = "",
    val description: String? = null,
    val author: String? = null


) : Serializable
