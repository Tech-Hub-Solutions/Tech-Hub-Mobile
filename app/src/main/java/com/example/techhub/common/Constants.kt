package com.example.techhub.common

import com.example.techhub.domain.RetrofitService

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal
    */
    private const val IP = "192.168.15.8"
    const val BASE_URL = "http://${IP}:8080/api/"
    const val PARAM_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXRhaW5ub3ZhdGVAaG90bWFpbC5jb20iLCJpYXQiOjE3MTQyMjU2ODUsImV4cCI6MTcxNzgyNTY4NX0.MDWTx4DWLl9Eg6hzn6Zqcd97NkufujAf1UcTVEmxYkdCnAoXcFasRDXBSkrhJ_XL5Z8N0bkG7jzohqj8gamtyg"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"
}