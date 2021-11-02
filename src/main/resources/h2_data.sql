INSERT INTO USER (email, is_active, password, roles) VALUES('sotirisy@hotmail.com', TRUE, 'password456343456', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('pavlospavlides@outlook.com', TRUE, 'Iamascientistpassword', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('arisapeitou@gmail.com', TRUE, 'PASSWORD123pass', 'APPLICANT');

INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(1, 'Sotiris', 'Yiallourides', '993443563', 'Agia Paraskevi', 'Cyprus', 'Nicosia', '2054', '12/03/1199', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(2, 'Pavlos', 'Pavlides', '932533563', 'Agia Thekla', 'Cyprus', 'Larnaca', '2324', '18/09/1993', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(3, 'Aris', 'Apeitou', '32325363', 'Simonos', 'Cyprus', 'Limassol', '1224', '03/02/1996', 'Male');

INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(1, 'Not experience at all', 'Bsc Computer Science degree','cv.pdf', 'coverletter.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(2, 'A bit of experience', 'Bsc Applied Software Engineering degree','cvs.pdf', 'coverletterds.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(3, 'A lot of experience with clients', 'Bsc Applied Software Engineering degree','cvsds.pdf', 'coverldetterds.pdf');