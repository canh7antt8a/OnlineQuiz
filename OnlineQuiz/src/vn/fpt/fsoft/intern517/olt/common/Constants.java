package vn.fpt.fsoft.intern517.olt.common;

public class Constants {
	public static final String LIBRARY_NAME = "net.sourceforge.jtds.jdbc.Driver";

	public static final String HOST_NAME = "localhost";
	public static final String SQL_INSTANCE_NAME = "MSSQLSERVER";
	public static final String DATABASE = "Online_Quiz";
	public static final String USERNAME = "sa";
	public static final String PASSWORD = "12345678";

	public static final String URL_CONNECTION = "jdbc:jtds:sqlserver://"
			+ HOST_NAME + ":1433/" + DATABASE + ";instance="
			+ SQL_INSTANCE_NAME;

	public static final String ADMIN_RIGHTS = "1";
	public static final String ADMIN_CHILD_RIGHTS = "2";
	public static final String STUDENT_RIGHTS = "0";

	public static final String[] ALPHABET = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	public static String MSG_DUPLICATE_USER = "Tên đăng nhập đã tồn tại!";
	public static String MSG_DUPLICATE_CLASS = "Tên lớp đã tồn tại!";
	public static String MSG_DUPLICATE_TOPIC = "Tên topic đã tồn tại!";
	public static String MSG_DELETE_DATA = "Bạn đã xóa toàn bộ dữ liệu thành công!";
	public static String MSG_WRONG_PASSWORD = "Mật khẩu không chính xác!";
	public static String MSG_LOGIN_FAILED = "Đăng nhập thất bại!";
	public static String MSG_RESETIP = "Bạn đã đặt lại IP thành công!";
	public static String MSG_NOT_SELECT_CLASS = "Bạn chưa chọn lớp";

}
