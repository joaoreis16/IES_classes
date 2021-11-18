# 3.1 | Accessing databases in SpringBoot

Java Persistence API (JPA) para além de ser uma framework leve para persistir objetos Java, oferece diversas funcionalidades essenciais em qualquer aplicação corporativa.

**The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?**

The UserRepository is initialized because UserController has the @Autowired annotation, which injects an instance of UserRepository on the UserController object.

**List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?** 

The methods called are findAll(), save(), findById() and delete(). These are defined on the CrudRepository class, which our Repository extends.

**Where is the databeing saved?**

The data is being saved using the h2database, which we added to the project as a dependency. By default, it is an in-memory database.

**Where is the rule for the “not empty” email address defined?**

The "not empty" rule is defined on the User class, with the annotation @NotBlank when we declare the attribute


# 3.2 | Multilayer applications: exposingdata with RESTinterface

colocar o docker a correr
```
docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33060:3306 -d mysql/mysql-server:5.7
```

Através do Postman, é possível enviar dados para a base de dados associada ao site. Os printscreens estão na diretoria ```lab3_2/img_Postman```


# Review questions

## A) Explain the differences between the RestController and Controller components used in different parts of this lab.

**@Controller**
* é uma anotação comum que é usada para marcar uma classe como Spring MVC Controller 
* é uma anotação antiga, existe desde que a Primavera começou a suportar a anotação
* indica que a classe é um "Controlador" como um controlador web
* é uma especialização de @Component annotation

**@RestController**
* é um controlador especial usado em serviços web RESTFul e o equivalente de @Controller + @ResponseBody.
* é relativamente novo, adicionado apenas na Spring 4.0
* indica que a classe é um controlador onde os métodos @RequestMapping assumem @ResponseBody semantics por defeito, ou seja, servicing REST API.
* é uma especialização de @Controller annotation


## B) Create a visualization of the Spring Boot layers (UML diagramor similar), displaying the key abstractions in the solutionof 3.3, in particular: entities, repositories, services and REST controllers.

está na diretoria seguinte ```lab3/UML_Diagram.png```

## C) Explain the annotations @Table, @Colum, @Id foundin the Employee entity.

**@Table** : cada classe de entidade mapeia uma tabela de base de dados com o mesmo nome no default schema da base de dados.

**@Column** : É uma anotação opcional que permite personalizar o mapeamento entre o atributo da entidade e a coluna da base de dados

**Id** : especifica pelo menos um atributo de chave primária para cada entidade


## D) Explain the use of the annotation @AutoWired (in the Rest Controller class).

A anotação @Autowired fornece um controle mais refinado sobre onde e como a autowiring deve ser realizada. A anotação @Autowired pode ser usada para autowire bean no método setter, assim como a anotação @Required, construtor, uma propriedade ou métodos com nomes arbitrários /ou argumentos múltiplos.