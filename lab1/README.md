# Maven

## O que é?

Maven é uma ferramenta de gestão e compilação com o intuito de fornecer aos programadores uma estrutura capaz de apoiar as fases do desenvolvimento de um projeto de software. Através do conceito Project Object Model (POM), o Maven trata da complicação, distribuição, documentação e colaboração em equipa.

## instalat o Maven
```
mvn install
```
## criar um projeto Maven
```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=[nome] -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false 
```
## compilar o projeto
```
mvn package
```
## executar o projeto
 ```
mvn exec:java -Dexec.mainClass="app.App" -D exec.cleanupDaemonThreads=false 
```
## outros comandos frequentes

* ```mvn clean``` limpa a pasta temporária
* ```mvn test``` compila o código do programa e executa os testes
* ```mvn verify ```compila o código do programa e executa os testes de integração