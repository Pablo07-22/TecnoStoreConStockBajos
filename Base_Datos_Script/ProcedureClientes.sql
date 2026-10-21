DELIMITER //

CREATE PROCEDURE Registrarcliente(
    IN p_id VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_cedula VARCHAR(20),
    IN p_correo VARCHAR(100),
    IN p_telefono VARCHAR(20),
    OUT p_id_cliente_out VARCHAR(20)
)
BEGIN
    DECLARE v_id_existente VARCHAR(20);

    -- Buscar si la cédula ya existe en la base de datos
    SELECT id INTO v_id_existente
    FROM clientes
    WHERE cedula = p_cedula
    LIMIT 1;

    IF v_id_existente IS NOT NULL THEN
        -- Si ya existe, retornamos el ID que ya tenía registrado
        SET p_id_cliente_out = v_id_existente;
    ELSE
        -- Si es nuevo, lo insertamos y devolvemos el nuevo p_id
        INSERT INTO clientes (id, nombre, cedula, correo, telefono)
        VALUES (p_id, p_nombre, p_cedula, p_correo, p_telefono);
        
        SET p_id_cliente_out = p_id;
    END IF;
END //

DELIMITER ;

DELIMITER //

-- 2. Registrar venta, detalle de venta y descontar stock del celular
CREATE PROCEDURE RealizarVenta (
    IN p_id_venta VARCHAR(20),
    IN p_id_cliente VARCHAR(20),
    IN p_id_empleado VARCHAR(20),
    IN p_id_celular VARCHAR(20),
    IN p_cantidad INT,
    IN p_precio_unitario DOUBLE,
    IN p_total DOUBLE
)
BEGIN
    -- Insertar en la tabla ventas
    INSERT INTO ventas (id, fecha, total, id_empleado, id_cliente)
    VALUES (p_id_venta, NOW(), p_total, p_id_empleado, p_id_cliente);

    -- Insertar en detalle_ventas
    INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, precio_unitario, subtotal)
    VALUES (p_id_venta, p_id_celular, p_cantidad, p_precio_unitario, p_total);

    -- Actualizar (descontar) el stock del celular comprado
    UPDATE celulares 
    SET stock = stock - p_cantidad 
    WHERE id = p_id_celular;
END //

DELIMITER ;