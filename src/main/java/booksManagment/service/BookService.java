package booksManagment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booksManagment.entity.Book;
import booksManagment.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bRepo;

	public Long getTotalCount() {
		return bRepo.count();
	}

	public List<Book> getAll() {
		return bRepo.findAll();
	}

	public Book get(Long id) {
		return bRepo.findById(id).get();
	}

	public Book addNew(Book book) {
		return bRepo.save(book);
	}

	public Book save(Book book) {
		return bRepo.save(book);
	}

	public void delete(Book book) {
		bRepo.delete(book);
	}

	public void delete(Long id) {
		bRepo.deleteById(id);
	}

}
