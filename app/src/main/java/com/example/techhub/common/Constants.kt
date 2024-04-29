package com.example.techhub.common

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal
    */
    private const val IP = "172.19.32.1"
    const val BASE_URL = "http://${IP}:8080/api/"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"
}