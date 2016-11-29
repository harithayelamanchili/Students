package org.launchcode.blogz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "info")
public class Info extends AbstractEntity {

	private String title;
	private String body;
	private Student author;
	private Date created;
	private Date modified;
	
	public Info() {}
	
	public Info(String title, String body, Student author) {
		
		super();
		
		this.title = title;
		this.body = body;
		this.author = author;
		this.created = new Date();
		this.updated();
		
		author.addInfo(this);
	}
	
	
	@NotNull
    @Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.updated();
	}

	@NotNull
    @Column(name = "body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
		this.updated();
	}
	
	@ManyToOne
	public Student getAuthor() {
		return author;
	}
	
	@SuppressWarnings("unused")
	private void setAuthor(Student author) {
		this.author = author;
	}
	
	@NotNull
	@OrderColumn
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	
	@SuppressWarnings("unused")
	private void setCreated(Date created) {
		this.created = created;
	}
	
	@NotNull
	@Column(name = "modified")
	public Date getModified() {
		return modified;
	}
	
	@SuppressWarnings("unused")
	private void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	
}
