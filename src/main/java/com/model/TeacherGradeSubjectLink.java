package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeacherGradeSubjectLink {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "grade_id")
	private Grade grade;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "subject_id")
	private Subject subject;

	public TeacherGradeSubjectLink(Long id, Teacher teacher, Grade grade, Subject subject) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.grade = grade;
		this.subject = subject;
	}

	public TeacherGradeSubjectLink(Teacher teacher, Grade grade, Subject subject) {
		super();
		this.teacher = teacher;
		this.grade = grade;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
