package utils;

import java.util.Collections;
import java.util.List;

public class CoursesUtils {
	
	public static boolean equalLists(List<Integer> one, List<Integer> two){     
	    if (one == null && two == null){
	        return true;
	    }

	    if((one == null && two != null) 
	      || one != null && two == null
	      || one.size() != two.size()){
	        return false;
	    }

	    Collections.sort(one);
	    Collections.sort(two);      
	    return one.equals(two);
	}
	
	public static boolean isInteger (String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
