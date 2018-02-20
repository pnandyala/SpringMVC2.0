package com.techrefresh.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techrefresh.springmvc.model.User;
import com.techrefresh.springmvc.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/home", "/", ""}, method = RequestMethod.GET)
	public ModelAndView goHome() {
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister() {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		userService.register(user);		
		return new ModelAndView("welcome", "firstName", user.getFirstName());
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		List<User> userList = new ArrayList<User>();
		userList = userService.getAllUsers();
		return new ModelAndView("users","userList", userList);
	}

}
