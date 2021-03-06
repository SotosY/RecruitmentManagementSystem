INSERT INTO USER (email, is_active, password, roles) VALUES('sotirisy@hotmail.com', TRUE, 'password456343456', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('pavlospavlides@outlook.com', TRUE, 'Iamascientistpassword', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('arisapeitou@gmail.com', TRUE, 'PASSWORD123pass', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('aris223y@outlook.com', TRUE, 'password456343456', 'EMPLOYER');
INSERT INTO USER (email, is_active, password, roles) VALUES('bob123@hotmail.com', TRUE, 'Iamascientistpassword', 'EMPLOYER');
INSERT INTO USER (email, is_active, password, roles) VALUES('afis45@outlook.com', TRUE, 'PASSWORD123pass', 'EMPLOYER');

INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(1, 'Sotiris', 'Yiallourides', '993443563', 'Agia Paraskevi', 'Cyprus', 'Nicosia', '2054', '12/03/1199', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(2, 'Pavlos', 'Pavlides', '932533563', 'Agia Thekla', 'Cyprus', 'Larnaca', '2324', '18/09/1993', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(3, 'Aris', 'Apeitou', '32325363', 'Simonos', 'Cyprus', 'Limassol', '1224', '03/02/1996', 'Male');

INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(1, 'Not experience at all', 'Bsc Computer Science degree','cv.pdf', 'coverletter.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(2, 'A bit of experience', 'Bsc Applied Software Engineering degree','cvs.pdf', 'coverletterds.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(3, 'A lot of experience with clients', 'Bsc Applied Software Engineering degree','cvsds.pdf', 'coverldetterds.pdf');

INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(4, 'Bob', 'Bobby', 'Bob Bobby','Google','bobby@cycom.vom.cy', 'Project Manager', '223231443563', 'I been working for 4 years', 'Agia Paraskevi', 'Cyprus', 'Nicosia', '2054');
INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(5, 'Renos', 'Beckham', 'Renos Beckham','Cycom','renos@google.com.cy', 'CEO', '2212323443563', 'I been working for 10 years', 'Oroklini', 'Cyprus', 'Larnaca', '2345');
INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(6, 'Christos', 'Christou', 'Christos Christou','Metaverse','christouc@cycom.vom.cy', 'Project Manager', '223213443563', 'I been working for 2 years', 'Agia Varvara', 'Cyprus', 'Nicosia', '1454');

INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(1, 'Entered', 'Senior Software Engineer', 'Programming', 'Cycom','Renos Panteli', 'Larnaca', '30000','12/02/2021', '15/02/2021', '15/03/2021', '01/05/2021', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Junior Software Engineer', 'Programming','Cycom','Aggelos Charisteas', 'Nicosia', '22000','12/02/2021', '15/02/2021', '15/03/2021', '01/05/2021', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Expired', 'Graduate Software Engineer', 'Programming','Microsoft','Renos Panteli', 'Limassol', '20000','12/02/2021', '15/02/2021', '15/03/2021', '01/05/2021', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Entered', 'Graduate Software Engineer', 'Programming','Facebook','Aggelos Charisteas', 'Limassol', '20000','12/02/2021', '15/02/2021', '15/03/2021', '01/05/2021', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Cancelled', 'Graduate Software Engineer', 'Programming','Google','Renos Panteli', 'Limassol', '20000','12/02/2021', '15/02/2021', '15/03/2021', '01/05/2021', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');

INSERT INTO APPLICATION (applicant_id, job_id, apply_date, application_status) VALUES(1, 2, '21/09/2021',"Accepted");
INSERT INTO APPLICATION (applicant_id, job_id, apply_date, application_status) VALUES(2, 2, '21/09/2021',"Rejected");
INSERT INTO APPLICATION (applicant_id, job_id, apply_date, application_status) VALUES(1, 3, '21/09/2021',"Received");
INSERT INTO APPLICATION (applicant_id, job_id, apply_date, application_status) VALUES(3, 4, '21/09/2021',"Rejected");
INSERT INTO APPLICATION (applicant_id, job_id, apply_date, application_status) VALUES(3, 5, '21/09/2021',"Accepted");

--Trigger that sets Status according to Starting Date
DROP TRIGGER IF EXISTS setActiveInactiveJob;
DELIMITER //
CREATE TRIGGER setActiveInactiveJob
    BEFORE UPDATE ON JOB
    FOR EACH ROW BEGIN
    IF (NEW.ACTIVE_DATE <= current_date()) THEN
		SET NEW.STATUS = "Active";
END IF;
IF (NEW.ACTIVE_DATE > current_date()) THEN
		SET NEW.STATUS = "Entered";
END IF;
END//
  DELIMITER ;
