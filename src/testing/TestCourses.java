package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import app.Courses;
import utils.CoursesUtils;

public class TestCourses {
	
	Courses courses = null;
	
	@Before
	public void setUp() throws Exception {
		courses = new Courses();		
	}
	
	/**
	 * Testing the data coming from the files and giving the correct output 
	 */
	@Test
	public void fileTest () {				
		ClassLoader classLoader = getClass().getClassLoader();
		String urlPreReq = classLoader.getResource("prerequisites.csv").getPath();
		String urlCourses = classLoader.getResource("courses.csv").getPath();
		
		courses.readFile(urlPreReq, 1);
		courses.readFile(urlCourses, 2);
		courses.getCourseNames();	
	}	
	
	/**
	 * Testing courses that require prerequisites without loop in the requisites
	 */
	@Test
	public void paramsTest() {
		Integer [] preReq = {2, 1, 2, 6, 2, 11, 3, 1, 3, 6, 3, 7, 3, 8, 3, 12, 4, 1, 4, 3, 4, 6, 5, 1, 5, 3, 5, 4, 5, 12, 6, 1, 7, 6, 8, 6, 8, 7, 8, 10, 8, 12, 9, 3, 11, 8, 11, 10};
		String [] course = {"id", "title", "1", "Programming in C", "2", "Distributed Computing", "3", "Database Systems", "4",
				"Algorithms 1", "5", "Algorithms 2", "6", "Programming in Java",
				"7", "Advanced Programming in Java", "8", "Big Data with Apache Spark", "9", 
				"Programming in Perl", "10", "Probability", "11", "Scalable Machine Learning", "12", "Data Structures"};
		
		courses.setCoursesNames(getCoursesMap(course));
		courses.setPrerequisited(new ArrayList<>(Arrays.asList(preReq)));
		courses.setCourses(new ArrayList<>(Arrays.asList(course)));
		courses.getCourseNames();
	}
	
	/**
	 * Testing courses require less prerequisites
	 */
	@Test
	public void paramTest2() {
		Integer [] preReq = {2, 1, 2, 6, 2, 11};
		String [] course = {"id", "title", "1", "Programming in C", "2", "Distributed Computing", "3", "Database Systems", "4",
				"Algorithms 1", "5", "Algorithms 2", "6", "Programming in Java",
				"7", "Advanced Programming in Java", "8", "Big Data with Apache Spark", "9", 
				"Programming in Perl", "10", "Probability", "11", "Scalable Machine Learning", "12", "Data Structures"};
		
		courses.setCoursesNames(getCoursesMap(course));
		courses.setPrerequisited(new ArrayList<>(Arrays.asList(preReq)));
		courses.setCourses(new ArrayList<>(Arrays.asList(course)));
		courses.getCourseNames();
	}
	
	/**
	 * Testing all courses require a prerequisite
	 */
	@Test
	public void paramTest3() {
		Integer [] preReq = {1, 2, 10, 11, 12, 4, 2, 1, 2, 6, 2, 11, 3, 1, 3, 6, 3, 7, 3, 8, 3, 12, 4, 1, 4, 3, 4, 6, 5, 1, 5, 3, 5, 4, 5, 12, 6, 1, 7, 6, 8, 6, 8, 7, 8, 10, 8, 12, 9, 5, 11, 8, 11, 10};
		String [] course = {"id", "title", "1", "Programming in C", "2", "Distributed Computing", "3", "Database Systems", "4",
				"Algorithms 1", "5", "Algorithms 2", "6", "Programming in Java",
				"7", "Advanced Programming in Java", "8", "Big Data with Apache Spark", "9", 
				"Programming in Perl", "10", "Probability", "11", "Scalable Machine Learning", "12", "Data Structures"};
		
		courses.setCoursesNames(getCoursesMap(course));
		courses.setPrerequisited(new ArrayList<>(Arrays.asList(preReq)));
		courses.setCourses(new ArrayList<>(Arrays.asList(course)));
		courses.getCourseNames();
	}
	
	/**
	 * Testing courses don't require prerequisites
	 */
	@Test
	public void paramTest4() {
		Integer [] preReq = {};
		String [] course = {"id", "title", "1", "Programming in C", "2", "Distributed Computing", "3", "Database Systems", "4",
				"Algorithms 1", "5", "Algorithms 2", "6", "Programming in Java",
				"7", "Advanced Programming in Java", "8", "Big Data with Apache Spark", "9", 
				"Programming in Perl", "10", "Probability", "11", "Scalable Machine Learning", "12", "Data Structures"};
		
		courses.setCoursesNames(getCoursesMap(course));
		courses.setPrerequisited(new ArrayList<>(Arrays.asList(preReq)));
		courses.setCourses(new ArrayList<>(Arrays.asList(course)));
		courses.getCourseNames();
	}
		
	private Map<Integer, String> getCoursesMap(String [] course){
		List<String> courseList = new ArrayList<>(Arrays.asList(course)); 		
		
		Map<Integer, String> coursesNamesMap = new TreeMap<>();
		
		for (int i = 0; i < courseList.size(); i++) {
			if (i % 2 == 0) {
				if (CoursesUtils.isInteger(courseList.get(i))) {
					coursesNamesMap.put(Integer.parseInt(courseList.get(i)), courseList.get(i + 1));
				}				
			}
		}
		
		return coursesNamesMap;
	}
	
}
