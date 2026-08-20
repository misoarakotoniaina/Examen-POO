CREATE TABLE movement_type (
  code VARCHAR(20) PRIMARY KEY,
  label VARCHAR(50) NOT NULL
);

CREATE TABLE product (
  id STRING PRIMARY KEY,
  name VARCHAR(100),
  description TEXT,
  unit_price BIGDECIMAL(10, 2) NOT NULL,
);

CREATE TABLE stock_movement (
  id STRING PRIMARY KEY,
  product_id STRING NOT NULL,
  created_at INSTANT DEFAULT CURRENT_TIMESTAMP,
  movement_type VARCHAR(3) NOT NULL CHECK (movement_type IN ('IN', 'OUT')),
  quantity INT NOT NULL,
  CONSTRAINT fk_movement_product
  FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
Insertion des D