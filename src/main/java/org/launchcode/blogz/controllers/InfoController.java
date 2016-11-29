package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Info;
import org.launchcode.blogz.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InfoController extends AbstractController {

	@RequestMapping(value = "/details/newpost", method = RequestMethod.GET)
	public String newInfoForm() {
		return "newinfo";
	}

	@RequestMapping(value = "/details/newpost", method = RequestMethod.POST)
	public String newinfo(HttpServletRequest request, Model model) {

		// TODO - implement newPost

		//get request parameters
		String newTitle = request.getParameter("title");
		String newBody = request.getParameter("body");
		Student author = getStudentFromSession(request.getSession());

		// validate parameters
		// if not valid, send back to form with error message
		//if valid,create new Info
		
		if (newTitle.equals("") || newTitle.equals(null)){
			String error = "Bad title";
			model.addAttribute("error", error);
			return "newpost";
		}

		
		if (newBody.equals("") || newBody.equals(null)){
			String error = "You need a body";
			model.addAttribute("error", error);
			return "newpost";
		}
		
			Info newInfo = new Info(newTitle, newBody, author);
			infoDao.save(newInfo);

			String username = author.getUsername();
			int uid = newInfo.getUid();
			return singlePost(username, uid, model);
			


		//return "redirect:index"; // TODO - this redirect should go to the new post's page  		
	}

	// handle requests like /blog/chris/5

	@RequestMapping(value = "/details/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {

		// TODO - implement singlePost

		//get given post
		Info newInfo = infoDao.findByUid(uid);

		// pass post into the template
		model.addAttribute("info", newInfo);

		return "info";
	}

	@RequestMapping(value = "/details/{username}", method = RequestMethod.GET)
	public String studentInfos(@PathVariable String username, Model model) {

		// TODO - implement userPosts

		// get all user posts
		Student student = studentDao.findByUsername(username);
		List<Info> studentInfos = student.getInfos();

		// pass the posts into the template
		model.addAttribute("infos", studentInfos);

		return "details";
	}

}