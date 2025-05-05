CREATE DATABASE assetdb;
USE assetdb;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE assets (
    asset_id INT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    serial_number VARCHAR(100),
    purchase_date DATE,
    location VARCHAR(100),
    status VARCHAR(50),
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES employees(employee_id)
);

CREATE TABLE maintenance_records (
    maintenance_id INT PRIMARY KEY,
    asset_id INT,
    maintenance_date DATE,
    description VARCHAR(255),
    cost DOUBLE,
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id)
);

CREATE TABLE asset_allocations (
    allocation_id INT PRIMARY KEY auto_increment,
    asset_id INT,
    employee_id INT,
    allocation_date DATE,
    return_date DATE,
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY,
    asset_id INT,
    employee_id INT,
    reservation_date DATE,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
