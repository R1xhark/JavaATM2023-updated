CREATE TABLE ClientInformation (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    pin VARCHAR(4) NOT NULL
);
CREATE VIEW ClientView AS
SELECT client_id, client_name, card_number
FROM ClientInformation;
