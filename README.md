# 💰 Payment-Management-System

# Chapter 1: Introduction

The Payment Management System is a crucial application designed to serve as a seamless gateway between two registered banks, facilitating efficient payment processing. With the growing need for secure and convenient financial transactions, this system aims to bridge the gap and streamline payment operations between various banks, offering a user-friendly experience for both customers and administrators.

By maintaining registered accounts linked to specific banks and currencies, the system ensures a high level of financial transparency and accuracy. For ease of use, the initial account balance will begin at 3000.0, and the supported currencies will include PKR, EUR, and USD, with constant conversion rates between them.

The system will cater to two types of users: administrators and regular users. Administrators will have extensive privileges, including creating and managing banks, accessing account details, payment history, and payment status. On the other hand, regular users will be able to sign up, create accounts, perform transactions between registered banks, and manage their own account information securely.

Overall, this Payment Management System promises to revolutionize financial interactions, ensuring convenience, security, and efficiency for all users involved.

## 1.1. Purpose of this Document

The objective of this document is to provide a comprehensive overview of the Payment Management System project. It was undertaken by a recent graduate with the aim of honing skills in Java and the Struts2 framework, along with its practical implementation in industry-level projects. Additionally, this document serves as a guide to assist others in embarking on their initial journey in developing web server applications using the Struts2 framework.

## 1.2. Intended Audience

This project is for Bitlatic Technologies and its employees. Also, it can be used as a reference guide and a project report by other peers who wish to develop a web server application.

## 1.3. Definitions and Acronyms

- PMS: Payment Management System
- PKR: Pakistani Rupee
- USD: United States Dollar
- EUR: Euro
- SQL: Structured Query Language
- Js: JavaScript
- HTML: Hyper Text markup Language
- CSS: Cascading Style Sheet

# Chapter 2: Project Vision
This chapter is about the breakdown of the project’s vision, Payment Management System.

## 2.1. Problem Domain Overview
Payment Management System shortly read as PMS is a web server application which aims at providing online payment services to its clients. The system comprises of several banks and middle services which they will provide in order to transfer money from one account to another.

## 2.2. Problem Statement
At times, it is difficult for the people to send their payments due to severe weather conditions, political instabilities or maybe because of a public holiday. This application will allow them to access the interface and transfer money from one account to another with just a click of a button. All they need to do is trust us and enter their credentials.

## 2.3. Goals and Objectives
The main objective and motivation behind creating this project is as follows:

- Provide a platform to the users to transfer payments.
- Provide a hassle-free environment to the people to make their payments secure.
- Allow the users to enter their credentials and access multiple banks.

## 2.4. Project Scope
The main technology being used is Java along with Struts2 framework. The front end and back-end will be done using JavaScript, HTML, CSS and Java. The web server used is Apache Tomcat.

## 2.5. Constraints
The following are the constraints for the Payment Management System:

- It can only send payment in one account at a time.
- It can only send payments in selected banks.
- The initial balance for each balance is 3000.0 despite the currency.

# Chapter 3: Software Requirement Specifications

This chapter mentions the software requirements of our project which includes functional requirements, non-functional requirements, assumptions, business, stakeholders, and risk analysis.

## 3.1. List of Features

The major features of the PMS are as follows:

- Create a new Bank
- Get list of banks
- Create a new Account
- Get list of accounts
- Get details of an account
- Update an account
- Delete an account
- Get the payment history of an account
- Get account balance
- Create payments between two banks
- Get list of payments
- Get payment details of any payment
- Get payment status of any payment

## 3.2. Functional Requirements

The functional requirements fully describe the external behavior of the system. The functional requirements of our system are as follows:

- The system shall allow the admin to create a bank.
- The system shall allow the admin to get a list of banks.
- The system shall allow the admin to get a list of accounts.
- The system shall allow the admin to get details of any account.
- The system shall allow the admin to get the payment history of an account.
- The system shall allow an admin to get the account balance.
- The system shall allow an admin to get all payments.
- The system shall allow an admin to get payment details of the provided payment ID.
- The system shall allow an admin to get payment status of the provided payment ID.
- The system shall allow a user to get a list of banks.
- The system shall allow a user to create an account.
- The system shall allow a user to get details of his account.
- The system shall allow a user to update their information.
- The system shall allow a user to delete his account.
- The system shall allow a user to get the payment history of his account.
- The system shall allow a user to get the balance of his account.
- The system shall allow a user to create payment between his account and any registered account.
- The system shall allow a user to get payment details of the payment ID related to his account.
- The system shall allow a user to get payment status of the payment ID belonging to his account.

## 3.3. Quality Attributes

The quality attributes are also known as non-functional attributes.

## 3.4. Non-Functional Requirements

- **Usability:** The interface of the application will be user-friendly.
- **Maintainability:** If the system fails to load, then only a restart will restore its functionality. If our users face any problem during the application use, they can simply report it so that we can look at those issues and fix them.
- **Compatibility:** The application is a website and is compatible with Firefox, Chrome, and Safari.

## 3.5. Hardware and Software Requirements

### 3.5.1. Hardware Requirements

- Internet
- Working PC/Laptop

### 3.5.2. Software Requirements and Programming Languages

- Eclipse
- Lucid chart
- Office Libre
- HTML, CSS
- Java
- Struts2
- H2 Database

## 3.6. Risk Analysis

- Internet availability
- Communication delays
- Schedule overrun
- Application crash
- Lack of an adequate development and testing environment

# Chapter 4: High Level and Low-Level Design

## 4.1. System Overview

The Payment Management System serves as a crucial gateway, facilitating seamless transactions between registered banks. With the primary objective of secure fund transfers, the system ensures robust functionality and privileges for both Admin and User roles. 

### Admin Role:
- Create new banks, establishing connections for payment processing.
- Retrieve a comprehensive list of registered banks, aiding in informed decision-making.
- Access an overview of all registered accounts, facilitating effective management.
- Obtain detailed information about specific user accounts, promoting thorough account monitoring.
- Retrieve the payment history associated with a particular account, aiding in transparency.
- Check the account balance, an essential aspect of financial tracking.
- Access a comprehensive record of all payments, ensuring a complete overview.
- Obtain specific details regarding a payment using its unique identifier, enhancing accountability.
- Verify the status of a payment using its provided identifier, ensuring transaction certainty.

### User Role:
- Access to a list of registered banks, facilitating informed choice.
- Seamless account creation, enabling Users to establish their financial presence within the system.
- Detailed account insights, providing a comprehensive view of their account status.
- The flexibility to update their account information as required, ensuring up-to-date details.
- Account deletion option, if needed, with careful consideration.
- Retrieval of personal payment history, fostering a transparent transaction experience.
- Real-time account balance checks, assisting Users in managing their finances.
- The ability to initiate payments between their account and any registered bank's account, streamlining transfers.
- In-depth payment detail access using the provided payment identifier, ensuring transaction clarity.
- Conveniently verifying the status of a payment, offering reassurance and transparency.

## 4.2. Design Considerations

The project's design considerations include making sure that our system is simple to use and that it is straightforward to understand. The user interface needs to be clear so that nobody ever feels confused or unable to proceed.

### 4.2.1. Assumptions and Dependencies

- Related software or hardware: This application can work on any mobile device since it is a mobile application.
- Operating systems: The OS required for this application's working is either IOS or Android.

### 4.2.2. General Constraints

The global and general constraints are as follows:
- Access to smartphones
- English language
- Internet connection is important

### 4.2.3. Goals and Guidelines

- The main goal of this project is to provide ease of access to the users to help themselves with the application.
- The emphasis on speed vs memory because the users would prefer an application that runs fast and is quick in response.
- The application should give a good feel so as to leave a good aesthetic impression on the users and so that they can use it again and again.
- The application should be user-friendly for all types of users so that every person can use it efficiently.

### 4.2.4. Development Methods

The methodology used in making this application is the waterfall development methodology. Waterfall methodology is used in making the application as the project guidelines are given and clear. Also, the requirements will not change, thus waterfall methodology is the best.

## 4.3. Architectural Strategies

To develop a Web Application, we will be using Java. The user interface of our application will be designed according to the user convention and will be user-friendly. The color selection will be done carefully considering all the factors and types of people who would be using our application.

Our system will have some error screens as well. The framework will be Struts2 framework. On the front end, JSP pages will be created to bind the actions with their respective functionality. Also, at the back end, Java classes and Java Action classes will be used. The database used is an in-memory H2 database.

# Chapter 5: Database Design

This chapter mentions the design of the database along with the data dictionary.

## 5.1. ER Diagram
<p align="center">
  <img src="/images/ERDiagram.png" alt="Image description"
</p>
<p align="center">
  <strong>Figure 1: ER Diagram</strong>
</p>

<p align="center">
  <i>The following screen is a visual representation of the Database of PMS.</i>
</p>

## 5.2. Database Dictionary

<p align="center">
  <strong>Table 1: Data Dictionary Table</strong>
</p>

<p align="center">
  <i>The following table shows the complete data dictionary of each attribute in the Database.</i>
</p>

<table align="center">
  <tr>
    <th>Entity</th>
    <th>Attribute</th>
    <th>Data Type</th>
    <th>Nullable</th>
    <th>Relation To</th>
    <th>Relation Type</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Admin</td>
    <td>username</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Primary key</td>
  </tr>
  <tr>
    <td></td>
    <td>password</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Password</td>
  </tr>
  <tr>
    <td>User</td>
    <td>username</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Primary key</td>
  </tr>
  <tr>
    <td></td>
    <td>password</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Password</td>
  </tr>
  <tr>
    <td>Bank</td>
    <td>bankid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Primary key</td>
  </tr>
  <tr>
    <td></td>
    <td>bankname</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Name of the bank</td>
  </tr>
  <tr>
    <td>Accounts</td>
    <td>accountid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td>Bank</td>
    <td>1:N</td>
    <td>Primary key</td>
  </tr>
  <tr>
    <td></td>
    <td>username</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Username</td>
  </tr>
  <tr>
    <td></td>
    <td>bankid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Foreign key</td>
  </tr>
  <tr>
    <td></td>
    <td>balance</td>
    <td>double</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Money in account</td>
  </tr>
  <tr>
    <td></td>
    <td>currency</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Currency</td>
  </tr>
  <tr>
    <td>Payments</td>
    <td>paymentid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td>Accounts</td>
    <td>1:N</td>
    <td>Primary key</td>
  </tr>
  <tr>
    <td></td>
    <td>senderaccountid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Foreign key</td>
  </tr>
  <tr>
    <td></td>
    <td>receiveraccountid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Foreign key</td>
  </tr>
  <tr>
    <td></td>
    <td>amount</td>
    <td>double</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Amount transferred</td>
  </tr>
  <tr>
    <td></td>
    <td>status</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Approved/Reject</td>
  </tr>
  <tr>
    <td>Balance</td>
    <td>accountid</td>
    <td>varchar</td>
    <td>Not null</td>
    <td>Accounts</td>
    <td>1:N</td>
    <td>Primary key-Foreign key</td>
  </tr>
  <tr>
    <td></td>
    <td>balance</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Amount in the account</td>
  </tr>
  <tr>
    <td></td>
    <td>currency</td>
    <td>varchar</td>
    <td>Not null</td>
    <td></td>
    <td></td>
    <td>Currency</td>
  </tr>
</table>

# Chapter 6: Implementation and Test Cases

This chapter talks about the implementation details for the prototype designed for the application and the test cases and their descriptions. Also, it enlists the test metrics which have been used and fulfilled.

## 6.1. Design Implementation

The application will start with the welcome page which has a login page where the user and admin will be allowed to enter his credentials. If the user is new to the application, then he will be asked to signup page. The guest user will be allowed to enter his credentials and then proceed to the login page. The admin can only login with the given credentials as there is only one admin, while the user has to signup before login. So, for this purpose, once the application runs, only one user can create his account. When the admin logs in to the portal, he will be allowed to view several options such as create banks, list accounts etc. On the other hand, the user will be allowed to perform several functions such as create an account and list banks. Both the user and admin roles have a logout button which will be used to sign out from the application.

## 6.2. Test Case Design and Description
This section lists all the important test cases along with their descriptions in a tabular form, the standard format followed in the industry.


