# Test Java

Se menciona los detalles más importantes al problema planteado:

# Enunciado
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:
 
PRICES
-------
 
BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR
 
Campos: 
 
BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador código de producto.
PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
PRICE: precio final de venta.
CURR: iso de la moneda.
 
Se pide:
 
Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
 
Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
 
Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, 
(se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).
              
Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
                                                                                       
-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
 
 
Se valorará:
 
Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los test.

#Solución
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






