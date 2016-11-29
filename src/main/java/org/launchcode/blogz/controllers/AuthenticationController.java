package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		String newUsername = request.getParameter("username");
		model.addAttribute("username", newUsername);
		String newPassword = request.getParameter("password");
		String newVerifyPassword = request.getParameter("verify");

		if (!Student.isValidUsername(newUsername)){
			String username_error = "Bad name";
			model.addAttribute("username_error", username_error);
		}
		if (!Student.isValidPassword(newPassword)){
			String password_error = "Bad password";
			model.addAttribute("password_error", password_error);
		}
		if (!newVerifyPassword.equals(newPassword)){
			String verify_error = "Your passwords do not match";
			model.addAttribute("verify_error", verify_error);
		}
		if ((Student.isValidUsername(newUsername)) && (Student.isValidPassword(newPassword))){
			if (newVerifyPassword.equals(newPassword)){
				Student newStudent = new Student(newUsername, newPassword);
				studentDao.save(newStudent);
				HttpSession thisSession = request.getSession();
				setStudentInSession(thisSession, newStudent);

				return "redirect:blog/newpost";
			}
			return "signup";

		}		

		// TODO - implement signup
		// get parameters from request object
		// validate parameters (username, password, verify)
		// if validated, create new user, and put them in the session
		// access session...   Session thisSession = request.getSession(); (in AbstractController)


		return "signup";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {

		// TODO - implement login
		// get parameters from request		
		// get user by their username Student.username

		//check password is correct, if not correct password redirect to the form and give an error message

		// log them in, if so (i.e. setting the user in the session

		String newUsername = request.getParameter("username");
		String newPassword = request.getParameter("password");
		Student student = studentDao.findByUsername(newUsername);
		// no user exists error
		if (student == null){
			String error = "Your user name does not exist";
			model.addAttribute("error", error);
			return "login";
		}
		if (!student.isMatchingPassword(newPassword)){
			String error = "Try again";
			model.addAttribute("error", error);
			return "login";

		}
		HttpSession thisSession = request.getSession();
		setStudentInSession(thisSession, student);
		return "redirect:blog/newpost";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
}