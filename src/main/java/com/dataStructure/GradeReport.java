package com.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GradeReport{
	public GradeReport() {
		students = new ArrayList<>();
		subjectsTeachers = new HashMap<String, String>();
	}
	public List<String> students;
	public HashMap<String, String> subjectsTeachers;
}
