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

CREATE PROCEDURE sp_listar_celulares()
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