/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  acer
 * Created: 6/10/2022
 */
CREATE TABLE Persona(
cedula BIGINT PRIMARY KEY NOT NULL,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
correoElectronico VARCHAR(20) NOT NULL,
contrasena VARCHAR(20) NOT NULL);

CREATE TABLE Producto(
codigoProducto BIGINT PRIMARY KEY NOT NULL,
nombreProducto VARCHAR(20) NOT NULL,
precio DOUBLE NOT NULL,
cantidad Integer NOT NULL,
categoria VARCHAR(20) NOT NULL);


CREATE TABLE Venta(
codigoVenta BIGINT PRIMARY KEY NOT NULL,
fechaVenta VARCHAR(20) NOT NULL,
unidadesVendidas int NOT NULL,
total int NOT NULL,
codigoProductoVenta BIGINT,
foreign key (codigoProductoVenta) references Producto (codigoProducto));