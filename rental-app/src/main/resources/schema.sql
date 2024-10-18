-- MySQL: Create rental_house table in the 'rental' database
CREATE TABLE IF NOT EXISTS rental_house (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            city VARCHAR(255),
                                            street VARCHAR(255),
                                            house_number INT,
                                            floor INT,
                                            room_count INT,
                                            price INT,
                                            area INT
);
