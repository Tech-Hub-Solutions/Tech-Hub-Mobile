/*
* 1. Começar criando estrutura de diretórios:
*   - common
*       📄Constants.kt - Object
*       📄Resource.kt - Sealed Class
*       📂 enums
*       📄 Screen - Sealed Class
*   - presentation
*       📂 feature1
*           📂 components
*       📂 feature2
*           📂 components
*       📂 ui.theme
*       📄 MainActivity.kt
*   - domain
*       📂 model - Data classes (classes com apenas os dados que serão renderizados no front)
*       📂 repository
*       📂 usec_ase
*           📂 feature1
*           📂 feature2
*   - data
*       📂 remote
*           📂 dto - Data classes  (classes com os dados que serão recebidos do back)
*           📄 UsuarioApi - Interface
*       📂 repository
*
*   - di
*
* 2. Cria a interface de conexão com a API
*   Esta conterá os métodos do Retrofit p/ requisições HTTP
*   Deve ser contextual e conter apenas os métodos necessários para a feature
*   Exemplo: UsuarioApi.kt
*
* 3. Cria as classes de Data Transfer Object (DTO)
*   Estas classes conterão os dados brutos que serão recebidos do back
*   Dentro delas pode-se criar funções de extensão para mapear os dados para as classes de modelo
*   Exemplo: fun CoinDto.toCoin(): Coin {
*       id = id,
*       name = name
*   }
*
* 4. Criar a classe de model
*   Esta classe conterá apenas os dados que serão renderizados no front
*   Será o retorno das funções dentreo da DTO
*   Exemplo: Coin.kt
*
* 5. Criar a Interface de repositório dentro do Domain
*   Esta conterá os métodos que serão chamados pela ViewModel
*   Essa abordagem ajuda no teste unitário, pois é possível criar um mock/stub do repositório
*   ViewModel é quem chama o repositório e serve para fazer a ponte entre a UI e o repositório da camada de dados
*   Exemplo: CoinRepository.kt
*
* 6. Criar a Interface de repositório dentro da camada de dados
*   Esta conterá as implementações dos métodos do repositório do Domain
*   Isso tem como benefício a separação de responsabilidades e a possibilidade de trocar a fonte de dados sem alterar a UI
*   Exemplo: UsuarioRepositoryImpl.kt
    *   class UsuarioRepositoryImpl @Inject constructor(
        private val api: UsuarioApi
        ) : UsuarioRepository {
            override suspend fun login(usuario: UsuarioLoginData): UsuarioTokenData {
                return api.loginUser(usuario)
            }
        }
*
* 7. Criar os user_cases
*   Estes conterão a lógica de negócio da aplicação
*   São responsáveis por orquestrar as chamadas ao repositório
*   Usuarão o repositório para fazer as chamadas ao back e retornar os dados para a ViewModel
*
*   Sobrecreva o operador invoke para chamar o método do repositório
*   Exemplo: operador fun invoke(): Flow<Resource<UsuarioTokenData>>
*   Use Flow (kotlinx.coroutines.flow.Flow), pois ele é assíncrono e pode ser observado
*   Use o Resource para encapsular o retorno da chamada ao back
*   Utilize try/catch ou o Either para tratar os erros e exceções, emitindo (emit()) o Resource específico
*
* 8. Criar a Injeção de Dependência no diretório di
*   Crie um módulo para cada camada (data, domain, presentation)
*   Ela é responsável por prover as instâncias das classes que serão injetadas
*   O propósito é desacoplar as classes e facilitar a troca de implementação
*   Não precisando alterar a classe que usa a dependência de forma hard-coded
*   Exemplo: AppModule.kt
*
*   Use a anotação @Module para definir a classe como um módulo
*   Use a anotação @InstallIn(SingletonComponent::class) para definir o escopo da injeção para a duração da vida do app
*   Use a anotação @Provides para prover a instância da classe
*   Use a anotação @Singleton para definir que a instância será única durante toda a vida do app
*   Crie funções para prover as instâncias das classes, usando a Interface API como parâmetro e retornando a implementação
*   Exemplo: fun provideUsuarioRepository(api: UsuarioApi): UsuarioRepository
*
* 8. Crie uma Application do app na raiz do projeto
*   Esta classe é responsável por inicializar o Hilt na aplicação inteira
*   Use a anotação @HiltAndroidApp para definir a classe como a classe geral de aplicação
*   Exemplo:
*       @HiltAndroidApp
*       class TechHubApplication : Application()
*
*   Precisa declarar no AndroidManifest.xml dentro da tag <application>
*   Exemplo: android:name=".TechHubApplication"
*
* 9. Crie a ViewModel dentro do diretório de presentation
*   Toda a lógica de negócio está sendo feita no user_case
*   A View Model terá a principal responsabilidade de manter o estado da UI, mesmo após mudança de configurações
*       do dispositivo (e.g. rotação, idioma, tema)
*   Ela é quem chama os user_cases e observa os dados que serão renderizados na UI
*   Elas também podem gerenciar os dados da UI e a lógica de negócio
*   Faça uma ViewModel para cada tela/ Activity/ Fragment
*   No constructor, injete o user_case que será utilizado ao invés do repository
*
*
*
* */
