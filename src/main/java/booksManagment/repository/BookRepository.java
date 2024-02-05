package booksManagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booksManagment.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}