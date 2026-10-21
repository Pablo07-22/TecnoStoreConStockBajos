DELIMITER //

CREATE PROCEDURE CrearEmpleado (
    IN p_id VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(20)
)
BEGIN
    INSERT INTO empleados (
        id, 
        nombre, 
        telefono
    ) 
    VALUES (
        p_id, 
        p_nombre, 
        p_telefono
    );
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE EliminarEmpleado (
    IN p_id VARCHAR(20)
)
BEGIN
    -- 1. Desvincular al empleado de las ventas realizadas (poner en NULL)
    UPDATE ventas SET id_empleado = NULL WHERE id_empleado = p_id;

    -- 2. Eliminar el registro del empleado
    DELETE FROM empleados WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ListarEmpleados ()
BEGIN
    SELECT 
        id, 
        nombre, 
        telefono
    FROM empleados;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ActualizarEmpleado (
    IN p_id VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(20)
)
BEGIN
    UPDATE empleados 
    SET 
        nombre = p_nombre,
        telefono = p_telefono
    WHERE TRIM(id) = TRIM(p_id);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ObtenerEmpleado (
    IN p_id VARCHAR(20)
)
BEGIN
    SELECT 
        id, 
        nombre, 
        telefono
    FROM empleados
    WHERE TRIM(id) = TRIM(p_id);
END //

DELIMITER ;