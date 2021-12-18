INSERT INTO User_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Test', 'User1', 'USER', true, '$2a$10$cgFMapKYpc4Ixd04FXoMA.mLmGjeORJYcJNB4cexw4KaWTFmPCArm', 'testuser1@simplilearn.com', '1234567890');


INSERT INTO Address( user_id, address_line_one, address_line_two, city, state, country, postal_code, is_billing, is_shipping)
VALUES (5, '88 Some Building', 'Some Garden', 'Bkt Jalil', 'KL', 'Malaysia', '57000', true, false );

INSERT INTO Cart (user_id, grand_total, cart_lines) VALUES (5, 0, 0);

