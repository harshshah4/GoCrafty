GoCrafty

About the project:-

An E-learning Management System for Art and Craft Courses. This project is a part of University of Maryland Software Engineering Course "ENPM-613'. 
The project is based on two architectures:
	Client-Server Model
	MVC Architecture
The Backend is developed in Spring Framework and Hibernate using Maven tool.
For the Frontend, we are using HTML, JavaScript, CSS, jQuery, and bootstrap.

Installation Guide:-

- Clone the repository from https://github.com/paryanimohit/GoCrafty/
- Install latest version of maven tool to build the project.
	- From the terminal while inside the project run mvn clean install. This will download all necessary dependencies from the web required for the project.
- Once the build is successful pick the jar from the target folder and place it in the server. The server should host this application project.
- The database schema is then imported into the database server. Freely available database server is MYSQL database server for desktop.
- On installation and import of schema, the mysql server configuration is to be changed in config.xml file.
	- The port number and credentials are to be changed along with the database name.
- Once the database server is up and running and configuration changes made in the config.xml, start the web server.
- The web server should be hosting the project as http://<webaddress or localhost>:<port>/GoCrafty/

User Guide:-

On running the application we see two options: Student and Instructor

For Instructor:-
- To register as an Instructor on the application use Create an Account.
- The special purpose field- "recruiter" is for the instructor who also wants to conduct a recruitment drive though our application.
- On registering one can login as the instructor now with the credentials set at the time of registering.
- The Instructor has the option to create Course and view the students registered in the course.
- The Instructor can later chose to modify the content of the course using modify course after clicking on the course it had created.
- The Instructor can also choose the delete the course under the same section. 

For Student:-
- To register as a student on the application use Create an Account.
- The special purpose field "apply for job" is basically for the user who wish to participate in the recruitment drive.
- On registering one can login as the student now with the credentials set at the time of registering.
- The student has the option to browse the available course catalog and also to register of any of the course, interested.
- The student can complete the course at their own pace and also ask doubts with the help of send email under the course page.
- The student can request for a certificate of completion at any time during the course using generate certificate. The score achieved by the student at that time shall be printed on the certificate.

If you are an Admin 
- you can login to the admin console using <domainname>/Gocrafty/home/showAdminLogin and enter the valid password set manually into the database.
- The admin can View/Add/Delete any Instructor user/Student User/Course as required to maintain the standard and integrity of the application.
- The admin can also reset the password on successful login into the application.

How to add Video and quiz?
- Create a google form quiz. Google form provides ways to create a quiz. Refer: https://support.google.com/docs/answer/7032287?hl=en
- Make the quiz public and save the link that appears in the quiz preview. That link will be used as quiz link.
- The first question in the quiz should be the Email ID
- Record the responses in excel file. The responses by default have scores in it along with email which will be used while generating the certificate.
- To enable successful communication with the sheets api, One should download the credentials from the same account where quiz is hosted. Refer: https://developers.google.com/sheets/api/quickstart/java?authuser=1
- Store the credentials under /resources/credentials/credentials.json. There is a dummy file existing by default.
- Authorize the connection when promted on clicking onto generate certificate.

Video Format: https://www.youtube.com/watch?v={youtube id} <br />
Quiz/Google forms format: https://docs.google.com/forms/d/e/{form id}/viewform <br />
Response/Google sheets format: https://docs.google.com/spreadsheets/d/{sheets id}/edit <br />
