# Ayuda para arrancar el proyecto
## Microservicio que consulta un API REST

El proyecto consta de dos subproyectos caracterizados por estar implementados en Java 11 utilizando el framework de SpringBoot. 
Por un lado tenemos el subproyecto discoveryserver que contiene toda la configuración necesaria para levantar el entorno de eureka y permitir así la gestión de los microservicios. 
Por último tenemos el proyecto jokes que contiene el microservicio objetivo, el cual realizará consultas a una API rest en la nube.
## Procedimiento para levantar los entornos

- Primero debemos levantar el entorno de eureka. 
- Después debemos levantar el proyecto jokes que contiene el microservicio que vamos a consumir
- Por último necesitaremos un entorno desde el cual poder realizar consultas, yo recomiento utilizar postman o una herramienta similar.

Una vez tengamos todos estos requisitos, una prueba fiable de funcionamiento es real realizar una llamada post a la siguiente url *localhost:8083/v1/joke-request* con un cuerpo o body vacío para obtener la respuesta del microservicio (Siempre y cuando se hayan respetado los entornos y los puertos de despliegue).