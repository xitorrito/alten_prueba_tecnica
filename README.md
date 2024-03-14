# Prueba técnica Alten

He optado por una arquitectura clean basada en 3 capas (presentation, domain y data) con patrón MVVM. Las vistas están hechas en compose y se comunican de forma reactiva con el viewmodel a traves de stateflow.
Para la inyección de dependencias estoy usando Hilt y para la conexión con la api utilizo Retrofit.


Algunas consideraciones:

No he incluido el mapa en el detalle del usuario porque para usar el sdk de google maps se necesita poner datos de pago para poder crear una credencial.

El detalle de usuario no incluye viewmodel. Se requiere si queremos presentar los datos de forma mas limpia al usuario en lugar de los campos tal y como llegan de la api.

El textfield para buscar usuarios no está muy detallado en el enunciado así que al abrir el menu de opciones de la barra superior y escoger la opción de "Buscar", aparece un campo de texto arriba de la lista. La api de randomuser no incluye buscar por nombre por lo que todo el filtrado es local.

La carga infinita de la lista no incluye ningún loader que indique que se están obteniendo nuevos usuarios.

He hecho un par de tests del viewmodel de la lista.

En la api no veo que se pueda hacer la llamada de un usuario específico. Sería lo ideal para evitar pasar mucha informacion entre vistas como ocurre ahora.

