# 2.1 | Server-side programming with servlets

Tomcat é um servidor web

## Correr o servidor Tomcat

Dentro da pasta apache-tomcat-9.0.54/bin, executar:
```
./startup.sh
```

Para confirmar que o Tomcat server está a correr:
i) ```curl -I 127.0.0.1:8080```
ii ) http://localhost:8080
iii) ```tail logs/catalina.out```


A instalação do Tomcat inclui um ***Manager app*** - podemos usar para controlar o servidor, incluindo deploying e un-deploying applications

Primeira vez a entrar no (manager app)[http://localhost:8080/manager/], user = admin e password = admin

==> dentro da manager app
    Examples --> Servlet--> Request Parameters
    o código fonte tem como objetivo criar um documento html. Tem como super classe ***HttpServlet***


## create a web application and deploy it into Tomcat

criar um projeto maven web aplication ==> webapp-javaee7

construir o projeto e assegurar que não há erros
```
mvn install
```
é então criado um ficheiro ***.war*** que contém a nossa aplicação empacotada como um arquivo web

Para dar deploy, temos 2 maneiras:
* Manager app [http://localhost:8080/manager/]
* copiar o ficheiro .war para /webapps na pasta Tomcat Root

Se o projeto estiver listada na Manager app ==> sucesso

# 2.2 | Server-side programming with embedded servers




