package com.jperez.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jperez.bookclub.models.LoginUser;
import com.jperez.bookclub.models.User;
import com.jperez.bookclub.services.BookService;
import com.jperez.bookclub.services.UserService;

@Controller
public class RouteController {
     @Autowired
     private UserService userServ;
     
     @Autowired
     private BookService bookServ;
     
    // --------------- Render ----------------------
    
    @GetMapping("/")
    public String index(Model model) {
    
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        
        return "index.jsp";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
    	
    	if(session.getAttribute("uuid") == null) {
    		return "redirect:/";
    	}
    	
    	model.addAttribute("user", userServ.findUser((Long) session.getAttribute("uuid")));
    	model.addAttribute("books", bookServ.getAll());
    	
    	return "dashboard.jsp";
    }
    
    // ----------------- Action ----------------------
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	User user = userServ.register(newUser, result);
        
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
         User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            
            return "index.jsp";
        }
    
        session.setAttribute("uuid", user.getId());
    
        return "redirect:/dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("uuid");
    	
    	return "redirect:/";
    }
    
}
