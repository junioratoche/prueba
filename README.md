# Test Java

En la carpeta docs se adjunta el enunciado para el reto de creación de API de consulta de precios de una empresa retail dedicada al comercio electrónico.

#Solución

Para dar solución al test, se consideraron los siguientes pasos:

1. Análisis de requerimiento, considerando los requisitos funcionales y no funcionales solicitados para poder definir el alcance y entregables.
2. Diseñar a alto nivel la arquitectura de desarrollo, y para ello se consideró un enfoque basado en Arquitectura Hexagonal, Principios SOLID, Patrones de Diseño, DDD, TDD, API First, estándar OpenAPI, Clean Code, Test Unitarios y de Integración.

![Image](/docs/images/APIFirst.jpg)

3. Para la implementación se consideraron las siguientes capas principales según la arquitectura hexagonal:
    /dominio (clases, excepciones)
    /application (casos de uso, servicio)
    /ports (interfaces in/out)
    /adapter (implementaciones)
4. Para las pruebas:
	/test

5. Se aplicó el enfoque API First y se usó una herramientas de generación de código a partir del diseño de APIs con la funcionalidad dada en el siguiente link https://editor.swagger.io/

6. Se aplicó el enfoque de TDD para tomar en cuenta los 5 casos de prueba solicitados.

7. Las pruebas se realizaron a través de JUnit, Postman, Swagger. Se adjunta en la carpeta docs, el archivo Collection para las pruebas Postman.





Para dar solución a este problema, se ha creado un servicio utilizando maven, java, springboot, jpa y una base de datos h2.

El código se ha estructurado de la siguiente forma:
- controller: controlador del servicio, con los endspoints habilitados: 
  * /prices : devuelve el listado de precios disponibles en la tabla product
  * /prices/{id}: devuelve el registro que corresponde al id seleccionado.
  * /prices/{brandId}/{productId}/{priceDate}: devuelve el registro cuya brandId, productId coincide con el indicado, además de que el priceDate se encuentre entre las fechas registradas (startDate y endDate)
  
- dto: objeto producto con sus atributos.

- entity: entidades relacionales de base de datos:
    - product: entidad principal con todos los atributos solicitados.
    - CreationAndLastUpdateHistory: entidad de auditoría.

- exceptions: clases de exceptión utilizadas, para controlar parámetros inválidos y precio no encontrado en el sistema.

- productRepository: clase de repositorio con la consulta lanzada. Extiende de CrudRepository para asociar, directamente, las acciones básicas de creación, lentura, modificación y eliminación.

- service: interfaz del servicio.

- service.impl: implementazión de los servicios implementando la interfaz asociada.

- validators: clases de validaciones.

- commons: clases comunes del proyecto. En nuestro caso, hemos definido mensajes.


 
  
Se han ejecutado todas las peticiones por url:

 Test1:
 
 ![Image](/images/p1.png)
 
 Test2:
 
 ![Image](/images/p2.png)

 Test3:
 
 ![Image](/images/p3.png)

 Test4:
 
 ![Image](/images/p4.png)

 Test5:
 
 ![Image](/images/p5.png)


Url peticion: /prices/{brandId}/{productId}/{priceDate}
Ejemplo: http://localhost:8080/prices/1/35455/2020-06-15T21:00:00

Acceso a swagger:
http://localhost:8080/swagger-ui/index.html#/

 ![Image](/images/swagger.png)
 
# Test
Se han ejecutado todos los test de forma satisfactoria:

 ![Image](/images/tests.png)

 
 
#Maven:

Ejecutar test (desde carpeta raiz)
 - mvn clean test
 
Ejecutar app(desde carpeta raiz)
 - mvn spring-boot:run 






