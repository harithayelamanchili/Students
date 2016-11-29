package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface StudentDao extends CrudRepository<Student, Integer> {

    Student findByUid(int uid);
    
    List<Student> findAll();
    Student findByUsername(String username);
    
    // TODO - add method signatures as needed

}