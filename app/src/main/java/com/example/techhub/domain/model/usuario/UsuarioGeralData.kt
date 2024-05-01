package com.example.techhub.domain.model.usuario

import com.example.techhub.domain.model.perfil.PerfilDetalhadoData

data class UsuarioGeralData(
    val usuarioDetalhado: PerfilDetalhadoData,
    val perfilDetalhado: PerfilDetalhadoData,
)
