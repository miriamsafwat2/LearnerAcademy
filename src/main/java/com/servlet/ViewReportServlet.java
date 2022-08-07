package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataStructure.GradeReport;
import com.db.CRUDOperations;

/**
 * Servlet implementation class ViewReportServlet
 */
@WebServlet("/ViewReportServlet")
public class ViewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReportServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("grade");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		writer.print("<h1>Report for grade " + grade + "</h1>");
		GradeReport report = CRUDOperations.getReportForGrade(grade);
		writer.print("<h2>Students list: </h2>");
		writer.print("<ul>");
		for(String student : report.students) {
			writer.print("<li>" + student);
		}
		writer.print("</ul>");
		
		writer.print("<h2>Subject - Teacher list: </h2>");
		writer.print("<ul>");
		
		for(String s : report.subjectsTeachers.keySet()) {
			writer.print("<li>" + s + " - " + report.subjectsTeachers.get(s));
		}
		writer.print("</ul>");
	}

}
