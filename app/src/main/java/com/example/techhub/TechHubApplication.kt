package com.example.techhub

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// isso serve para o dagger-hilt saber que essa é a classe de aplicação
// e que ele deve injetar as dependências nela

@HiltAndroidApp
class TechHubApplication : Application()