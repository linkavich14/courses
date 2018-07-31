package app;
import java.io.IOException;

public class StartApp {
	
	public static void main(String[] args) throws IOException {
		Courses courses = new Courses();		
		
		if (args.length > 0) {
			courses.readFile(args[0], 1);
			courses.readFile(args[1], 2);
		}else {
			courses.startReader();
		}
				
		courses.getCourseNames();
	}
	

}
