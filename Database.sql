
create Database hotel ;

use hotel ;




CREATE TABLE Position (
    id INTEGER PRIMARY KEY,
    positionName VARCHAR(255) NOT NULL
);

CREATE TABLE Branch (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    room_id INTEGER,
    phone VARCHAR(20),
    location VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE Floor (
    id INTEGER PRIMARY KEY,
    floorNumber INTEGER NOT NULL
);

CREATE TABLE RoomType (
    id INTEGER PRIMARY KEY,
    classPrice DECIMAL(10, 2) NOT NULL,
    className VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE Status (
    id INTEGER PRIMARY KEY,
    statusName VARCHAR(255) NOT NULL
);

CREATE TABLE Room (
    id INTEGER PRIMARY KEY,
    floor_id INTEGER NOT NULL,
    roomNumber INTEGER NOT NULL,
    status INTEGER NOT NULL,
    room_type INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    FOREIGN KEY (floor_id) REFERENCES Floor(id),
    FOREIGN KEY (status) REFERENCES Status(id),
    FOREIGN KEY (room_type) REFERENCES RoomType(id),
    FOREIGN KEY (branch_id) REFERENCES Branch(id)
);

CREATE TABLE Employee (
    id INTEGER PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    profileCreation DATE,
    email VARCHAR(255),
    position INTEGER,
    branch_id INTEGER,
    FOREIGN KEY (position) REFERENCES Position(id),
    FOREIGN KEY (branch_id) REFERENCES Branch(id)
);

CREATE TABLE Customer (
    id INTEGER PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    profileCreation DATE,
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE Booking (
    id INTEGER PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    checkout DATE,
    checkin DATE,
    bookingdate DATE,
    FOREIGN KEY (customer_id) REFERENCES Customer(id),
    FOREIGN KEY (room_id) REFERENCES Room(id),
    FOREIGN KEY (branch_id) REFERENCES Branch(id)
);

CREATE TABLE Receipt (
    id INTEGER PRIMARY KEY,
    booking_id INTEGER NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (booking_id) REFERENCES Booking(id)
);





-- Inserting data into Position table
INSERT INTO Position (id, positionName) VALUES 
(1, 'Manager'),
(2, 'Receptionist'),
(3, 'Cleaner');

-- Inserting data into Branch table
INSERT INTO Branch (id, name, room_id, phone, location, description) VALUES 
(1, 'Downtown Branch', NULL, '123-456-7890', '123 Main St', 'Main downtown branch'),
(2, 'Airport Branch', NULL, '234-567-8901', '456 Airport Rd', 'Branch near the airport');

-- Inserting data into Floor table
INSERT INTO Floor (id, floorNumber) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Inserting data into RoomType table
INSERT INTO RoomType (id, classPrice, className, capacity, description) VALUES 
(1, 100.00, 'Single', 1, 'Single room with one bed'),
(2, 150.00, 'Double', 2, 'Double room with two beds'),
(3, 200.00, 'Suite', 4, 'Suite with multiple rooms and beds');

-- Inserting data into Status table
INSERT INTO Status (id, statusName) VALUES 
(1, 'Available'),
(2, 'Occupied'),
(3, 'Under Maintenance');

-- Inserting data into Room table
INSERT INTO Room (id, floor_id, roomNumber, status, room_type, branch_id) VALUES 
(1, 1, 101, 1, 1, 1),
(2, 1, 102, 2, 2, 1),
(3, 2, 201, 1, 3, 1),
(4, 3, 301, 3, 1, 2);

-- Inserting data into Employee table
INSERT INTO Employee (id, firstName, lastName, address, phone, profileCreation, email, position, branch_id) VALUES 
(1, 'John', 'Doe', '789 Elm St', '345-678-9012', '2022-01-01', 'john.doe@example.com', 1, 1),
(2, 'Jane', 'Smith', '456 Oak St', '456-789-0123', '2023-03-15', 'jane.smith@example.com', 2, 2),
(3, 'Jim', 'Brown', '321 Pine St', '567-890-1234', '2023-06-20', 'jim.brown@example.com', 3, 1);

-- Inserting data into Customer table
INSERT INTO Customer (id, firstName, lastName, address, phone, profileCreation, email, password) VALUES 
(1, 'Alice', 'Johnson', '654 Cedar St', '678-901-2345', '2023-02-10', 'alice.johnson@example.com', 'alice123'),
(2, 'Bob', 'Williams', '987 Birch St', '789-012-3456', '2023-04-25', 'bob.williams@example.com', 'bob456');

-- Inserting data into Booking table
INSERT INTO Booking (id, customer_id, room_id, branch_id, checkout, checkin, bookingdate) VALUES 
(1, 1, 1, 1, '2023-06-20', '2023-06-15', '2023-06-10'),
(2, 2, 2, 1, '2023-06-22', '2023-06-18', '2023-06-12');

-- Inserting data into Receipt table
INSERT INTO Receipt (id, booking_id, description) VALUES 
(1, 1, 'Payment for room 101'),
(2, 2, 'Payment for room 102');

