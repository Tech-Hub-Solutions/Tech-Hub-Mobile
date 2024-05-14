package com.example.techhub.common.objects

object Constants {
    private const val IP = "192.168.15.60:8080"
    const val BASE_URL = "http://$IP/api/"
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"

    fun getGitHubUrl(iconName: String): String {
        return "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"
    }
}