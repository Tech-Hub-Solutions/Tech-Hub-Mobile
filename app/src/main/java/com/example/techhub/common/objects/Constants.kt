package com.example.techhub.common.objects

object Constants {
    const val BASE_URL = "https://tech-hub.ddns.net/api/"
    const val GITHUB_BASE_URL = "https://api.github.com/"

    fun getGitHubUrl(iconName: String): String {
        return "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${iconName}/${iconName}-original.svg"
    }
}