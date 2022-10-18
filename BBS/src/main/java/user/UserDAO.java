package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private static UserDAO dao = new UserDAO();
	public static UserDAO getInstance() { return dao; }
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "1234";
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "select userPassword from user where userID = ?";
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			System.out.println(e);
		}
		return -2; // 데이터베이스 오류
	}
	
}
