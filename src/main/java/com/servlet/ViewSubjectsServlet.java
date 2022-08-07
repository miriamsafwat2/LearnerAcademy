package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.CRUDOperations;
import com.model.Teacher;

/**
 * Servlet implementation class ViewSubjectsServlet
 */
@WebServlet("/viewSubjects")
public class ViewSubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSubjectsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		HashMap<String, List<String>> gradeSubjects = CRUDOperations.getAllSubjectsForAllClasses();
		writer.print("<h2>List of Subjects: </h2>");
		writer.print("<ul>");
		
		for (String i : gradeSubjects.keySet()) {
			writer.print(i);
			writer.print("<ul>");
			for(String subject :  gradeSubjects.get(i)) {
				writer.print("<li>" + subject);
			}
			writer.print("</ul>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
