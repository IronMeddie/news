package com.example.test1.ui.adapters


interface HolderView {
    companion object{
        val HEADER :Int
        get() = 0

        val CONTENT : Int
        get() = 1

        val HEADER_Favorite :Int
            get() = 3

    }
}

