<%-- 
    Document   : index
    Created on : 5/05/2024, 6:14:29 p. m.
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Formulario insertar usuario</div>
        <form name="ingreso" action="ServletUsuarios" method="post">
            <br><br>
            Documento: <input type="text" name="documento" value=""/>
            <br><br>
            Nombre: <input type="text" name="nombre" value=""/>
            <br><br>
            Especialidad: <input type="text" name="especialidad" value=""/>
            <br><br>
            Ciudad: <input type="text" name="ciudad" value=""/>
            <br><br>
            <input type="submit" name="submit" value="Insertar"/>
            <br><br>
            <input type="submit" name="submit" value="Consultar"/>
            <br><br>
            <input type="submit" name="submit" value="Borrar"/>
            <br><br>
            <input type="submit" name="submit" value="Actualizar"/>
            <br><br>
    </body>
</html>
