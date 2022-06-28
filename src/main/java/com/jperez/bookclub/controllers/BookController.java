package com.jperez.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jperez.bookclub.models.Book;
import com.jperez.bookclub.services.BookService;
import com.jperez.bookclub.services.UserService;

@Controller
public class BookController {
	
	@Autowired
    private BookService bookServ;
	
	@Autowired
    private UserService userServ;
	
	
	// --------------- Render ----------------------
	
	@GetMapping("book/new")
	public String createPage(HttpSession session, Model model) {
		
		if(session.getAttribute("uuid") == null) {
    		return "redirect:/";
    	}
		
		model.addAttribute("newBook", new Book());
		
		return "newBook.jsp";
	}
	
	@GetMapping("book/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(session.getAttribute("uuid") == null) {
    		return "redirect:/";
    	}
		
		model.addAttribute("book", bookServ.findBook(id));
		
		return "editBook.jsp";
	}
	
	@GetMapping("book/{id}")
	public String viewBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(session.getAttribute("uuid") == null) {
    		return "redirect:/";
    	}
		
		model.addAttribute("id", session.getAttribute("uuid"));
		model.addAttribute("book", bookServ.findBook(id));
		
		return "viewBook.jsp";
	}
	
	// ----------------- Action ----------------------

	@PostMapping("book/create")
	public String createBook(@Valid @ModelAttribute("newBook") Book newBook, 
            BindingResult result, Model model, HttpSession session) {
		
		if(result.hasErrors()) {
            return "newBook.jsp";
        }
		
		newBook.setUser(userServ.findUser((Long)session.getAttribute("uuid")));
		
		bookServ.save(newBook);
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("book/edit/{id}")
	public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session, @PathVariable("id") Long id) {
		
		if(result.hasErrors()) {
            return "editbook.jsp";
        }
		
		Book checkBook = bookServ.findBook(id);
		Long uuid = (Long)session.getAttribute("uuid");
		
		if(!uuid.equals(checkBook.getUser().getId())) {
			return "redirect:/dashboard";
		}
		
		book.setId(id);
		book.setUser(userServ.findUser((Long)session.getAttribute("uuid")));
		
//      OTHER METHODS
		
//		book.setId(checkBook.getId());
//		book.setUser(checkBook.getUser());
		
//		checkBook.setTitle(book.getTitle());
//		checkBook.setAuthorName(book.getAuthorName());
//		checkBook.setThoughts(book.getThoughts());
		
		bookServ.save(book);
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("book/delete/{id}")
	public void deleteBook(@PathVariable("id") Long id) {
		bookServ.deleteBook(id);
	}
	
}
