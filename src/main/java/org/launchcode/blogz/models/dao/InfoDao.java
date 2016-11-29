package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Info;
import org.launchcode.blogz.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface InfoDao extends CrudRepository<Info, Integer> {
    
    List<Info> findByAuthor(int authorId);
    List<Info> findAll();
    
    
    // TODO - add method signatures as needed
	// find all posts
    // find post by title
    
    Info findByUid(int uid);
}
