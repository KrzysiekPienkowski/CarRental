package main.java;

public class Utils {

	public static String checkNull(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

}
