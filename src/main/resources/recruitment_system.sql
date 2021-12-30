-- Step 4: Create a `recruitment_system` database
CREATE SCHEMA IF NOT EXISTS `recruitment_system`;
use `recruitment_system`;

-- Step 6: Add trigger and records into the system
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
  
INSERT INTO USER (email, is_active, password, roles) VALUES('sotirisy@hotmail.com', TRUE, 'password456343456', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('pavlospavlides@outlook.com', TRUE, 'Iamascientistpassword', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('arisapeitou@gmail.com', TRUE, 'PASSWORD123pass', 'APPLICANT');
INSERT INTO USER (email, is_active, password, roles) VALUES('aris223y@outlook.com', TRUE, 'password456343456', 'EMPLOYER');
INSERT INTO USER (email, is_active, password, roles) VALUES('bob123@hotmail.com', TRUE, 'Iamascientistpassword', 'EMPLOYER');
INSERT INTO USER (email, is_active, password, roles) VALUES('afis45@outlook.com', TRUE, 'PASSWORD123pass', 'EMPLOYER');

INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(1, 'Sotiris', 'Yiallourides', '993443563', 'Agia Paraskevi', 'Cyprus', 'Nicosia', '2054', '1999-09-03', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(2, 'Pavlos', 'Pavlides', '932533563', 'Agia Thekla', 'Cyprus', 'Larnaca', '2324', '1993-09-16', 'Male');
INSERT INTO APPLICANT (user_id, first_name, last_name, phone_number, address, country, city, postcode, date_of_birth, gender) VALUES(3, 'Aris', 'Apeitou', '32325363', 'Simonos', 'Cyprus', 'Limassol', '1224', '1996-04-02', 'Male');

INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(1, 'Not experience at all', 'Bsc Computer Science degree','cv.pdf', 'coverletter.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(2, 'A bit of experience', 'Bsc Applied Software Engineering degree','cvs.pdf', 'coverletterds.pdf');
INSERT INTO APPLICANT_RESUME (applicant_id, experience, education, cv, cover_letter) VALUES(3, 'A lot of experience with clients', 'Bsc Applied Software Engineering degree','cvsds.pdf', 'coverldetterds.pdf');

INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(4, 'Bob', 'Bobby', 'Bob Bobby','Google','bobby@cycom.vom.cy', 'Project Manager', '223231443563', 'I been working for 4 years', 'Agia Paraskevi', 'Cyprus', 'Nicosia', '2054');
INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(5, 'Renos', 'Beckham', 'Renos Beckham','Cycom','renos@google.com.cy', 'CEO', '2212323443563', 'I been working for 10 years', 'Oroklini', 'Cyprus', 'Larnaca', '2345');
INSERT INTO EMPLOYER (user_id, first_name, last_name, contact_name, company, company_email, business_type, telephone_number, company_profile, address, country, city, postcode) VALUES(6, 'Christos', 'Christou', 'Christos Christou','Metaverse','christouc@cycom.vom.cy', 'Project Manager', '223213443563', 'I been working for 2 years', 'Agia Varvara', 'Cyprus', 'Nicosia', '1454');

INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(1, 'Entered', 'Senior Software Engineer', 'Programming', 'Cycom','Renos Panteli', 'Larnaca', '30000','2021-12-02', '2021-12-02', '2022-02-01', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Junior Software Engineer', 'Programming','Cycom','Aggelos Charisteas', 'Nicosia', '22000','2021-12-04', '2021-12-04', '2022-01-01', '2022-03-23', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Expired', 'Graduate Software Engineer', 'Programming','Microsoft','Renos Panteli', 'Limassol', '20000','2021-12-05', '2021-12-05', '2022-03-01', '2022-03-02', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Entered', 'Graduate Software Engineer', 'Programming','Facebook','Aggelos Charisteas', 'Limassol', '20000','2021-11-02', '2021-11-06', '2021-12-25', '2022-01-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Cancelled', 'Graduate Software Engineer', 'Programming','Google','Renos Panteli', 'Limassol', '20000','2021-10-02', '2021-11-05', '2021-12-27', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(1, 'Active', 'Senior Software Engineer', 'Programming','Microsoft','Renos Panteli', 'Limassol', '20000','2021-11-07', '2021-11-07', '2021-12-26', '2022-01-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Senior Software Engineer', 'Programming','Facebook','Aggelos Charisteas', 'Limassol', '20000','2021-12-01', '2021-12-01', '2021-12-29', '2022-02-21', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Active', 'Senior Software Engineer', 'Programming','Google','Renos Panteli', 'Limassol', '20000','2021-09-13', '2021-09-13', '2022-02-01', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(1, 'Active', 'Graduate Software Engineer', 'Programming','Microsoft','Renos Panteli', 'Limassol', '20000','2021-12-02', '2021-12-02', '2022-02-01', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Devops Engineer', 'Programming','Facebook','Aggelos Charisteas', 'Limassol', '20000','2021-12-02', '2021-12-02', '2022-02-01', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Active', 'Web Developer', 'Programming','Google','Renos Panteli', 'Limassol', '20000','2021-12-03', '2021-12-03', '2022-01-01', '2022-01-25', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(1, 'Active', 'UX Designer', 'Programming','Microsoft','Renos Panteli', 'Larnaca', '20000','2021-12-05', '2021-12-05', '2022-02-05', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Mechanical Engineer', 'Programming','Facebook','Aggelos Charisteas', 'Paphos', '20000','2021-11-20', '2021-11-20', '2022-02-01', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(3, 'Active', 'Web Developer', 'Programming','Google','Renos Panteli', 'Nicosia', '20000','2021-11-29', '2021-11-25', '2021-11-26', '2022-03-01', 'We’re looking for', 'Provide full diary and administration support to the Group CEO;
Be prepared to challenge the status quo if needed and make suggestions for improvement
This is not a full definition of the role but covers the main aspects and drivers for success.','Previous PA experience is essential 
 / problems before they arise, and can anticipate opportunities to support the wider team
Able to identify issues quickly and come up with effective solutions', 'Flexibility to support the changing workload of the department and managers, and a willingness to work outside of core hours when necessary
 using Microsoft packages such as Word, Excel, PowerPoint and Outlook
Confidentiality and discretion', 'We do not have a set salary for this position, as it will be dependent on the successful candidate’s experience. We are happy to see CVs from all candidates who meet the requirements and will be happy to discuss the remuneration package.');
INSERT INTO JOB (employer_id, status, title, department, company, managed_by, location, salary, post_date, active_date, expiry_date, starting_date, description, requirements, essential_criteria, desirable_criteria, salary_and_benefits) VALUES(2, 'Active', 'Senior Software Engineer', 'Programming','Cycom','Aggelos Charisteas', 'Limassol', '20000','2021-11-29', '2021-11-25', '2021-11-26', '2022-03-01', 'This is the description', 'These are the requirements','The essential criteria are', 'The desirable criteria are', 'The salary and benefits are');

INSERT INTO APPLICATION (applicant_id, resume_id, job_id, apply_date, application_status) VALUES(1, 1, 2, '2021-10-25',"Accepted");
INSERT INTO APPLICATION (applicant_id, resume_id, job_id, apply_date, application_status) VALUES(2, 2, 2, '2021-09-20',"Entered");
INSERT INTO APPLICATION (applicant_id, resume_id, job_id, apply_date, application_status) VALUES(1, 1, 3, '2020-06-04',"Received");
INSERT INTO APPLICATION (applicant_id, resume_id, job_id, apply_date, application_status) VALUES(3, 3, 4, '2021-05-05',"Rejected");
INSERT INTO APPLICATION (applicant_id, resume_id, job_id, apply_date, application_status) VALUES(3, 3, 5, '2021-04-03',"Accepted");
