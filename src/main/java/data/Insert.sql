INSERT INTO movement_type (code, label) VALUES
 ('IN', 'Entrée de stock'),
 ('OUT', 'Sortie (Vente)');

INSERT INTO product (id, name, description, price) VALUES
  ('001', 'Clavier mecanique', 'Clavier RGB Switch Red', 85.000),
  ('002', 'Souris Sans Fil', 'Souris ergonomique 4000 DPI', 40.000),
  ('003', 'Écran 27 pouces', 'Moniteur 4K IPS 144Hz', 300.000);

INSERT INTO stock_movement (id, createdAt, movementType, quantity) VALUES
  (1, '20 August 2026', 'IN', 40),
  (1, '15 July 2026', 'OUT', 20),
  (2, '17 August 2026', 'OUT', 64),
  (3, '28 April 2026', 'IN', 15);