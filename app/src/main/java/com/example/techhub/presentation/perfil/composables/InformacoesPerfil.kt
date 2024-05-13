package com.example.techhub.presentation.perfil.composables

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.perfil.GitHubViewModel
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.avaliacao.AvaliacaoSection
import com.example.techhub.presentation.perfil.composables.informacoesAdicionais.InformacoesAdicionaisSection
import com.example.techhub.presentation.perfil.composables.projetosDesenvolvidos.ProjetosDesenvolvidos
import com.example.techhub.presentation.perfil.composables.tag.TagsSection

@Composable
fun InformacoesPerfil(
    userInfo: State<PerfilGeralDetalhadoData?>,
    isOwnProfile: Boolean,
    isEmpresa: Boolean,
    viewModel: PerfilViewModel,
    gitHubViewModel: GitHubViewModel,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        TextContainer(
            title = UiText.StringResource(
                R.string.title_experiencia,
            ).asString(context = context),
            description = userInfo.value!!.experiencia ?: UiText.StringResource(
                R.string.text_sem_descricao,
            ).asString(context = context)
        )

        TextContainer(
            title = if (isEmpresa) UiText.StringResource(
                R.string.title_quem_procuramos,
            ).asString(context = context) else
                UiText.StringResource(
                    R.string.title_sobre_mim,
                ).asString(context = context),
            description = userInfo.value!!.sobreMim ?: UiText.StringResource(
                R.string.text_sem_descricao,
            ).asString(context = context)
        )

        userInfo.value!!.flags?.filter {
            it.categoria == "soft-skill"
        }?.let {
            TagsSection(
                title = if (isEmpresa) UiText.StringResource(
                    R.string.text_valores,
                ).asString(context = context)
                else UiText.StringResource(
                    R.string.text_soft_skills,
                ).asString(context = context),
                flags = it
            )
        }

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        if (!isEmpresa) {
            userInfo.value!!.flags?.filter {
                it.categoria == "hard-skill"
            }?.let {
                TagsSection(
                    title = UiText.StringResource(
                        R.string.text_hard_skills,
                    ).asString(context = context),
                    flags = it
                )
            }
            Divider(
                color = Color.LightGray.copy(alpha = 0.4f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }


        if (!isEmpresa) {
            Column(modifier = Modifier.fillMaxSize()) {
                SectionTitle(
                    title = UiText.StringResource(
                        R.string.title_projetos_desenvolvidos,
                    ).asString(context = context), isCentered = false
                )
                ProjetosDesenvolvidos(
                    context = context,
                    nomeGitHub = userInfo.value!!.nomeGithub,
                    viewModel = gitHubViewModel
                )
            }

            Divider(
                color = Color.LightGray.copy(alpha = 0.4f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        // Seção de Avaliações
        AvaliacaoSection(
            usuarioId = userInfo.value!!.idUsuario!!,
            viewModel = viewModel,
            context = context
        )

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // Seção de Informações Adicionais
        InformacoesAdicionaisSection(
            isOwnProfile = isOwnProfile,
            usuarioId = userInfo.value!!.idUsuario!!,
            empresasInteressadas = userInfo.value!!.qtdFavoritos?.toInt() ?: 0,
            recomendacoes = userInfo.value!!.qtdRecomendacoes?.toInt() ?: 0,
            isRecomendado = userInfo.value!!.isRecomendado ?: false,
            viewModel = viewModel,
            context = context
        )
    }
}