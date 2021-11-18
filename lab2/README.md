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

# 2.3 | Spring Boot

**Spring Boot** facilita o processo de configuração e publicação de aplicações
* favorece a **convenção sobre configuração** à plataforma Spring
* útil para começar com o mínimo esforço e criar aplicações autónomas

**Spring Initializr** - criar projetos maven (Spring Wed dependency)


## Run our Spring-Boot project
```
./mvnwspring-boot:run
```

depois aceder: http://localhost:8080/

- devemos encontrar o seguinte erro "Whitelabel Error Page" 

**Note**: mind the portsin use! You will get an error if 8080 is already in use(maybe there is a running Tomcat?...)


## Tutorial "Getting Started article" https://spring.io/guides/gs/serving-web-content/

1) criar um controlador

```
@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
```

**@GetMapping** - garante que as solicitações HTTP GET para /greeting seham mapeadas para o método greeting

**@RequestParam** - vincula o valor do parâmetro da string de consulta nameao nameparâmetro do greeting()método. Este parâmetro de string de consulta não é required. Se estiver ausente na solicitação, o defaultValue de World será usado. O valor do nameparâmetro é adicionado a um Modelobjeto, tornando-o acessível ao modelo de visualização.


# Review questions

## A) What are the responsibilities/services of a “servlet container”?
O "servlet container"  é um componente do servidor responsável pela gestão dos pedidos que permite a geração de páginas dinâmicas, por receber os pedidos do cliente e convertê-los em chamadas às classes Java que implementam a classe Servlet e receber as respostas das mesmas, depois encaminha para o cliente. Também é responsável pela gestão da concorrência, uma vez que consegue processar múltiplos pedidos em simultâneo.

## B) Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)
Spring Boot uses the MVC pattern in which the application is divided in 3 components:

After an HTTP request is made, the Controller receives and processes it. The Model is the middleware between the Controller and the View, which is being controlled and updated by the Controller The view is the component that allows  the user to see the processed request.

Exemple of this os the @Controller tag we used in the exercises.

## C) Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?
Facilitam a organização e a legibilidade uma vez que as Spring Boot started ajudam a reduzir o número de dependências que se tinham de adicionar manualmente.

## D) Which annotations are transitively included in the @SpringBootApplication?
A classe raíz @SpringBootApplication importa @EnableAutoConfiguration e @ComponentScan.

## E) Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.

***Use logical nesting on endpoints***

When designing endpoints we should group related topics, both to be easier to understand and to avoid giving attackers unnecessary information.
Exemple of this would be to append the /tables path to the end of the /furniture path in a online store.

***Handle errors gracefully and return standard error codes***

We should do this to every type of application we develop but it's very important in API's so that we provide clarity to the clients. IT also helps maintainers understand more quickly the problem that's occurred.

Some exemples of this would be-
403 Forbiden- Meaning the user is authenticated but has no permission to access a resource.

404 Not Found - When a resource can't be located.

***Cache data to improve performance***

We can add caching to return data from local memory instead of queryin the database everytime we want to retrieve data. It also provides users with a more responsive application. Although we should be carefull because caching may give users outdated information.


***Maintain good security practices***

Pretty much all information between client and server should be private, as people should't be access more information than they requested.
We can enforce this privacy by giving users roles that provided them permissions to all the information they need and nothing more.

***Use nouns instead of verbs in endpoint paths***

Http requests already include verbs like POST, GET, DELETE so we should give our endpoint paths, nouns that represent the entity that we are retrieving or manipulating. Also verbs are often similar and mean the same thing as get and retrieve providing no new information.
