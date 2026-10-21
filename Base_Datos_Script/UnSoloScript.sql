CREATE DATABASE TecnologiaStorePablo;
USE TecnologiaStorePablo;

CREATE TABLE marca (
    id VARCHAR(20) PRIMARY KEY,
    nombre_marca VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE celulares (
    id VARCHAR(20) PRIMARY KEY,
    id_marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(80) NOT NULL,
    sistema_operativo ENUM('IOS', 'ANDROID', 'KAIOS') NOT NULL,
    gama ENUM('ALTA', 'MEDIA', 'BAJA') NOT NULL,
    precio DOUBLE NOT NULL CHECK (precio > 0),
    stock INT NOT NULL CHECK (stock >= 0),
    stock_minimo INT NOT NULL CHECK (stock_minimo >= 0),
    FOREIGN KEY (id_marca) REFERENCES marca(id)
);

CREATE TABLE clientes (
    id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE empleados (
    id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
        
);

CREATE TABLE ventas (
    id VARCHAR(20) PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DOUBLE NOT NULL,
    id_empleado VARCHAR(20) NOT NULL,
    id_cliente VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta VARCHAR(20) NOT NULL,
    id_celular VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DOUBLE NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id),
    FOREIGN KEY (id_celular) REFERENCES celulares(id)
);

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

DELIMITER //

CREATE PROCEDURE CrearCelular(
    IN p_id_celular VARCHAR(20),
    IN p_id_marca VARCHAR(20),
    IN p_nombre_marca VARCHAR(50),
    IN p_modelo VARCHAR(50),
    IN p_sistema_operativo VARCHAR(50),
    IN p_gama VARCHAR(50),
    IN p_precio DOUBLE,
    IN p_stock INT,
    IN p_stock_minimo INT
)
BEGIN
    DECLARE v_id_marca VARCHAR(20);

    -- 1. Verificamos si la marca (por nombre) ya existe en la base de datos
    SELECT id INTO v_id_marca 
    FROM marca 
    WHERE LOWER(nombre_marca) = LOWER(p_nombre_marca) 
    LIMIT 1;

    -- 2. Si no existe, la insertamos con el ID que le mandamos desde Java
    IF v_id_marca IS NULL THEN
        INSERT INTO marca (id, nombre_marca) 
        VALUES (p_id_marca, p_nombre_marca);
        SET v_id_marca = p_id_marca;
    END IF;

    -- 3. Insertamos el celular usando la clave foránea correcta ya existente/creada
    INSERT INTO celulares (id, id_marca, modelo, sistema_operativo, gama, precio, stock, stock_minimo)
    VALUES (p_id_celular, v_id_marca, p_modelo, p_sistema_operativo, p_gama, p_precio, p_stock, p_stock_minimo);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE EliminarCelular (
    IN p_id VARCHAR(20)
)
BEGIN
    DECLARE v_id_marca VARCHAR(20);

    -- 1. Obtener el id_marca del celular antes de borrarlo
    SELECT id_marca INTO v_id_marca 
    FROM celulares 
    WHERE id = p_id;

    -- 2. Eliminar referencias en detalle_ventas si existen
    DELETE FROM detalle_ventas WHERE id_celular = p_id;

    -- 3. Eliminar el celular
    DELETE FROM celulares WHERE id = p_id;

    -- 4. Si la marca no la usa ningún otro celular, se elimina automáticamente
    IF v_id_marca IS NOT NULL THEN
        IF NOT EXISTS (SELECT 1 FROM celulares WHERE id_marca = v_id_marca) THEN
            DELETE FROM marca WHERE id = v_id_marca;
        END IF;
    END IF;

END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ActualizarCelular(
    IN p_id_celular VARCHAR(20),
    IN p_id_marca VARCHAR(20),
    IN p_nombre_marca VARCHAR(50),
    IN p_modelo VARCHAR(50),
    IN p_sistema_operativo VARCHAR(50),
    IN p_gama VARCHAR(50),
    IN p_precio DOUBLE,
    IN p_stock INT,
    IN p_stock_minimo INT
)
BEGIN
    DECLARE v_id_marca VARCHAR(20);

    -- Verificar si la marca ya existe
    SELECT id INTO v_id_marca 
    FROM marca 
    WHERE LOWER(nombre_marca) = LOWER(p_nombre_marca) 
    LIMIT 1;

    -- Si no existe la marca nueva, se crea en la tabla marca
    IF v_id_marca IS NULL THEN
        IF p_id_marca IS NULL OR p_id_marca = '' THEN
            SET v_id_marca = CONCAT('MAR-', UPPER(SUBSTRING(MD5(RAND()), 1, 5)));
        ELSE
            SET v_id_marca = p_id_marca;
        END IF;
        
        INSERT INTO marca (id, nombre_marca) VALUES (v_id_marca, p_nombre_marca);
    END IF;

    -- Actualizar los datos del celular
    UPDATE celulares 
    SET id_marca = v_id_marca,
        modelo = p_modelo,
        sistema_operativo = p_sistema_operativo,
        gama = p_gama,
        precio = p_precio,
        stock = p_stock,
        stock_minimo = p_stock_minimo
    WHERE TRIM(id) = TRIM(p_id_celular);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ObtenerCelular(IN p_id VARCHAR(50))
BEGIN
    SELECT 
        c.id, 
        m.nombre_marca, 
        c.modelo, 
        c.sistema_operativo, 
        c.gama, 
        c.precio, 
        c.stock, 
        c.stock_minimo
    FROM celulares c
    INNER JOIN marca m ON c.id_marca = m.id
    WHERE TRIM(c.id) = TRIM(p_id);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ListarCelulares()
BEGIN
    SELECT 
        c.id, 
        m.nombre_marca, 
        c.modelo, 
        c.sistema_operativo, 
        c.gama, 
        c.precio, 
        c.stock, 
        c.stock_minimo
    FROM celulares c
    INNER JOIN marca m ON c.id_marca = m.id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ObtenerReporteVentas()
BEGIN
    SELECT 
        v.id AS id_venta,
        v.fecha,
        c.nombre AS nombre_cliente,
        c.cedula AS cedula_cliente,
        e.nombre AS nombre_empleado,
        cel.modelo AS modelo_celular,
        dv.cantidad,
        dv.precio_unitario,
        v.total
    FROM ventas v
    INNER JOIN clientes c ON v.id_cliente = c.id
    INNER JOIN empleados e ON v.id_empleado = e.id
    INNER JOIN detalle_ventas dv ON v.id = dv.id_venta
    INNER JOIN celulares cel ON dv.id_celular = cel.id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE RegistrarCliente(
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