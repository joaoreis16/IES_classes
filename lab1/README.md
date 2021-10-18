# 1.2 | Maven

## O que é?

Maven é uma ferramenta de gestão e compilação com o intuito de fornecer aos programadores uma estrutura capaz de apoiar as fases do desenvolvimento de um projeto de software. Através do conceito Project Object Model (POM), o Maven trata da complicação, distribuição, documentação e colaboração em equipa.

pom.xml - É um ficheiro XML que contém a informação sobre o projeto e os detalhes de configuração usados pelo Maven para construir o projeto. Contém default values para a maioria dos projetos.

Maven Archetye - [COMPLETE]

groupId -  [COMPLETE]

artifactId -  [COMPLETE]

## Instalar o Maven
```
mvn install
```
## Criar um projeto Maven
```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=[nome] -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false 
```
## Compilar o projeto
```
mvn package
```
## Executar o projeto
 ```
mvn exec:java -Dexec.mainClass="app.App" -D exec.cleanupDaemonThreads=false 
```
* O parâmetro "-D exec.cleanupDaemonThreads=false" serve para evitar um erro ao correr o exercicio 

* Ao executar o projeto, devemos especificar a class principal deste modo "-Dexec.mainClass='app.App'" 

* Ao executar o projeto, podemos também passar um argumento...
```
mvn exec:java -Dexec.mainClass="app.App" -Dexec.args="1110600"
```
... ou vários argumentos
```
mvn exec:java -Dexec.mainClass="app.App" -Dexec.args="1110600 1010500"
```

* Podemos também executar o projeto de outras diretorias utilizando o parâmentro '-f'
```
mvn exec:java -Dexec.mainClass=test.Main -f folder/pom.xm
```

## Outros comandos frequentes

* ```mvn clean``` limpa a pasta temporária
* ```mvn test``` compila o código do programa e executa os testes
* ```mvn verify``` compila o código do programa e executa os testes de integração


## Outras notas

* Para compliar e correr o exercício 1.2, é necessário estar no seguinte diretório:
```
cd lab1/lab1_2/MyWeatherRadar
```

# 1.3 | GitHub

```.gitignore``` - ficheiro para ignorar outros ficheiros ao dar push para o repositório


# 1.4 | Docker

## O que é?

[explicar Docker]

## Alguns comandos

Verificar a versão do docker
```
docker --version
```

Verificar se a instalação do Docker foi bem sucedida
```
$ docker run hello-world
```

Lista todas as imagens do Docker no computador
```
$ docker image ls
```

Lista os containers existentes
```
$ docker ps --all
```
## Apontamentos do tutorial em https://docs.docker.com/get-started/

* ## creating a container from scratch
```
 docker run -d -p 80:80 docker/getting-started
```
You’ll notice a few flags being used. Here’s some more info on them:

* ```-d``` -> run the container in detached mode (in the background)
* ```-p 80:80``` -> map port 80 of the host to port 80 in the container
* ```docker/getting-started``` -> the image to use

* ## What is a container?

A container is a sandboxed process on your machine that is isolated from all other processes on the host machine.

* ## What is a container image?

When running a container, it uses an isolated filesystem. This custom filesystem is provided by a container image. Since the image contains the container’s filesystem, it must contain everything needed to run an application - all dependencies, configuration, scripts, binaries, etc. The image also contains other configuration for the container, such as environment variables, a default command to run, and other metadata.

* ## Build the app’s container image
After create a dockerfile,
```
docker build -t getting-started .
```
*  the ```-t``` flag tags our image

* ## Start an app container
```
docker run -dp 3000:3000 getting-started
```

## Portainer CE: como instalar e executar

```
docker volume create portainer_data
docker run -d -p 8000:8000 -p 9443:9443 --name portainer \    
    --restart=always \    
    -v /var/run/docker.sock:/var/run/docker.sock \    
    -v portainer_data:/data \    
    portainer/portainer-ce:latest
```

## Dockerize PostgreSQL (inside of postgreSQL folder)

Build an image from the Dockerfile and assign it a name
```
docker build -t eg_postgresql .
```

Run the PostgreSQL server container (in the foreground):
```
docker run --rm -P --name pg_test eg_postgresql
```
