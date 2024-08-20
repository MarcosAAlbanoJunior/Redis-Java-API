# Projeto Spring Boot com Redis e MySQL utilizando Docker Compose

Este projeto é uma aplicação Spring Boot que utiliza MySQL como banco de dados e Redis como cache. O projeto é configurado para ser executado em containers Docker utilizando `docker-compose` ou `podman-compose`.

## Pré-requisitos

- [Docker](https://www.docker.com/get-started) ou [Podman](https://podman.io/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/) ou [Podman Compose](https://github.com/containers/podman-compose)

### Resumo do Cache Redis

O Redis atua como uma camada de cache neste projeto para otimizar as
operações de leitura. Ele armazena temporariamente os resultados das
consultas ao MySQL, diminuindo os acessos ao banco e melhorando o
tempo de resposta.

#### Como Funciona

- **Anotações do Spring Cache**: O cache é gerenciado por anotações
  como `@Cacheable` (adiciona ao cache) e `@CacheEvict` (limpa o cache),
  utilizadas na camada de serviço. Ao buscar uma lista de produtos,
  o resultado é armazenado no Redis para futuras consultas.
- **Tempo de Vida (TTL)**: O cache tem um TTL configurado, neste projeto utilizo
  30 segundos como exemplo, definido na classe `CacheConfig`. Após esse
  tempo, os dados são removidos automaticamente, garantindo informações
  atualizadas a partir do banco de dados.
- **Ativação do Cache**: O cache é ativado com a anotação `@EnableCaching`,
  presente no arquivo `CacheConfig` ou na classe principal da aplicação.

#### Benefícios

- **Desempenho**: Redis reduz a carga no banco e acelera as leituras.
- **Eficiência**: Minimiza acessos repetidos ao banco de dados para dados estáveis.

## Configuração do Projeto

### Estrutura do Projeto

- **Spring Boot App**: A aplicação principal que utiliza Redis para caching e MySQL como banco de dados.
- **MySQL**: Banco de dados utilizado pela aplicação.
- **Redis**: Servidor de cache utilizado para melhorar o desempenho das operações de leitura de dados.

### Configuração do Docker Compose

O arquivo `docker-compose.yml` define os serviços e suas configurações. Ele inclui:

- **app**: A aplicação Spring Boot. As variáveis de ambiente configuram a conexão com o MySQL e Redis.
- **mysql**: O serviço de banco de dados MySQL.
- **redis**: O serviço Redis, com autenticação configurada.

### Configuração conexão com o Redis

- **Configuração no application.properties**:
  A configuração do cache Redis é definida no arquivo `application.properties`.
  As seguintes propriedades são usadas:
    - **spring.cache.type=redis**: Define que o tipo de cache usado é o Redis.
    - **spring.data.redis.host=${SPRING_REDIS_HOST}**: Especifica o host do Redis,
      que é configurado via variável de ambiente que no nosso caso o valor será **'redis'**,
      pois configuramos assim no `docker-compose.yml` na linha 26 e 15.
    - **spring.data.redis.port=${SPRING_REDIS_PORT}**: Define a porta do Redis,
      também configurada via variável de ambiente que no nosso caso será **'6379'**.
      Definimos isso no `docker-compose.yml` na linha 31 e 16.
    - **spring.data.redis.password=${SPRING_REDIS_PASSWORD}**: Especifica a
      senha de acesso ao Redis, configurada por variável de ambiente para
      maior segurança que será **'your_redis_password'**.
      Definimos isso no`docker-compose.yml` na linha 29 e 17.

