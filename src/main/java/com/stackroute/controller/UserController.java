package com.stackroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.dao.UserDao;
import com.stackroute.model.User;

@Controller
public class UserController {

	private UserDao userDao;
	
	@Autowired
	public UserController(UserDao userDao) {
		this.userDao=userDao;
		
	}
	
	@GetMapping("/")
	public ModelAndView viewPage(Model model) {
		//Sending model object to DS
		//model.addAttribute("userList", userDao.listAllUsers());
		
		//Sending view name to DS
		return new ModelAndView("index", "userList", userDao.listAllUsers());
		
	}
	//CREATE USER
	@PostMapping("/addUser")
	public String addUser(@RequestParam("name") String name ,@RequestParam("city") String city, @RequestParam("email") String email) {
		User existingUser=userDao.getUserByEmail(email);
		if(existingUser!=null) {
			existingUser.setCity(city);
			existingUser.setName(name);
			userDao.updateUser(existingUser);
		}
		else {
			userDao.addUser(new User(name, city, email));
		}
		return "redirect:/";
		
		}
	
	//DELETE USER
	@GetMapping("/delUser/{email}")
	public String deleteUser(@PathVariable String email) {
		userDao.deleteUser(email);
		return "redirect:/";
	}
	
	//UPDATE USER
	@GetMapping("/updUser/{email}")
	public String updateUser(@PathVariable String email, ModelMap modelMap) {
		User userItems=userDao.getUserByEmail(email);
		modelMap.addAttribute("userItem", userItems);
		modelMap.addAttribute("userList", userDao.listAllUsers());
		return "index";
	}
	
}
