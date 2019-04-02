package challenge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 
 * http://www.bernzilla.com/2008/05/13/selecting-a-random-row-from-an-sqlite-table/
 * 
 * */
public class QuoteDao {

	public Quote getQuote() throws SQLException {
        Quote quote = null;
        Connection connection = null;
        
        try {
        		connection = ConnectionFactory.createConnection();
                Statement stmt = connection.createStatement();
                
                String sql = " SELECT actor, detail FROM scripts " +
		                     " WHERE actor IS NOT NULL " +
		                     " ORDER BY RANDOM() LIMIT 1 ";
                
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    quote = new Quote(rs.getString("actor"), rs.getString("detail"));
                }
                
                rs.close();
                stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                	connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return quote;
	}

	public Quote getQuoteByActor(String actor) throws SQLException {
        Quote quote = null;
        Connection connection = null;
        
        try {
        	connection = ConnectionFactory.createConnection();
            Statement stmt = connection.createStatement();
            
            String sql = " SELECT actor, detail FROM scripts " +
	                	 " WHERE actor = '" + actor.trim() + "' " +
	                     " ORDER BY RANDOM() LIMIT 1 ";
            
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                quote = new Quote(rs.getString("actor"), rs.getString("detail"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                	connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return quote;
	}

	public static void main(String[] args) throws SQLException {
		QuoteDao dao = new QuoteDao();
		System.out.println(dao.getQuote());
		
		System.out.println(dao.getQuoteByActor("Graham Chapman"));
    }
}
