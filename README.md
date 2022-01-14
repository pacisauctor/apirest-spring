# Api Rest hecha con Spring Boot

## Tecnologías usadas

- Microsoft SQL Server 2019 (RTM-CU14) (KB5007182) - 15.0.4188.2 (X64) 
- Java version 11.0.13
- Spring Boot Versión 2.6.2
- Apache Netbeans 12.0

## ¿Cómo comenzar?

- Con el SQL Server instalado, debe de ejecutar el script [createdb.sql](https://github.com/pacisauctor/apirest-spring/blob/master/createdb.sql).
- Luego de ejecutarlo, lo siguiente es rellenar la base de datos, esto se hace con [data.sql](https://github.com/pacisauctor/apirest-spring/blob/master/data.sql).
- Posteriormente, abrir el proyecto con el IDE de su preferencia.
- Abrir [pom.xml](https://github.com/pacisauctor/apirest-spring/blob/master/pom.xml), y modificar spring.datasource.url, spring.datasource.username y spring.datasource.password según las credenciales de su server de base de datos.
- **Nota**: Recuerde que el spring.datasource.url debe de ir con la siguiente estructura: jdbc:sqlserver://host\\instancia:puerto;database=nombreDatabase
- Una vez hecho esto, puede usar la aplicación.

## ¿Qué hace esta api?

Esta api tiene las siguientes funcionalidades:

- **Autenticación**
- Control de sesión
  - El acceso a los servicios son controlados por una sesión, password encriptadas en la base de datos.
- **Control de permisos**
  - Los usuarios autenticados están divididos en dos grupos
  - Usuarios
    - Pueden consultar todos los servicios (get), excepto el registro de usuarios
    - Pueden Modificar (post/put) todos los registros, excepto el registro de usuario
  - Administradores
    - Pueden consultar todos los servicios(get)
    - Pueden modificar todos los registros (post/put)
    - Pueden desactivar usuarios
    
- Base de Datos: La base de datos consta de 5 tablas ([modelo relacional acá](https://github.com/pacisauctor/apirest-spring/blob/master/app_database.png?raw=true)), pero en resumen, es un api donde se pueden crear productos y sus categorías, además, a los productos puedes asignarles tags.

## ¿Cómo puedo probarlo?

Se elaboró una colección en Postman disponible [acá](https://www.postman.com/collections/86273b45464cfa4bb57d), solo es de guardarlo en un .json y posteriormente importarlo en su workspace, si desea ahorrarse este proceso, puede verlo en web, tanto como los endpoints creados y los ejemplos, en este [link](https://documenter.getpostman.com/view/12356617/UVXjJvYr#8c8f3d8c-163c-4a65-8a82-f0ed8d927ff4).

## ¿Y lo primero que debo de hacer?

Iniciar sesión, por medio de la ```POST /api/login/``` , en Postman está presente el cuerpo que debe de tener, sin embargo, por cualquier caso, lo adjunto acá:
```json
{
    "user":"user2",
    "password":"12"
}
```
**Nota: Si se notará, la contraseña del data.sql es distinta a la mostrada en el JSON, se recuerda que como está encriptada en la bd, esto sería la contraseña equivalente a texto plano.**

El user2 y user4 son administradores, por lo que pueden hacer cualquier consulta, en cambio, el user1 y user3, no lo son por lo que no pueden acceder a los endpoints de ```/api/users/```

Luego de eso puede acceder a las otras endpoints perfectamente, tal y como se declararon en postman.

## Extras

Cabe mencionar que con la ayuda de Swagger UI se automatizó una documentación (un poco imcompleta porque no me dió tiempo) acerca de los conjuntos de endpoints disponibles, los de interés son:
- category-rest-controller 
- tag-rest-controller
- product-rest-controller
- user-controller
- user-data-rest-controller
Esta incompleta puesto que falta agregarle la parte de autenticación, pero lo dejé para posteriormente retormalo y agregarlo.


--------------------------------
Elaborado por [@pacisauctor](https://github.com/pacisauctor/)
