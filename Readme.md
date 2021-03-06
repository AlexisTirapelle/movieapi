# MOVIE-API

> * API REST desenvolvida em Java com Spring Boot e JPA.
> * Utilizado o banco de dados embarcado H2.
> * JUnit com Rest Assured para teste de integração.
> * Carga inicial padrão: arquivo movielist.csv.

## 💻 Pré-requisitos
* JDK 11+ (https://www.oracle.com/java/technologies/downloads/).
* IDE para execução de código Java, exemplos: Eclipse, NetBeans, etc. (https://www.eclipse.org/downloads/).
* (Opcional) Sistema para realizar as requisições HTTP, o mais comum é o Postman (https://www.postman.com/).
  * Ou simplesmente utilizar o browser, ou ainda, apenas executar o teste de integração (descrito no final desta página).

## 🚀 Execução
* Na IDE, importar o projeto como Maven.
* IMPORTANTE: alterar o caminho do *spring.datasource.url* especificado no arquivo *application.properties* para o diretório do usuário da sua máquina.
   * Exemplo no Windows: *spring.datasource.url=jdbc:h2:file:C:/Users/Usuario/dbh2movieapi*
   * Exemplo no Linux: *spring.datasource.url=jdbc:h2:file:/home/usuario/dbh2movieapi*
* Executar o projeto como Java Application (na classe main *MovieapiApplication*) ou na pasta raiz como Spring Boot App.

> Após o servidor iniciar é possível realizar as requições.

### 📝 End-points disponíveis
* **(GET) http://localhost:8080/movies**
    * Retorna todos os filmes.
* **(GET) http://localhost:8080/movies/min-max-interval**
    * Retorna os produtores com maior e menor intervalo entre seus prêmios.
* **(GET) http://localhost:8080/movies/{id}**
    * Retorna um filme específico por Id.
* **(PUT) http://localhost:8080/movies/{id}**
    * Atualiza os dados de um filme por Id.
* **(POST) http://localhost:8080/movies**
    * Cria um novo filme.
* **(DEL) http://localhost:8080/movies/{id}**
    * Exclui um filme por Id.

## - Teste de Integração
> IPORTANTE: o servidor deve estar rodando para que possa ser executado o teste de integração.
* Executar como *JUnit Test* a classe *MovieResourceTest*.
   * Caso não seja executado o teste, por favor alterar no *RunConfiguration* da classe o *Test runner* para **JUnit4**.
* Para executar com diferentes arquivos de entradas será necessário:
    * Salvar o novo arquivo dentro da pasta raiz do projeto.
    * Alterar na classe *MovieapiApplication* dentro do método *persistirCSV* o nome do arquivo que será carregado.
    * Alterar na classe *MovieResourceTest* o retorno esperado no *body* da requisição de acordo com os novos dados.
