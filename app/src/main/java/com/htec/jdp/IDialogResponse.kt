package com.htec.jdp

interface IDialogResponse {
    fun onDialogSubmit(username : String, password : String)
    fun onDialogCancel()
}