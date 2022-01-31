# Recruitment System

This is the Recruitment System. It is a web application that allows employers(companies) to 
post/open vacancies and also applicants to be able to search and apply to a vacancy.  
 
An applicant can:
- Register as an applicant
- Login using its profile
- Edit its profile details
- Search through all active vacancies and apply
- Monitor its vacancies

An employer(company) can:
- Register as an employer
- Login using its company profile 
- Edit its profile details 
- Add a new vacancy
- Monitor its vacancies
## Tech Stack

**Front-end:** React (Runs on Node.js)

**Back-end:** Spring Boot

**Database:** MariaDB, H2 Database

**Tools:** Maven, npm, Postman, Structurizr, Hotjar

**Libraries:** Axios, Bootstrap, React-Bootstrap, TailwindCSS, Lodash, React-Icons, React-Paginate, React Hook Form, Lombok


## Prototype

This is a prototype of the Recruitment system. A prototype is an initial creation of a 
product that shows the basics of what a product will look like, what the product will do, 
and how the product operates. A prototype is NOT the original product and it might have some
changes from the original product.

Click on the link below to see the prototype:

https://tx7lr4.axshare.com


## Architecture
 - [C4 Model - Context Diagram](https://git.cardiff.ac.uk/c1847017/recruitement_system/-/blob/main/src/main/resources/ArchitectureDiagrams/c4_level_1.jpg)
 - [C4 Model - Container Diagram](https://git.cardiff.ac.uk/c1847017/recruitement_system/-/blob/main/src/main/resources/ArchitectureDiagrams/c4_level_2.jpg)
 - [C4 Model - Component Diagram](https://structurizr.com/share/71432/aca6e278-8743-4f83-bbf1-2fc9479db556/diagrams#components)
 - [Project Architecture Diagram](https://git.cardiff.ac.uk/c1847017/recruitement_system/-/blob/main/src/main/resources/ArchitectureDiagrams/project_architecture_diagram.jpg)
 - [Database Architecture Diagram](https://git.cardiff.ac.uk/c1847017/recruitement_system/-/blob/main/src/main/resources/ArchitectureDiagrams/database_architecture.diagram.png)


## How to build/start/run/compile the project

#### The project was build/start/run/compile successfuly using:
- Java v11.0.11
- Node v14.17.3
- MariaDB v10.6
- Maven v3.8.1
- npm 7.24.1
- GIT 2.32.0
- MySQL Workbench 8.0 CE

#### To build/start/run/compile the project please follow the steps below:

HINT - You can also use the "How to build, run, compile the project.mp4" video for help. 
- [Deployment video](https://git.cardiff.ac.uk/c1847017/recruitement_system/-/blob/main/src/main/resources/DeploymentVideo/How%20to%20build,%20run,%20compile%20the%20project.mp4)


#### Step 1: Clone the repository
```bash
  git clone https://git.cardiff.ac.uk/c1847017/recruitement_system.git
```
#### Step 2: Go inside the directory
```bash
  cd recruitement_system
```
#### Step 3: Build the back-end server
```bash
  mvn clean install
```
#### Step 4: Create a `recruitment_system` database on MySQL. 
You can use the `recruitment_system.sql` script located in the `src/main/resources` folder and
run it using MySQL Workbench. 

NOTE: MariaDB connection should have these settings below:
- Hostname: localhost:3306
- Username: root 
- Password: comsc
  
(IMPORTANT: Run only these 2 lines of code)
```bash
  CREATE SCHEMA IF NOT EXISTS `recruitment_system`;
  use `recruitment_system`;
```

#### Step 5: Start the back-end server
```bash
  java -jar target/recruitment-system-project-2.1.1-SNAPSHOT.jar
```

#### Step 6: Run the remaining lines of the `recruitment_system.sql` script. (As seen below) 
(IMPORTANT: Run these only after the back end server has started)
```bash
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
  
  INSERT INTO USER (email, is_active, password, roles) VALUES('applicant@hotmail.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'APPLICANT');
  INSERT INTO USER (email, is_active, password, roles) VALUES('pavlospavlides@outlook.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'APPLICANT');
  INSERT INTO USER (email, is_active, password, roles) VALUES('arisapeitou@gmail.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'APPLICANT');
  INSERT INTO USER (email, is_active, password, roles) VALUES('aris223y@outlook.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'EMPLOYER');
  INSERT INTO USER (email, is_active, password, roles) VALUES('employer@hotmail.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'EMPLOYER');
  INSERT INTO USER (email, is_active, password, roles) VALUES('afis45@outlook.com', TRUE, '$2a$10$FK.cGmuYhcdo1ELXMrq/1.QukdhX89HD0gpaI2N8m3N8LIIKRwXyK', 'EMPLOYER');

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

```
#### Step 7: Build the front-end server
```bash
  cd src/main/java/com/example/recruitmentsystemproject/Presentation/front_end && npm install
```
#### Step 8: Start the front-end server
```bash
  npm start
```

Once the server is running, the website can be accessed from http://localhost:3000/careers.

- You are able to login as an Applicant using:
	
	  Email: applicant@hotmail.com
	  Password: password

- You are able to login as an Employer using:
	
	  Email: employer@hotmail.com
	  Password: password


## Documentation

### Java (Programming Language)
[Java documentation](https://docs.oracle.com/en/java/)

Java is considered to be one of the most popular programming languages. It is the language
that was used to develop the backend of the Recruitment system. Java was used because the 
chosen framework for the backend was Spring Boot which is a Java-based framework.

### JavaScript (Programming Language)

[JavaScript documentation](https://www.javascript.com/learn/strings)

JavaScript is one of the most widely used programming languages. It is the language that
was used to develop the frontend of the Recruitment system. JavaScript was used because I 
have had minimal experience on this language before, therefore it was a good opportunity 
to use it and learn more to enhance my skills as a Software Developer. In addition, 
JavaScript was used because of React, which is a JavaScript library.

### SQL (Structure Quary Language)
[SQL documentation](https://docs.data.world/documentation/sql/concepts/basic/intro.html)

SQL is a database programming language for quering and editing information stored in a 
certain database management system. It is the language that was used to develop the database
tier of the Recruitment system. SQL is also a standard language for dealing with Relational
Databases, and as MariaDB is a Relational Database Management System, SQL was the only option
to use. 

### React (Front End)
[React documentation](https://reactjs.org/docs/getting-started.html)

React (Runs on Node.js) was used for the frontend of the Recruitment system. One of the main reasons React
was chosen, was because I wanted to learn and try something new for my frontend. React is 
considered to be one of the most popular front-end frameworks (library) therefore it was 
a good opportunity to work with something that can enhance my skills as a Software Developer.
In addition, I always wanted to combine 2 programming languages together and find out about
the pros and cons of this decision.

#### Community Resources:
[Building Full-Stack Apps with React and Spring by Emmanuel Henri - LinkedIn Tutorial](https://www.linkedin.com/learning/building-full-stack-apps-with-react-and-spring/initialize-the-react-client-project?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to create a React project)

[React: Creating and Hosting a Full-Stack Site by Shaun Wassell - LinkedIn Tutorial](https://www.linkedin.com/learning/react-creating-and-hosting-a-full-stack-site/url-parameters-with-react-router?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to use React-Router)

[React.js Essential Training by Eve Porcello - LinkedIn Tutorial](https://www.linkedin.com/learning/react-js-essential-training/working-with-lists?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to work with lists, how to use useEffect and useState hooks and how to use the React testing library)

[React.js: Building an Interface by Ray Villalobos- LinkedIn Tutorial](https://www.linkedin.com/learning/react-js-building-an-interface-8551484/importing-json-data-as-a-variable?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to work with JSON data)

[React - The Complete Guide by Maximilian Schwarzmüller - OReilly Tutorial](https://learning.oreilly.com/videos/react-the/9781789132229/9781789132229-video26_2/) (How to work with React Hooks)

### Spring Boot (Back End)
[Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)

Spring Boot was used for the backend of the Recruitment system. One of the main reasons
Spring Boot was chosen, was because the idea was a bit more complicated that it should, 
that required many tasks to be completed within a small period of time. Using Spring Boot 
increased my productivity and reduced the development time allowing me to complete the base
of my backend within 1.5 weeks. In addition, I wanted to work with some Spring features 
which I didn't work in the past, such as Spring Security and Spring Profiles.  

#### Community Resources:
[Building Full-Stack Apps with React and Spring by Emmanuel Henri - LinkedIn Tutorial](https://www.linkedin.com/learning/building-full-stack-apps-with-react-and-spring/add-server-controller-for-cors?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to fix CORS issue and how to integrate Spring Boot with React.)

 ### MariaDB (Database Service)
[MariaDB documentation](https://mariadb.com/kb/en/documentation/)

MariaDB was used in production for the database service of the Recruitment system. One of the 
main reasons MariaDB was chosen, was because it integrates really well with Spring Boot using
Spring Data JPA feature, that allowed me to focus more on new technologies. In addition, 
MariaDB is known for being the most secure and reliable database management system, having 
also a high permormance on large amount of data.

 ### H2 Database (Database Service)
[H2 Database documentation](https://h2database.com/html/quickstart.html)

H2 Database was used in development for the database service of the Recruitment system. One 
of the main reasons H2 Database was chosen, was because it is an in-memory database which
allowed me to test the system extremely quick as every time the system was run, the data was 
created and erased automatically. H2 Database is also considered very convenient with Spring
Boot as it doesn't even need an installation.

 ### Maven (Project Management tool)
[Maven documentation](https://maven.apache.org/guides/)

Maven is a popular build tool to build, publish, and deploy several projects at once for a
better project management. Maven was used because I haven't had the chance to work with it in
the past, therefore it was a good opportunity. In addition, maven was used as it allows you
to add any dependency extremely easily and simplifies the process of project building. Moreover,
it is written in Java, therefore it is a more suitable choice for Java projects.

 ### npm (Package Manager tool for Node.js)
[npm documentation](https://docs.npmjs.com/)

Npm is the default package manager for the JavaScript runtime environment Node.js. It was 
used as a command-line utility for interacting with said repository that aids in package 
installation, version management, and dependency management. In addition, it was used to 
build and start the project. 

 ### Postman (API platform)
[Postman documentation](https://learning.postman.com/docs/publishing-your-api/documenting-your-api/)

Postman is a tool that was used on testing the API calls from the backend of the Recruitment
system. It was a really helpful tool as I was able to test my backend API calls before 
implementing the frontend. It was the first time that I've used it, and I would definitely
recommend it to anyone when working with APIs.

#### Community Resources:
[Building Full-Stack Apps with React and Spring by Emmanuel Henri - LinkedIn Tutorial](https://www.linkedin.com/learning/building-full-stack-apps-with-react-and-spring/introduction-to-postman?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to use Postman)


 ### GIT (Version Control System)
[GIT documentation](https://git-scm.com/doc)

GIT is an essential tool for any sized projects and was used to manage the source code of the 
project. GIT was used to monitor, track changes and issues as well as enabling other 
developers to have access on it, be able to download it and review it.      

 ### Lombok (Java library)
[Lombok documentation](https://projectlombok.org/api/lombok/package-summary.html)

Lombok was a really helpful library as it was used to minimize and remove boilerplate code 
from the backend. By using different annotations on the Model layer, it allowed me to save
time and effort by writing much less code. In addition, Lombok also was used to save space 
and improve readability of the source code.

 ### Structurizr (Visualising Software Architecture tool)
[Structurizr documentation](https://www.structurizr.com/help/documentation)

Structurizr is a tool that helps visualise, document and explore the software architecture of
the project as a C4 Model. It was used to visualise the Component Layer of the system by 
providing a programmatic approach (via API) that allowed for a faster implementation and 
better visualisation.

 ### Hotjar (Heatmap & Behavior Analytics tool)
[Hotjar documentation](https://www.hotjar.com/product/heatmaps/)

Hotjar is a tool that shows behavior analytics and feedback data to help you understand the
customers that uses the product. Hotjar was used to collect quantitative data from a heatmap
by recording where and how many times a user clicks on the website.

 ### Axios (HTTP Client library)
[Axios documentation](https://axios-http.com/docs/intro)

Axios is a promise-based HTTP Client for the browser that was used on the front end. 
Axios API was used to handle GET and POST requests between the frontend and the backend. I 
have never worked with Axios before, so it was a good opportunity to use this library. I found 
Axios really easy to use. 

#### Community Resources:

[React - The Complete Guide by Maximilian Schwarzmüller - OReilly Tutorial](https://learning.oreilly.com/videos/react-the/9781789132229/9781789132229-video10_3/) (How to work with Axios)

 ### React-Bootstrap (Component-based library)
[React-Bootstrap documentation](https://react-bootstrap.github.io/getting-started/introduction)

React-Bootstrap is component-based library which replaces the Bootstrap JavaScript.
React-Bootstrap was used on the frontend for all the components used in the system, to reduce
the amount of boilerplate code that would need to create simple tasks such as buttons and 
form elements. It also has shorter syntax that bootstrap for each component, therefore it 
requires less code. In addition, React-Bootstrap library was created specifically for a React 
project, therefore it is more suitable when using React.  

 ### Bootstrap (CSS Framework library)
[Bootstrap documentation](https://getbootstrap.com/docs/4.1/getting-started/introduction/)

Bootstrap is considered one of the most popular and widely used open-source frameworks for 
developing with HTML, CSS, and JS. Bootstrap was used on the frontend specifically for 
using the ready-made css it provides, for a faster web development. In addition, bootstrap
ready-made css were used because its grid system works across multiple devices with different 
sizes and supports all major browsers.      

 ### Tailwind CSS (CSS Framework library)
[Tailwind CSS documentation](https://tailwindcss.com/docs/installation)

Tailwind CSS is a “utility-first” CSS framework that provides a deep catalog of CSS classes 
and tools. Tailwind CSS was a library that I have never used before despite having heard
good reviews about it, so it was a good a opportinity to learn and use it. It was used on the
frontend specifically for only quick changes and fixes such as margin-top. Personally, I was
really impressed on how easy and fast I could fix something with just a "mt-5" which is 
equals to margin-top 1.25rem for example.

#### Community Resources:

[React.js: Building an Interface by Ray Villalobos- LinkedIn Tutorial](https://www.linkedin.com/learning/react-js-building-an-interface-8551484/installing-tailwind-css-in-react?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to work with Tailwind CSS)

 ### Lodash (JavaScript Utility library)
[Lodash documentation](https://lodash.com/docs/4.17.15)

Lodash is a Utility library that provides utility functions for common programming tasks
using a functional programming paradigm. Lodash was used specifically to handle large amount
of data and slice it into arrays. By using Lodash, I realised how cleaner and easier code is
written as it provides several built-in utility functions that allowed me to accomplish the 
task I wanted with a single line of code. 

#### Community Resources:

[What is Lodash and How it Works by Ray iEatWebsites - Youtube Tutorial](https://www.youtube.com/watch?v=YyxliogSwrM) (How to work with Lodash)

 ### React-Icons (Popular Icons library)
[React-Icons documentation](https://react-bootstrap.github.io/getting-started/introduction)

React-Icons is a library that includes popular icons to use for the project. React-Icons was
used so I do not have to install more than one icon library in the system, as it includes
everything. In addition, I found React-Icons library straight forward and easy to use, and 
it also allows you to include and import only the specific icons you want and not the whole 
library so that it saves space.

#### Community Resources:

[React.js: Building an Interface by Ray Villalobos - LinkedIn Tutorial](https://www.linkedin.com/learning/react-js-building-an-interface-8551484/using-react-icon-components?autoAdvance=true&autoSkip=false&autoplay=true&resume=false&u=35392996) (How to work with React-Icons)

 ### React-Paginate (Pagination library)
[React-Paginate documentation](https://openbase.com/js/react-paginate/documentation)

React-Paginate is one of the most popular pagination libraries to use when adding a 
pagination functionality to the system. React-Paginated library was used to add pagination 
functionality to all the tables of the system. Using this library I was able to add
pagination functionality to my tables much easier with less code.

#### Community Resources:

[React js Pagination With API Call Using React-Paginate by coderspirit - Youtube Tutorial](https://www.youtube.com/watch?v=kMuRr53RjcE&t=1316s) (How to work with React-Paginate)

 ### React Hook Form (Form Validation library)
[React Hook Form documentation](https://react-hook-form.com/api/)

React Hook Form is a small library that takes care of form validation, state management and
user data. React Hook Form was used to add form validation functionality to all the fields
where user input was needed on the system. React Hook Form library was very easy to implement
using the useForm hook which works with forms. React Hook Form library validates a form with
a reduced number of re-render, adds a security layer and is considered more performant.  

#### Community Resources:

[React Forms Full Tutorial - Validation, React-Hook-Form, Yup by PedroTech - Youtube Tutorial](https://www.youtube.com/watch?v=UvH70UkbyfE) (How to work with React-Hook-Form)

 ### Intellij IDEA (Integrated Development Environments tool)
[Intellij IDEA documentation](https://www.jetbrains.com/help/idea/discover-intellij-idea.html)

Intellij IDEA is a powerful IDE tool that helps with importing, developing, modelling, and 
deploying computer software. It was used for writing most of the code of the Recruitment 
system, including the frontend and backend. Even though is mostly suitable for Java, I found 
it really useful when working with Javascript too, as it provided massive assistance with 
the imports.  

 ### MySQL Workbench (Relational Database Design tool)
[MySQL Workbench](https://dev.mysql.com/doc/workbench/en/)

MySQL Workbench is a visual designing tool for creating, executing, and optimizing SQL 
queries. It was used for writing the code for database tier of the Recruitment system. In 
addition, it was used for testing purposes on the development phase.


## Business Decisions

1. Spring Boot is used for the backend of the system and acts as an API.

- Spring Boot project was created using Spring Initializr approach on Intellij IDEA

- Spring Features used:

      Spring Profiles, Spring Data JPA, Spring Security

2. Data is stored in MariaDB database.

- The schema of the Recruitment system is populated automatically, when the backend is run. It only requires a database to be created from before.
- MariaDB connection should have these settings below:

      Hostname: localhost:3306
      Username: root 
      Password: comsc

3. React is used for the frontend of the Recruitment system and interacts with the backend using Axios.

- React was created using Create React App approach
```bash
  npx create-react-app front_end
```

- React codebase (frontend) can be found inside the Presentation Layer of the project.

4. If recruitment_system.sql script is run:

- You are able to login as an Applicant using:
	
	  Email: applicant@hotmail.com
	  Password: password

- You are able to login as an Employer using:
	
	  Email: employer@hotmail.com
	  Password: password

## Authors

- [@c1847017](https://git.cardiff.ac.uk/c1847017)

