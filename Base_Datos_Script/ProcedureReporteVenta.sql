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

