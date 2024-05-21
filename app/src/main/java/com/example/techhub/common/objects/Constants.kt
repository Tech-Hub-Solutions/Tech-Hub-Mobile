package com.example.techhub.common.objects

object Constants {
    private const val IP = "https://tech-hub.ddns.net/api/"
    const val BASE_URL = "https://tech-hub.ddns.net/api/"
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val EMPRESA = "empresa"
    const val FREELANCER = "freelancer"

    fun getGitHubUrl(iconName: String): String {
        return "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"
    }
}