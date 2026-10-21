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



















