Operacion QUASAR - CHallenge MELI


Diseño de la Arquitectura,
Se utilizo el modulo Spring Webflux, el cual implementa el modulo de RxJava, lo cual permite una
mejor performance a un nivel alto de request, debido a que implementa un comportamiento de peticiones no
bloqueantes.

Servicios:
Se dividieron los servicios getLocation y getMessage en dos clases separadas siguiendo el
principio de Simplicidad, lo cual en un futuro se podria escalar a que estas dos clases invoquen
a un microservicio encargado de procesar el mensaje o al microservicio encargado de 
obtener la localizacion 

Se definio un tercer Servicio que es el encargado de invocar a los servicios de localizacion y mensaje.

Para la obtencion del punto X,Y se utilizo la tecnica de Trilateracion, con la implementacion de la siguiente
Api, para obtener la misma
_com.lemmingapex.trilateration_

Hosting de la App
La misma se encuentra hosteada en Heroku
Endpoints:
 Obtener Mensaje y Localizacion:
 URL:`https://operation-quasar.herokuapp.com/api/v1/topsecret_split`
 Metodo: GET
 
 Actualizar satelite:`https://operation-quasar.herokuapp.com/api/v1/topsecret_split/{satellite_name}`
 Request Ej:
 Metodo Post: 
 Url:
 ` https://operation-quasar.herokuapp.com/api/v1/topsecret_split/kenobi`
 Body:
 `{
            "distance": 100.0,
            "message": ["algun", "Mensaje", "", "", ""]
    }`
    
Obtener Mensaje:`https://operation-quasar.herokuapp.com/api/v1/topsecret`
Metodo Post
Body:

`{ "satellites":
  [
     {   "name": "kenobi",
         "distance": 100.0,
         "message": ["algun", "Mensaje", "", "", ""]
     },
     {   "name": "skywalker",
         "distance": 115.5,
         "message": ["", "", "Oculto", "", "secreto"]
     },
     {   "name": "sato",
         "distance": 142.7,
         "message": ["este", "", "un", "", ""]
     }
     ]
     }`

 
 Requisitos de la App:
 JDK: Java 11
 Instalacion
 1)Clonar el proyecto
 2)Ejecutar mvn clean install
 3) Ejecutar java -jar quasar-0.0.1-SNAPSHOT.jar. 
 Se levantar por default en el puerto 8080
 
 

