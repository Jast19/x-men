# Proyecto x-men
Examen MercadoLibre

## Tecnologías

* Java 17
* Spring Boot
* WebFlux
* DynamoDB
* Gradle 7.3
* Docker
* AWS

## Ambiente Local

1. Tener una cuenta en aws y configurar AWS CLI
2. Crear la tabla **mutant** en DynamoDB
3. Clonar el proyecto
4. Ejecutar con Gradle build
5. Probar los end points
    * http://localhost:8080/api/mutant
    * http://localhost:8080/api/start

## Ambiente AWS (ECS, Fargate)

1. Crea la imagen con docker
2. Sube la imagen ha AWS ECR
3. En ECS crear una Tack Definitions -> Fargate
4. Adiciona el contendor que subiste a ECR
5. Crear un cluster con una VPC nueva
6. Crear un Balanceador de carga de aplicación ALB
7. Crear un grupo de seguridad SG
8. Crear un service en ECS, asigna el ALB y SG
9. Por ultimo prueba con el DNS del ALb
