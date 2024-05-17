/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Livre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asma
 */
public class LivreDAO {
     private static final String URL = "jdbc:mysql://localhost:3306/bd_gb2";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    
    private static final String ENSERT_LIVRE= "INSERT INTO livre (titre, auteur) VALUES ( ?, ?)";

    private static final String SELECT_LIVRE_BY_ID ="SELECT livre_id,titre, auteur,disponible FROM livre WHERE livre_id= ?";
    private static final String SELECT_ALL_LIVRES ="SELECT * FROM livre ";
    private static final String DELETE_LIVRE_BY_ID = "DELETE FROM livre WHERE livre_id= ?";
    private static final String UPDATE_LIVRE_BY_ID = "UPDATE livre SET titre=?,auteur=?,disponible=? WHERE livre_id= ?";

   public LivreDAO (){ }
     
   public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(DRIVE );
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new SQLException("Database connection error.");
        }
        return conn;
    }
   
   //insert
   
   
   public void insertLivre(Livre livre) throws SQLException {
		System.out.println( ENSERT_LIVRE);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement( ENSERT_LIVRE)) {
			preparedStatement.setString(1, livre.getTitre());
			preparedStatement.setString(2, livre.getAuteur());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

   //selectuser
   
   public Livre selectLivre(int id) {
		Livre livre = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				boolean disponible = rs.getBoolean("disponible");
				livre = new Livre(id, titre , auteur, disponible );
                                
          
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return livre;
	}
   //select all users
   public List<Livre> selectAllLivres() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Livre> livres = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVRES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("livre_id");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				boolean disponible  = rs.getBoolean("disponible");
				livres.add(new Livre(id, titre , auteur, disponible ));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return livres;
	}
   
   //update
   public boolean updateLivre(Livre livre) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIVRE_BY_ID);) {
			System.out.println("updated livre:"+statement);
			statement.setString(1, livre.getTitre());
			statement.setString(2, livre.getAuteur());
			statement.setBoolean(3, livre.isdisponible());
			statement.setInt(4, livre.getIdLivre());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
   
   //delete
    public boolean deleteLivre(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_LIVRE_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
   
   // exception
   	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}

