package vn.fpt.fsoft.intern517.olt.common;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Utils {
	/*
	 * Ham tra ve sau rong neu la null
	 */
	public static String getVaildString(String s) {
		if (s == null)
			return "";
		return s;
	}

	/*
	 * Ham tra ve: true = la so, false = la chuoi
	 */
	public static boolean checkNumber(String cloneNumber) {

		if (Character.isDigit(cloneNumber.charAt(0))) {
			return true;
		}
		return false;
	}

	/*
	 * Ham tra ve ten topic clone
	 */
	public static String newTopicName(String topicName, String date1,
			String date2) {
		String newTopicName = topicName;
		if (topicName.indexOf("(" + date1 + ")") != -1) {
			newTopicName = topicName.substring(0,
					topicName.indexOf("(" + date1 + ")"));
		}
		newTopicName += " (" + date2 + ")";
		return newTopicName;
	}

	/*
	 * Ham so sanh hai chuoi String: true=giong nhau, false=khac nhau
	 */
	public static boolean checkString(String a, String b) {
		if (a.equals(b)) {
			return true;
		}
		return false;
	}

	/*
	 * Ham chinh dinh dang ngay gio
	 */
	public static String formatDateTime(String d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String newDate = "";
		try {
			Date date = df.parse(d);
			df = new SimpleDateFormat("kk:mm dd/MM/yyyy");
			newDate = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static boolean checkString(String a, String b, String c) {

		if (a == null || "".equals(a.trim())) {
			return false;
		} else if (b == null || "".equals(b.trim())) {
			return false;
		} else if (c == null || "".equals(c.trim())) {
			return false;
		}

		return true;
	}

	public static boolean checkGender(String sex) {
		if (sex.equals("0"))
			return true;
		else if (sex.equals("1"))
			return true;
		return false;
	}
}
