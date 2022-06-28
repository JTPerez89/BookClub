package com.jperez.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jperez.bookclub.models.Book;
import com.jperez.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
    private BookRepository repo;
	
	public Book save(Book s) {
		return repo.save(s);
	}
	
	public List<Book> getAll(){
		return repo.findAll();
	}
	
    public Book findBook(Long id) {
        Optional<Book> optionalBook = repo.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    public void deleteBook(Long id) {
    	repo.deleteById(id);
    }
}
