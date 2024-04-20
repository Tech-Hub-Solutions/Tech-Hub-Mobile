package com.example.techhub.common

import com.example.techhub.domain.RetrofitService

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal
    */
    private const val IP = "192.168.15.7"
    const val BASE_URL = "http://${IP}:8080/api/"
    const val PARAM_TOKEN = "token"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"
}