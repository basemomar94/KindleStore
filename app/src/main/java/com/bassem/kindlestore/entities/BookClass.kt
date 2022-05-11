package com.bassem.kindlestore.entities

data class BookClass(
    val title: String = "",
    val photo: String = "",
    var favorite: Boolean = false,
    val id: String? = null,
    val category: String? = null,
    val subCategory: String? = null,
    val link: String? = null


)
