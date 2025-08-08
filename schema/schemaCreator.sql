-- ParkingLot table
CREATE TABLE parking_lot (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(32),
    car_present INT,
    fee_calculation_strategy VARCHAR(255),
    slot_allocation_strategy VARCHAR(255)
);

-- ParkingFloor table
CREATE TABLE parking_floor (
    id VARCHAR(36) PRIMARY KEY,
    floor_number INT NOT NULL,
    status VARCHAR(32),
    parking_lot_id VARCHAR(36),
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id)
);

-- ParkingSlot table
CREATE TABLE parking_slot (
    id VARCHAR(36) PRIMARY KEY,
    slot_number INT NOT NULL,
    status VARCHAR(32),
    parking_floor_id VARCHAR(36),
    FOREIGN KEY (parking_floor_id) REFERENCES parking_floor(id)
);

-- Gate table
CREATE TABLE gate (
    id VARCHAR(36) PRIMARY KEY,
    number INT NOT NULL,
    status VARCHAR(32),
    parking_lot_id VARCHAR(36),
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id)
);

-- Operator table
CREATE TABLE operator (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    employee_id VARCHAR(64),
    gate_id VARCHAR(36),
    FOREIGN KEY (gate_id) REFERENCES gate(id)
);

-- Vehicle table
CREATE TABLE vehicle (
    id VARCHAR(36) PRIMARY KEY,
    registration_number VARCHAR(32) NOT NULL,
    vehicle_type VARCHAR(16),
    owner_name VARCHAR(255),
    owner_contact_number VARCHAR(32)
);

-- Ticket table
CREATE TABLE ticket (
    id VARCHAR(36) PRIMARY KEY,
    entry_time TIMESTAMP NOT NULL,
    entry_gate_id VARCHAR(36),
    operator_id VARCHAR(36),
    parking_slot_id VARCHAR(36),
    vehicle_id VARCHAR(36),
    FOREIGN KEY (entry_gate_id) REFERENCES gate(id),
    FOREIGN KEY (operator_id) REFERENCES operator(id),
    FOREIGN KEY (parking_slot_id) REFERENCES parking_slot(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

-- Bill table
CREATE TABLE bill (
    id VARCHAR(36) PRIMARY KEY,
    ticket_id VARCHAR(36),
    exit_time TIMESTAMP,
    amount DECIMAL(10,2),
    exit_gate_id VARCHAR(36),
    operator_id VARCHAR(36),
    status VARCHAR(32),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    FOREIGN KEY (exit_gate_id) REFERENCES gate(id),
    FOREIGN KEY (operator_id) REFERENCES operator(id)
);

-- Payment table
CREATE TABLE payment (
    id VARCHAR(36) PRIMARY KEY,
    transaction_id VARCHAR(64),
    payment_method VARCHAR(32),
    payment_status VARCHAR(32),
    amount DECIMAL(10,2),
    bill_id VARCHAR(36),
    FOREIGN KEY (bill_id) REFERENCES bill(id)
);

-- Allowed vehicle types for parking lot
CREATE TABLE parking_lot_allowed_vehicle_type (
    parking_lot_id VARCHAR(36),
    vehicle_type VARCHAR(16),
    PRIMARY KEY (parking_lot_id, vehicle_type),
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id)
);

-- Allowed vehicle types for parking floor
CREATE TABLE parking_floor_allowed_vehicle_type (
    parking_floor_id VARCHAR(36),
    vehicle_type VARCHAR(16),
    PRIMARY KEY (parking_floor_id, vehicle_type),
    FOREIGN KEY (parking_floor_id) REFERENCES parking_floor(id)
);

-- Allowed vehicle types for parking slot
CREATE TABLE parking_slot_allowed_vehicle_type (
    parking_slot_id VARCHAR(36),
    vehicle_type VARCHAR(16),
    PRIMARY KEY (parking_slot_id, vehicle_type),
    FOREIGN KEY (parking_slot_id) REFERENCES parking_slot(id)
);