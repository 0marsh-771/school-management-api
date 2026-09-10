# Student Management API

A Spring Boot REST API for managing students, teachers, and directors with role-based authorization.

## Features

* CRUD operations for students
* Role-based access control
* Spring Security authentication
* Three roles:

  * STUDENT
  * TEACHER
  * DIRECTOR
* Swagger/OpenAPI documentation
* MySQL database integration
* Spring Data JPA

## Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger/OpenAPI

## Authorization Rules

### STUDENT

* View data only

### TEACHER

* View data
* Create and update records
* Cannot delete records

### DIRECTOR

* Full access to all endpoints

## API Documentation

Swagger UI is available after running the application.

## Learning Purpose

This project was built to practice Spring Boot, Spring Security, JPA, REST APIs, and role-based authorization.
