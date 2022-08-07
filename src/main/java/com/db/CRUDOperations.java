package com.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import com.dataStructure.GradeReport;
import com.model.Grade;
import com.model.Student;
import com.model.Subject;
import com.model.Teacher;
import com.model.TeacherGradeSubjectLink;

@SuppressWarnings("deprecation")
public class CRUDOperations {

	public static void addStudent(Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		int id = (int) session.save(student);
		System.out.println("Saved student id is: " + id);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}
	
	public static List <Student> getAllStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List <Student> students = session.createQuery("from Student", Student.class).list();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return students;
	}
	
	public static void addGrade(Grade grade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		int id = (int) session.save(grade);
		System.out.println("Saved grade id is: " + id);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}
	
	public static List <Grade> getAllGrades() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List <Grade> grades = session.createQuery("from Grade", Grade.class).list();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return grades;
	}
	
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
	
	public static void addTeacherGradeSubjectLink(TeacherGradeSubjectLink teacherGradeSubjectLink) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		long id = (long) session.save(teacherGradeSubjectLink);
		System.out.println("Saved teacherGradeSubjectLink id is: " + id);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("deprecation")
	public static HashMap<String, List<String>> getAllSubjectsForAllClasses() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		Query<Object[]> q = session.createSQLQuery("SELECT distinct class.name as \"Grade\", subject.name as \"Subject\" "
				+ "from teachergradesubjectlink "		
				+ "join subject ON teachergradesubjectlink.subject_id = subject.id "
				+ "join class ON teachergradesubjectlink.grade_id = class.id "
				+ "ORDER BY \"Grade\" ");
		
		List<Object[]> gradesSubjectObjects = (List<Object[]>)q.getResultList();
		
		HashMap<String, List<String>> gradeSubjects = new HashMap<String, List<String>>();
		//gradeSubjects.put("", new List<>)
		for(Object[] gradesSubject: gradesSubjectObjects){
	         String className = (String)gradesSubject[0];
	         String subject = (String)gradesSubject[1];
	         System.out.println(className + " " + (String)gradesSubject[1]);
	         
	         if (!gradeSubjects.containsKey(className)) {
	        	 gradeSubjects.put(className, new ArrayList<String>());
	         }
	         gradeSubjects.get(className).add(subject);
		}
		/*
		 * 
		 ScrollableResults sr = query.scroll();

        while ( sr.next() )
        {
            System.out.println( sr.get( 0 ) );
        }
		 */
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return gradeSubjects;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static GradeReport getReportForGrade(String gradeName) {
		// get report id
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select id from Grade where name=:name");
		query.setParameter("name", gradeName);
		int id = (int) query.uniqueResult();
		System.out.println("Id of grade is: " + id);
	
		Query <Student> queryStudents = session.createQuery("from Student where grade_id=:grade_id", Student.class);
		queryStudents.setParameter("grade_id", id);
		List<Student> students = queryStudents.list();
		System.out.println("Students list: ");
		GradeReport report = new GradeReport();
		for(Student s : students) {
			report.students.add(s.getName());
			System.out.println(s.getName());
		}
		
		// get subjects and teachers
		Query<Object[]> q = session.createSQLQuery("SELECT teacher.name as \"Teacher\", subject.name as \"Subject\" "
				+ "from teachergradesubjectlink "
				+ "join teacher ON teachergradesubjectlink.teacher_id = teacher.id "
				+ "join subject ON teachergradesubjectlink.subject_id = subject.id "
				+ "WHERE grade_id=:grade_id ");
		q.setParameter("grade_id", id);
		List<Object[]> gradesSubjectObjects = (List<Object[]>)q.getResultList();
		for(Object[] gradesSubject: gradesSubjectObjects){
	         String teacher = (String)gradesSubject[0];
	         String subject = (String)gradesSubject[1];
	         System.out.println(teacher + " " + subject);
	         report.subjectsTeachers.put(subject, teacher);
		}
		
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return report;
	}
	
	public static void main(String[] args) {
		//Grade grade = new Grade(2, "Grade 2");
		//Student student = new Student(4, "Noha", grade);
		//addStudent(student);
		
		//Teacher teacher = new Teacher(3, "Gehan");
		//addTeacher(teacher);
		Grade grade = new Grade(11, "Grade 11");
		Subject subject = new Subject(1, "English");
		//Teacher teacher = new Teacher(11, "Teacher11");
		
		//TeacherGradeSubjectLink teacherGradeSubject = new TeacherGradeSubjectLink(null, grade, subject);
		//addTeacherGradeSubjectLink(teacherGradeSubject);
		//teacherGradeSubject.getTeacher();
		/*
		List<GradeSubject> gradeSubjects = getAllSubjectsForAllClasses();
		for(GradeSubject g : gradeSubjects) {
			System.out.println(g.grade);
			for(String s : g.subjects) {
				System.out.println(s);
			}
		}
		
		List<Student> students = getAllStudents();
		for(Student s : students) {
			System.out.println(s.getName());
		}
		*/
		getReportForGrade("Grade 1");
	}

}


