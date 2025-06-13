# Cinematic - Online Cinema Ticket Booking System

## Overview

Cinematic is a web-based cinema ticket booking system developed using Java Server Pages (JSP) and Servlets. It enables users to browse movies, select seats, and book tickets online, ensuring a seamless and secure transaction process. Administrators can efficiently manage movie schedules, ticket slots, and user bookings. The system also incorporates secure authentication using Bcrypt password encryption and email notifications via SMTP.

---

## System Requirements

### 1️. Development Environment

- **JDK**: Java Development Kit (JDK 8, 11, or newer) _(Recommended: JDK 8)_
- **Apache Tomcat**: Version 9.x
- **Maven**: Version 2.3.x or newer _(If using dependency management)_

### 2️. Development Tools (IDE - Optional)

- **Eclipse IDE for Enterprise Java Developers**
- **IntelliJ IDEA Ultimate** _(Paid version required for full support)_
- **NetBeans** _(Integrated support for JSP & Servlets)_

### 3️. Database (Optional)

- **SQL Server** 2019 or newer

### 4️. Related Technologies (If Used)

- **JSTL** (JavaServer Pages Standard Tag Library) - Enhances JSP functionality
- **JDBC** (Java Database Connectivity) - Manages database interactions

---

## Technologies Used

- **Backend**: Java, Servlet, JSP
- **Frontend**: JavaScript, HTML, CSS, Bootstrap, jQuery
- **Database**: MS SQL
- **Security**: Bcrypt for password encryption
- **Services**: SMTP Email service for notifications

---

## Role & Contributions

**Role**: Leader (Full-Stack Developer)  
**Team Size**: 2 members

### Key Responsibilities:

- **Database Design** - Analyzed business requirements and structured the database schema.
- **Backend Development** - Implemented core booking system functionalities & admin panel.
- **Security** - Integrated Bcrypt for password hashing & email notifications via SMTP.
- **Frontend Development** - Designed and developed a user-friendly interface.

---

## Project Setup & Installation

### 1️. Clone the Repository

```sh
git clone https://github.com/huypqse/CENEMATIC.git
```

### 2️. Import Project (Using Eclipse or IntelliJ)

- Open your IDE and import the project as a **Maven project**.
- Ensure **Apache Tomcat** is installed and configured in the IDE.
- Set up **SQL Server** and import the required database schema.

### 3️. Build & Run the Project

#### Using Maven:

```sh
mvn clean install
```

#### Deploy on Tomcat:

1. Right-click the project → **Run on Server**.
2. Select **Apache Tomcat** and start the server.

#### Access the Application:

- **User Portal**: [http://localhost:8080/cinematic](http://localhost:8080/cinematic)
- **Admin Panel**: [http://localhost:8080/admin](http://localhost:8080/admin)

---

## GitHub Repository

[Cinematic on GitHub](https://github.com/sonnamnguyen/cinema-house-cinema-web-project)

---

## Contact

For inquiries or suggestions, feel free to reach out:
**Email**: [sonnamsonnam402@gmail.com](mailto:sonnamsonnam402@gmail.com)  
 **LinkedIn**: [My LinkedIn Profile](linkedin.com/in/son-nam-nguyen-0a8094354)

### new updated by Khoi - 13.06.2025

For introduction, this is our website for cinematic during the time we learn Java Servlet + JSP at FPT \_ semester 4

# recommend:

username: sa
password: 12245

1. create DB
2. Download Apacha 9.x -> 10+ change to jakarta not javax
3. javafx.Pair -> create new class Pair (already update)
4. Tool -> Server -> add Apache Tomcat
   server: 8080
   shutdown: 8005
5. add lib .jar to src/WEB-INF/lib
6. Project -> Properties -> Run -> Context Path /Cenematic1
