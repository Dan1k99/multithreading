import java.sql.*;

public class Database {
	private Connection conn;
	private Statement stmt;

	// CONSTRUCTOR
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "root");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	// CREATE TABLES METHOD
	public void createTables(String firstTable) {
		try{
			// if table already exists, drop it
			// if table already exists, SQL can't override it
			stmt.executeUpdate("DROP TABLE IF EXISTS " + firstTable);
			// create new empty table with given name
			String createFirstTable = "CREATE TABLE " + firstTable +"(id varchar(50), age int, medicine int, DoctorName varchar(50), treatment int)";
			stmt.executeUpdate(createFirstTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//INSERT INTO TABLE METHOD
	public void insertIntoTable(String tableName, Note n) throws SQLException{
		if(n.getPre()==null) { // Patients whose didn't get medicine will get medicine number "0"
			String insertDetails = "INSERT INTO " + tableName + "(id , age, medicine, DoctorName, treatment) VALUES('" + n.getPatient().getId() + "'," + n.getPatient().getAge() + ","+ 0 +",'"+n.getJunior() +"',"+ n.getTreatment()+ ")";
			stmt.executeUpdate(insertDetails);
		}else { // patients whose got medicine  
			String insertDetails = "INSERT INTO " + tableName + "(id , age, medicine, DoctorName, treatment) VALUES('" + n.getPatient().getId() + "'," + n.getPatient().getAge() + ","+ n.getPre().getMedicine() +",'"+n.getJunior() +"',"+ n.getTreatment()+ ")";
			stmt.executeUpdate(insertDetails);
		}
	}
}


