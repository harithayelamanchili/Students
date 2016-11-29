package org.launchcode.blogz.controllers;

import java.util.List;

import org.launchcode.blogz.models.Info;
import org.launchcode.blogz.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		
		// TODO - fetch users and pass to template
		List<Student> allStudents = studentDao.findAll();
		model.addAttribute("student", allStudents);
		
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String infoIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		List<Info> allInfos = infoDao.findAll();
		model.addAttribute("infos", allInfos);
		
		return "blog";
	}
	
}
