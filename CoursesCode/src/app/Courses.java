package app;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.collections4.CollectionUtils;
import utils.CoursesUtils;

public class Courses {
	
	private List<Integer> prerequisited = new ArrayList<>();
	private List<String> courses = new ArrayList<>();
	private Map<Integer, String> coursesNamesMap = new TreeMap<>();

	public void readFile(String fileToRead, int fileType) {
		File file = new File(fileToRead);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {

				String[] preReq = line.split(cvsSplitBy);
				
				if (fileType == 1) {
					if (CoursesUtils.isInteger(preReq[0]) && CoursesUtils.isInteger(preReq[1])) {
						prerequisited.add(Integer.parseInt(preReq[0]));
						prerequisited.add(Integer.parseInt(preReq[1]));
					}
				}else if(fileType == 2) {
					courses.add(preReq[0]);
					courses.add(preReq[1]);
					
					if (CoursesUtils.isInteger(preReq[0])) {
						coursesNamesMap.put(Integer.parseInt(preReq[0]), preReq[1]);
					}
				}															
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public void setPrerequisited(List<Integer> prerequisited) {
		this.prerequisited = prerequisited;
	}
	
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	
	public void setCoursesNames(Map<Integer, String> coursesNamesMap) {
		this.coursesNamesMap = coursesNamesMap;
	}
	
	public void startReader() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the path of the prerequisites");
		String prereqPath = br.readLine();
		System.out.println("Enter the path of the prerequisites");
		String coursesPath = br.readLine();
		
		readFile(prereqPath, 1);
		readFile(coursesPath, 2);
	}
	
	
	private  Map<Integer, List<Integer>> getRequisites() {
		Map<Integer, List<Integer>> mapRequisites = new TreeMap<>();
		
		for (int i = 0; i < prerequisited.size(); i++) {
			if (i % 2 == 0) {
				if (mapRequisites.containsKey(prerequisited.get(i))) {
					List <Integer> req = mapRequisites.get(prerequisited.get(i));
					req.add(prerequisited.get(i + 1));
					mapRequisites.put(prerequisited.get(i), req);
				}else {
					List <Integer> req = new ArrayList<>();
					req.add(prerequisited.get(i + 1));
					mapRequisites.put(prerequisited.get(i), req);
				}				
			}												
		}
		return mapRequisites;
	}
	
	private List<Integer> getCoursesIDs() {		
		Map<Integer, List<Integer>> mapRequisites = getRequisites();		
		List<Integer> localCourses = new ArrayList<>();
		
		//Get all the courses that don't have a requirement		
		List<Integer> entryCourses = new ArrayList<>();
		
		for (int i = 0; i < courses.size(); i++) {			
			if (i % 2 == 0) {		
				if (CoursesUtils.isInteger(courses.get(i))) {
					if (!mapRequisites.containsKey(Integer.parseInt(courses.get(i)))) {
						entryCourses.add(Integer.parseInt(courses.get(i)));
					}else {
						localCourses.add(Integer.parseInt(courses.get(i)));
					}
				}					
			}
		}
			
		if (entryCourses.isEmpty()) {
			System.out.println("All courses require a prerequisite");
		}else {
			
			while(localCourses.size() != 0) {
				int index = 0;
				for (int i = 0; i < localCourses.size(); i++) {
					List<Integer> reqs = mapRequisites.get(localCourses.get(i));
					
					Collection<Integer> common = CollectionUtils.retainAll(entryCourses, reqs);
					
					if (CoursesUtils.equalLists((List<Integer>) common, reqs)) {
						entryCourses.add(localCourses.get(i));
						index = i;
						break;
					}									
				}
				localCourses.remove(index);
			}			
		}
		return entryCourses;
	}
	
	public void getCourseNames(){
		List<Integer> coursesIds = getCoursesIDs();
		
		for (int i = 0; i < coursesIds.size(); i++) {
			System.out.println(coursesNamesMap.get(coursesIds.get(i)));
		}
	}
		

}
