$$HOME PAGE
<img width="944" alt="Screenshot 2025-02-24 100245" src="https://github.com/user-attachments/assets/cac0e2d6-0a81-49b9-a3a6-95904db23087" />

$ADMIN DASHBOARD
<img width="943" alt="Screenshot 2025-02-24 100351" src="https://github.com/user-attachments/assets/d03c3781-23c6-4331-a45e-04f3899c7162" />

*User Management
<img width="950" alt="Screenshot 2025-02-24 100522" src="https://github.com/user-attachments/assets/1b4f1834-2e2f-474a-a571-39d1f92dbda3" />

*Book List
<img width="946" alt="Screenshot 2025-02-24 100614" src="https://github.com/user-attachments/assets/c87cccda-d89b-4879-8030-fb1729b693fe" />

*Borrow Records
<img width="947" alt="Screenshot 2025-02-24 100734" src="https://github.com/user-attachments/assets/26b8fa4a-e44d-4b5f-9909-ace439ae5972" />

$USER ADMIN
<img width="932" alt="Screenshot 2025-02-24 102149" src="https://github.com/user-attachments/assets/fa257174-19f7-441a-bbdd-0af8ffb00113" />

*Book List
<img width="947" alt="Screenshot 2025-02-24 100927" src="https://github.com/user-attachments/assets/a4c5db7d-24b4-49e8-b29c-3b61f59a4a9f" />

*User Borrow Records
<img width="944" alt="Screenshot 2025-02-24 101013" src="https://github.com/user-attachments/assets/63dcd26e-93da-427f-b5ab-a8f7a10ba81a" />



📌 Project Overview

The Library Management System is a Spring Boot-based application that facilitates the management of books, users, and borrow records. It enables role-based access for admins and users, ensuring a structured and efficient library experience.

🛠️ Tech Stack

Backend: Java, Spring Boot, Spring MVC, Spring Security, Spring Data JPA

Frontend: Thymeleaf, Bootstrap, HTML, CSS, JavaScript

Database: Oracle SQL

Security: Spring Security with BCrypt Password Encoding

Testing: JUnit, Mockito


⚙️ Features & Functionalities

1️⃣ User Management

✔ Admin Role: Add, edit, delete, and manage users

✔ User Role: Self-registration and login

2️⃣ Book Management

✔ Admin: Add, update, delete books

✔ Users: Search and borrow books

3️⃣ Borrow & Return Management

✔ Issue & return books with due date tracking

✔ Overdue detection for unreturned books

4️⃣ Security & Authentication

✔ Role-based access control (RBAC) using Spring Security

✔ BCrypt Password Hashing for secure authentication


📂 Project Structure

Directory structure:
└── alphasierra24-lib-mgmt/
    ├── README.md
    ├── pom.xml
    ├── .factorypath
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/
        │   │       └── cts/
        │   │           └── LibraryManagementSystem/
        │   │               ├── LibraryManagementApplication.java
        │   │               ├── ThyemleafController/
        │   │               │   ├── AuthenticationController.java
        │   │               │   ├── HomeController.java
        │   │               │   ├── ThyBorrowController.java
        │   │               │   ├── ThyCatalogController.java
        │   │               │   └── UserManagementController.java
        │   │               ├── config/
        │   │               │   ├── MyUserDetailsService.java
        │   │               │   └── SecurityConfig.java
        │   │               ├── controller/
        │   │               │   ├── BorrowController.java
        │   │               │   ├── CatalogController.java
        │   │               │   └── UsersController.java
        │   │               ├── dto/
        │   │               │   ├── BorrowRecordDTO.java
        │   │               │   ├── CatalogDTO.java
        │   │               │   └── UsersDTO.java
        │   │               ├── model/
        │   │               │   ├── BorrowRecordModel.java
        │   │               │   ├── CatalogModel.java
        │   │               │   ├── Role.java
        │   │               │   └── UsersModel.java
        │   │               ├── repository/
        │   │               │   ├── BorrowRecordRepository.java
        │   │               │   ├── CatalogRepository.java
        │   │               │   └── UsersRepository.java
        │   │               └── service/
        │   │                   ├── BorrowService.java
        │   │                   ├── BorrowServiceImpl.java
        │   │                   ├── CatalogService.java
        │   │                   ├── CatalogServiceImpl.java
        │   │                   ├── UsersService.java
        │   │                   └── UsersServiceImpl.java
        │   └── resources/
        │       ├── application.properties
        │       └── templates/
        │           ├── admin-users.html
        │           ├── book-form.html
        │           ├── book-list.html
        │           ├── borrow-records.html
        │           ├── borrow-request.html
        │           ├── dashboard.html
        │           ├── home.html
        │           ├── login.html
        │           ├── manage-returns.html
        │           ├── register.html
        │           ├── return-book.html
        │           └── user.html
        └── test/
            └── java/
                └── com/
                    └── as/
                        └── LibraryManagementSystem/
                            └── AppTest.java



🚀 Setup & Installation

1️⃣ Clone the Repository

git clone https://github.com/your-repo/library-management-system.git

cd library-management-system

2️⃣ Configure Database (Oracle SQL)

Set up Oracle SQL and update application.properties with your database credentials:

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE

spring.datasource.username=your_username

spring.datasource.password=your_password


3️⃣ Run the Application

mvn spring-boot:run

or

java -jar target/library-management-system.jar


🔗 API Endpoints

📌 Future Enhancements

🔹 Email Notifications for due dates

🔹 REST API Endpoints for external integration

🔹 Docker Support for containerized deployment


👨‍💻 Contributors

Aditya Satish Shelar (Spring Boot, Java, Thymeleaf, Security)

📄 License

This project is licensed under MIT License.

