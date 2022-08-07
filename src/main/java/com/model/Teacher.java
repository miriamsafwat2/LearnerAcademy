package com.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	
	@Id
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TeacherGradeSubjectLink> teacherGradeSubjectLinks;
	
	public Teacher() {
		
	}
	
	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<TeacherGradeSubjectLink> getTeacherGradeSubjectLinks() {
		return teacherGradeSubjectLinks;
	}

	public void setTeacherGradeSubjectLinks(Set<TeacherGradeSubjectLink> teacherGradeSubjectLinks) {
		this.teacherGradeSubjectLinks = teacherGradeSubjectLinks;
	}
}

