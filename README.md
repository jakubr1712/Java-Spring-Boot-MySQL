# Book Management

## Description

REST API CRUD application facilitates the management of a book collection. Users can browse the list of books, add new items, edit existing ones, and delete them. The Thymeleaf-based user interface provides easy and intuitive navigation.

## Features

- **Viewing Book List**: Browse through all available books.
- **Adding Books**: Capability to add new books to the collection.
- **Editing Books**: Update information about books.
- **Deleting Books**: Remove books from the collection.
- **Success Action Messages**: Feedback after each successful operation.

## Technologies

- **Spring Boot** for backend logic.
- **Thymeleaf** for the user interface.
- **Maven** as a build and project management tool.
- **MySQL** as the database for persisting data.

## Installation

1. Clone the repository
2. Navigate to the project folder
3. Run the application using Maven:
   mvn spring-boot:run

## Usage

After launching the application, the user interface browser is available at `http://localhost:8080/`. Here, you can view, add, edit, and delete books.

# API Documentation

## Overview

REST API allows for the management of a book collection. Users can browse, add, update, and delete books.

## API Security

- **Key-Based Security**: REST API is secured with a key. Each request must include a valid API key in the header for authentication.
- Key:**MY-API-KEY**,Value:**my-api-key-123**

## Endpoints

### 1. Get All Books

- **URL**: `/api/books`
- **Method**: `GET`
- **Description**: Returns a list of all books.

### 2. Get Book by ID

- **URL**: `/api/books/{id}`
- **Method**: `GET`
- **Parameters**:
  - `id` (in URL): ID of the book
- **Description**: Returns detailed information about a specific book.

### 3. Add a New Book

- **URL**: `/api/books`
- **Method**: `POST`
- **Request Body**: Book data
- **Description**: Creates a new book.

### 4. Update a Book

- **URL**: `/api/books/{id}`
- **Method**: `PUT`
- **Parameters**:
  - `id` (in URL): ID of the book
- **Request Body**: book data
- **Description**: Updates the book data.

### 5. Delete a Book

- **URL**: `/api/books/{id}`
- **Method**: `DELETE`
- **Parameters**:
  - `id` (in URL): ID of the book
- **Description**: Deletes the book.
