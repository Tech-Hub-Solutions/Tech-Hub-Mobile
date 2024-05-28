package com.example.techhub.common.objects

object Constants {
    /* TODO - Alterar o endereço de IP para o endereço do servidor
    * ⚠️ sempre trocar o IP para o IPv4 da sua máquina que aparece quando executa "ipconfig" no terminal, caso for Windows
    *
    * ⚠️ caso estiver com a EC2 ligada, descomentar a linha 13 que possui o "https://tech-hub.ddns.net/api/"
    */

//    private const val IP = "10.0.0.103"
//    const val BASE_URL = "http://$IP:8080/api/"

     const val BASE_URL = "https://tech-hub.ddns.net/api/"
    const val GITHUB_BASE_URL = "https://api.github.com/"

    fun getGitHubUrl(iconName: String): String {
        return "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"
    }
}