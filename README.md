# Task Manager API

Bem-vindo ao TaskManager API! Este é um serviço de gerenciamento de tarefas que permite criar, atualizar, listar e excluir tarefas.

### Introdução
A TaskManager API permite a você gerenciar suas tarefas de maneira eficiente e organizada. Com ela, você pode adicionar novas tarefas, visualizar todas as tarefas, atualizar tarefas existentes e removê-las quando necessário.

[//]: # (## Sumário)

[//]: # ()
[//]: # (- [Requisitos]&#40;#requisitos&#41;)

[//]: # (- [Instalação]&#40;#instalação&#41;)

[//]: # (- [Uso]&#40;#uso&#41;)

[//]: # (- [Testes]&#40;#testes&#41;)

[//]: # (- [Docker]&#40;#docker&#41;)

[//]: # (- [Contribuição]&#40;#contribuição&#41;)

[//]: # (- [Licença]&#40;#licença&#41;)

[//]: # (  Introdução)

[//]: # (  Instalação)

[//]: # (  Uso)

[//]: # (  Endpoints)

[//]: # (  Criar Tarefa)

[//]: # (  Listar Tarefas)

[//]: # (  Atualizar Tarefa)

[//]: # (  Excluir Tarefa)

[//]: # (  Autenticação)

[//]: # (  Exemplos)

[//]: # (  Contribuição)

[//]: # (  Licença)

## Requisitos

- Java 21 JDK
- Spring Boot 3.3.2
- Gradle
- JPA
- MySQL
- Docker
- Swagger

## Instalação

### Clonando o Repositório

```sh
git clone https://github.com/alexandreluchetti/taskManager.git
cd taskManager
```

### Compilando o Projeto

1. Compile o projeto e empacote o JAR:
```sh
./gradlew build
```

## Uso

### Executando a Aplicação

Depois de compilar o projeto, você pode executar o JAR gerado com o seguinte comando:

```sh
./gradlew bootRun
```

CONTINUAR













## Docker

### Construindo a Imagem Docker

Para construir a imagem Docker do projeto, execute:

```sh
docker build -t snail-supera-project-java .
```

### Executando o Contêiner Docker

Depois de construir a imagem, você pode executar o contêiner usando:

```sh
docker run -it --rm snail-supera-project-java
```

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b minha-feature`
3. Commit suas mudanças: `git commit -am 'Adiciona minha feature'`
4. Faça push para a branch: `git push origin minha-feature`
5. Envie um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.