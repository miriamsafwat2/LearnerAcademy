package com.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Teacher;

public class CRUD {
	public static List <Teacher> getAllTeachers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List <Teacher> teachers = session.createQuery("from Teacher", Teacher.class).list();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return teachers;
	}
	
	public static void addTeacher(Teacher teacher) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		int id = (int) session.save(teacher);
		System.out.println("Saved teacher id is: " + id);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}
	public static void main(String[] args) {
		//Grade grade = new Grade(2, "Grade 2");
		//Student student = new Student(4, "Noha", grade);
		//addStudent(student);
		
		//Teacher teacher = new Teacher(2, "Rania");
		//addTeacher(teacher);
		List <Teacher> teachers = getAllTeachers();
		System.out.println("Teachers saved in the database are: ");
		for(Teacher t : teachers) {
			System.out.println(t.getName());
		}
	}
}
