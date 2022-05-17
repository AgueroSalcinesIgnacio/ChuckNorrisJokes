# Ayuda para arrancar el proyecto
## Microservicio que consulta un API REST

El proyecto está caracterizado por estar implementado en Java 11 utilizando el framework de SpringBoot. 
El proyecto jokes realizará consultas a una API rest (de chuck norris) en la nube.
## Procedimiento para levantar el entorno

- Debemos levantar el proyecto jokes que contiene el microservicio que vamos a consumir utilizando el comando **mvnw spring-boot:run**
- Por último necesitaremos un entorno desde el cual poder realizar consultas, yo recomiento utilizar postman o una herramienta similar.

Una vez tengamos todos estos requisitos, una prueba fiable de funcionamiento es real realizar una llamada post a la siguiente url **localhost:8083/v1/joke-request** con un cuerpo o body vacío para obtener la respuesta del microservicio (Siempre y cuando se hayan respetado los entornos y los puertos de despliegue).