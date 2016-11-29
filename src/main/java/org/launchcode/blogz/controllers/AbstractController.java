package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Student;
import org.launchcode.blogz.models.dao.InfoDao;
import org.launchcode.blogz.models.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

	@Autowired
    protected StudentDao studentDao;
	
	@Autowired
	protected InfoDao infoDao;

    public static final String studentSessionKey = "student_id";

    protected Student getStudentFromSession(HttpSession session) {
    	
        Integer studentId = (Integer) session.getAttribute(studentSessionKey);
        return studentId == null ? null : studentDao.findByUid(studentId);
    }
    
    protected void setStudentInSession(HttpSession session, Student student) {
    	session.setAttribute(studentSessionKey, student.getUid());
    }
	
}
