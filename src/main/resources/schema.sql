DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    order_id     INT AUTO_INCREMENT PRIMARY KEY,
    client_id    VARCHAR(255)                               NOT NULL,
    state        ENUM ('running', 'delivered', 'cancelled') NOT NULL,
    product_name VARCHAR(255)                               NOT NULL,
    quantity     INT                                        NOT NULL
        check (quantity > 0)
);