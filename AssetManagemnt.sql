CREATE DATABASE assetdb;
USE assetdb;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);
INSERT INTO employees (employee_id, name, department, email, password) VALUES
(1, 'John Doe', 'IT', 'john@example.com', 'pass123'),
(2, 'Jane Smith', 'Finance', 'jane@example.com', 'pass456'),
(3, 'Mike Lee', 'HR', 'mike@example.com', 'pass789');
select * from employees;
-------------------------------------------------------------------
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
INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id) VALUES
(101, 'Dell Laptop', 'Laptop', 'DL-123456', '2022-05-10', 'Mumbai', 'in use', 1),
(102, 'HP Printer', 'Printer', 'HP-987654', '2021-11-01', 'Delhi', 'available', 2),
(103, 'Projector', 'Electronics', 'PJ-556677', '2020-01-15', 'Chennai', 'under maintenance', 3);

-- to check for delete 
INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id)
VALUES (200, 'Test Mouse', 'Peripheral', 'TM-200', '2024-01-01', 'Pune', 'available', 1);

select * from assets;
-------------------------------------------------------------------------
CREATE TABLE maintenance_records (
    maintenance_id INT PRIMARY KEY,
    asset_id INT,
    maintenance_date DATE,
    description VARCHAR(255),
    cost DOUBLE,
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id)
);
INSERT INTO maintenance_records (maintenance_id, asset_id, maintenance_date, description, cost) VALUES
(1, 101, '2023-01-10', 'Battery Replacement', 2500.00),
(2, 103, '2024-02-05', 'Lens Cleaning', 1500.00);

select * from maintenance_records;
--------------------------------------------------------------------------------
CREATE TABLE asset_allocations (
    allocation_id INT PRIMARY KEY,
    asset_id INT,
    employee_id INT,
    allocation_date DATE,
    return_date DATE,
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
INSERT INTO asset_allocations (allocation_id, asset_id, employee_id, allocation_date, return_date) VALUES
(1, 101, 1, '2024-10-01', NULL),
(2, 102, 2, '2023-12-15', '2024-01-20');

select * from  asset_allocations ;
-- to check for allocation and deallocation --
SELECT * FROM asset_allocations WHERE asset_id = 101;


--------------------------------------------------------------------------------
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
INSERT INTO reservations (reservation_id, asset_id, employee_id, reservation_date, start_date, end_date, status) VALUES
(1, 103, 3, '2024-04-01', '2024-04-10', '2024-04-15', 'approved'),
(2, 102, 1, '2024-03-10', '2024-03-15', '2024-03-20', 'pending');

select * from reservations;
---------------------------------------------------------------------------------------
show tables;
-- Insert an employee
INSERT INTO employees (employee_id, name, department, email, password)
VALUES (99, 'Test User', 'QA', 'test@example.com', 'test123');

-- Insert an asset owned by that employee, not used anywhere else(to check for delete)
INSERT INTO assets (asset_id, name, type, serial_number, purchase_date, location, status, owner_id)
VALUES (999, 'Temp Chair', 'Furniture', 'TC-999', '2024-04-01', 'TestLab', 'available', 99);