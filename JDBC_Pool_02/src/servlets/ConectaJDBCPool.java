package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

// import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
// import javax.sql.PooledConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import java.util.Properties;

// import org.apache.catalina.Context;
// import org.apache.tomcat.jdbc.pool.DataSource;
// import org.apache.tomcat.jdbc.pool.PoolProperties;

@WebServlet("/ConectaJDBCPool")
public class ConectaJDBCPool extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			InitialContext ctx = new InitialContext();
			 DataSource ds =
			          (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
			 Connection conn = null;
		     Statement stmt = null;
		     ResultSet rs = null;
		     try {
		    	 conn = ds.getConnection();
  	             stmt = conn.createStatement();
		         // stmt.execute("SELECT * FROM Emp");
		         String consultaSQL = "SELECT * FROM Emp";
  	             rs = stmt.executeQuery(consultaSQL);
			     int cnt = 1;
			     while (rs.next()) {
			    	 out.println((cnt++) + ". Empno:" + rs.getLong("empno") +
			    			 " Ename: " + rs.getString("ename") + " Job: " + rs.getString("job") + " <br />");  
			     }
			     rs.close();
			     rs = null;
		         stmt.close();
		         stmt = null;
		         conn.close();
		         conn = null;
		     } catch (SQLException sqlE) {
		    	 sqlE.printStackTrace(out);
		     } finally {
		            /*
		             * close any jdbc instances here that weren't
		             * explicitly closed during normal code path, so
		             * that we don't 'leak' resources...
		             */
		    	   
		    	 if (rs != null) {
		    		 try { 
		    			 rs.close(); 
		    		 } catch (SQLException e) { 
 
		    		 }
		    		 rs = null;
		    	 }

		         if (stmt != null) {
		        	 try {
		        		 stmt.close();
		             } catch (SQLException sqlex) {
                
		             }
	                 stmt = null;
		         }

		         if (conn != null) {
		        	 try {
		        		 conn.close();
		             } catch (SQLException sqlex) {

		             }
	                 conn = null;
		         }
		     }
		} catch (NamingException e1) {
			e1.printStackTrace(out);
		}
		 
		//		Connection conn = null;
		//	    Statement stmt = null;
		//	    ResultSet rS = null;

		// InitialContext initContext;

		//try {
			//Properties env = new Properties();
			 // env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			 // env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
			 // InitialContext context = new InitialContext(env);
		//initContext = new InitialContext();
		//  String jndiName = "java:/comp/env/jdbc/TestDB";
		//ConnectionPoolDataSource dataSource = (ConnectionPoolDataSource) initContext.lookup(jndiName);
		//PooledConnection pooledConnection = dataSource.getPooledConnection();

			    
			
			 // initContext = new InitialContext();
			// Context envContext  = (Context)cxt.lookup("java:/comp/env");
			// DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
			/*if ( initContext == null ) {
				out.println("Uh oh -- no context!" + " <br />");
			}*/
			// Context envContext = (Context)initContext.lookup("java:/comp/env");
			
			// DataSource ds = (DataSource) ((InitialContext) envContext).lookup( "jdbc/TestDB" );
			
			/*if ( ds == null ) {
				out.println("Data source not found!" + " <br />");
			}*/
			
		//conn = pooledConnection.getConnection();
	        
		//  Statement st = conn.createStatement();
	        // ResultSet rs = st.executeQuery("select * from user");
//		  ResultSet rs = st.executeQuery("select * from EMP");
		//        int cnt = 1;
		//  while (rs.next()) {
	            // out.println((cnt++)+". Host:" +rs.getString("Host")+
	            //   " User:"+rs.getString("User")+" Password:"+rs.getString("Password") + " <br />");
		//      out.println((cnt++) + ". Empno:" + rs.getLong("empno") +
		//                " Ename: " + rs.getString("ename") + " Job: " + rs.getString("job") + " <br />");  
		//  }
		//  rs.close();
		//  st.close();
		//	} catch (NamingException e) {
		//e.printStackTrace(out);			
		//} catch (SQLException sqlE) {
		//sqlE.printStackTrace(out);
		//} finally {
		//if (conn!=null) 
		//  try {
		//	  conn.close();
		//  } catch (Exception ignore) {}
		//}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}