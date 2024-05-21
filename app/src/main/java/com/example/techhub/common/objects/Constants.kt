package com.example.techhub.common.objects

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal
    */
    const val BASE_URL = "http://10.18.33.73:8080/api/"

    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"

    fun getGitHubUrl(iconName: String): String {
        return "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"
    }
}