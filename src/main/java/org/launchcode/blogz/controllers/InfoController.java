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

	@RequestMapping(value = "/details/newinfo", method = RequestMethod.GET)
	public String newInfoForm() {
		return "newinfo";
	}

	@RequestMapping(value = "/details/newinfo", method = RequestMethod.POST)
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
			return "newinfo";
		}

		
		if (newBody.equals("") || newBody.equals(null)){
			String error = "You need a body";
			model.addAttribute("error", error);
			return "newinfo";
		}
		
			Info newInfo = new Info(newTitle, newBody, author);
			infoDao.save(newInfo);

			String username = author.getUsername();
			int uid = newInfo.getUid();
			return singlePost(username, uid, model);
			

 		
	}

	

	@RequestMapping(value = "/details/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {

		
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