package com.example.techhub.presentation.editarUsuario.composables

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.AboutMeTextField
import com.example.techhub.common.composable.DescriptionTextField
import com.example.techhub.common.composable.ExperienceTextField
import com.example.techhub.common.composable.GitHubTextField
import com.example.techhub.common.composable.LinkedinTextField
import com.example.techhub.common.composable.PriceTextField
import com.example.techhub.common.composable.ProgressButton
import com.example.techhub.common.composable.SkillsSelectedField
import com.example.techhub.common.composable.SkillsDropDownMenu
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.perfil.PerfilCadastroData
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.editarUsuario.EditarUsuarioViewModel
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@SuppressLint("MutableCollectionMutableState")
@Composable
fun EditarUsuarioView(
    userInfo: PerfilGeralDetalhadoData,
    viewModel: EditarUsuarioViewModel = EditarUsuarioViewModel(),
) {
    val context = LocalContext.current
    var descricao by remember { mutableStateOf(userInfo.descricao) }
    var experiencia by remember { mutableStateOf(userInfo.experiencia) }
    var sobreMim by remember { mutableStateOf(userInfo.sobreMim) }
    var preco by remember { mutableStateOf(userInfo.precoMedio.toString()) }
    var linkLinkedin by remember { mutableStateOf(userInfo.linkLinkedin) }
    var linkGithub by remember { mutableStateOf(userInfo.linkGithub) }
    var nomeGithub by remember { mutableStateOf(userInfo.nomeGithub) }
    val softSkillList = remember {
        mutableStateOf(userInfo.flags!!.filter {
            it.categoria == "soft-skill"
        }.toMutableStateList())
    }
    val hardSkillList = remember {
        mutableStateOf(userInfo.flags!!.filter {
            it.categoria == "hard-skill"
        }.toMutableStateList())
    }
    val isLoading = viewModel.isLoading.observeAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = PerfilActivity::class.java,
                context = context,
                title = UiText.StringResource(
                    R.string.btn_text_edit_perfil
                ).asString(context = context),
            )
        },
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.getFlags()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Spacer(modifier = Modifier.padding(0.dp))

                DescriptionTextField(
                    initialValue = userInfo.descricao ?: "",
                    onValueChanged = { descricao = it },
                    context = context,
                    modifier = Modifier.focusRequester(focusRequester)
                )

                Spacer(modifier = Modifier.padding(0.dp))

                ExperienceTextField(
                    initialValue = userInfo.experiencia ?: "",
                    onValueChanged = { experiencia = it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(0.dp))

                AboutMeTextField(
                    initialValue = userInfo.sobreMim ?: "",
                    onValueChanged = { sobreMim = it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(0.dp))

                PriceTextField(
                    initialValue = userInfo.precoMedio.toString(),
                    onValueChanged = { preco = if (it.isNullOrEmpty()) "0.00" else it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Row {
                    Text(
                        text = UiText.StringResource(
                            R.string.text_formas_contato
                        ).asString(context = context),
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Justify,
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))

                LinkedinTextField(
                    initialValue = userInfo.linkLinkedin ?: "",
                    onValueChanged = { linkLinkedin = it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(2.dp))

                GitHubTextField(
                    label = UiText.StringResource(
                        R.string.text_link_github
                    ).asString(context = context),
                    initialValue = userInfo.linkGithub ?: "",
                    onValueChanged = { linkGithub = it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(2.dp))

                if (userInfo.funcao == UsuarioFuncao.FREELANCER) {
                    GitHubTextField(
                        label = UiText.StringResource(
                            R.string.text_nome_github
                        ).asString(context = context),
                        initialValue = userInfo.nomeGithub ?: "",
                        onValueChanged = { nomeGithub = it },
                        context = context
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                }

                Row {
                    val texto =
                        if (userInfo.funcao == UsuarioFuncao.FREELANCER)
                            UiText.StringResource(
                                R.string.text_soft_skills,
                            ).asString(context = context)
                        else
                            UiText.StringResource(
                                R.string.text_valores,
                            ).asString(context = context)
                    Text(
                        text = texto,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Justify,
                    )
                }
                SkillsDropDownMenu(
                    viewModel = viewModel,
                    categoria = "soft-skill",
                    softSkillList.value
                )
                SkillsSelectedField(softSkillList.value, context = context)

                Spacer(modifier = Modifier.padding(2.dp))

                if (userInfo.funcao == UsuarioFuncao.FREELANCER) {
                    Row {
                        Text(
                            text = UiText.StringResource(
                                R.string.text_hard_skills,
                            ).asString(context = context),
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Justify,
                        )
                    }
                    SkillsDropDownMenu(
                        viewModel = viewModel,
                        categoria = "hard-skill",
                        hardSkillList.value
                    )
                    SkillsSelectedField(hardSkillList.value, context = context)
                    Spacer(modifier = Modifier.padding(2.dp))
                }

                ProgressButton(
                    onClick = {
                        val skills = mutableListOf<Int>()
                        skills.addAll(softSkillList.value.map { it.id!! })
                        skills.addAll(hardSkillList.value.map { it.id!! })
                        val perfilCadastroData = PerfilCadastroData(
                            sobreMim = sobreMim,
                            experiencia = experiencia,
                            descricao = descricao,
                            precoMedio = preco.toDouble(),
                            nomeGithub = nomeGithub,
                            linkGithub = linkGithub,
                            linkLinkedin = linkLinkedin,
                            flagsId = skills
                        )
                        viewModel.updateUserInfo(context, perfilCadastroData)
                    },
                    text = UiText.StringResource(
                        R.string.btn_text_salvar,
                    ).asString(context = context),
                    backgroundColor = Color(PrimaryBlue.value),
                    height = (60),
                    width = (385),
                    padding = (0),
                    isLoading = isLoading,
                )

                Spacer(modifier = Modifier.padding(6.dp))

                ElevatedButton(
                    onClick = {
                        val extras = Bundle()
                        extras.putInt("id", CurrentUser.userProfile!!.id!!)
                        startNewActivity(context, PerfilActivity::class.java, extras)
                    },
                    modifier = Modifier
                        .width(385.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(PrimaryBlue.value),
                    ),
                    border = BorderStroke(
                        1.dp, Color(PrimaryBlue.value)
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = UiText.StringResource(
                            R.string.btn_text_cancelar,
                        ).asString(context = context),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                }
            }
        }
    }
}


