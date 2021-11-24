# MOVIE-API

> * API REST desenvolvida em Java com Spring Boot e JPA
> * Utilizado o banco de dados embarcado H2
> * Carrega os dados informados no arquivo movielist.csv

## 💻 Pré-requisitos
* JDK, de preferência as mais recentes (https://www.oracle.com/java/technologies/downloads/)
* IDE para execução de código Java, exemplos: Eclipse, NetBeans, etc. (https://www.eclipse.org/downloads/)
* Sistema para realizar as requisições, o mais comum é o Postman (https://www.postman.com/)

## 🚀 Execução
* Na IDE importar o projeto como Maven
* Executa-lo como aplicação Java ou Spring

> Após o servidor iniciar é possível realizar as requições

### 📝 End-points disponíveis
* ##### (GET) localhost:8080/movies
    * Retorna todos os filmes
* ##### (GET) localhost:8080/movies/min-max-interval
    * Retorna os produtores com maior e menor intervalo entre seus prêmios
* ##### (GET) localhost:8080/movies/{id}
    * Retorna um filme específico por Id
* ##### (PUT) localhost:8080/movies/{id}
    * Atualiza os dados de um filme por Id
* ##### (POST) localhost:8080/movies
    * Cria um novo filme
* ##### (DEL) localhost:8080/movies/{id}
    * Exclui um filme por Id
