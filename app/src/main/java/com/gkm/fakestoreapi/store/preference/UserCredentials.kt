package com.gkm.fakestoreapi.store.preference

data class UserCredentials (
    val name:String,
    val pass:String,
    val saveSwitch:Boolean
)