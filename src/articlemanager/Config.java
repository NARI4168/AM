package articlemanager;

public class Config {
	public static String getDBUrl() {
		return "jdbc:mysql://localhost:3306/articlemanager?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
	}

	public static String getDBId() {
		return "root";
	}

	public static String getDBPw() {
		return "";
	}

	public static String getDbDriverClassName() {
		return "com.mysql.cj.jdbc.Driver";
	}
}