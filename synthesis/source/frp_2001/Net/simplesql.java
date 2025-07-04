package examples.jdbc.oracle;

import java.sql.*;
import java.util.Properties;

/**
 * This simple example shows how to execute DDL and DML via JDBC.
 * <p>
 * This example is run on the command line and is output to
 * System.out.
 * <p><h3>Build the Example</h3>  
 * <ol>
 * <li> Open a new command shell. 
 * <p><li>Set up this development shell as described in 
 * <a href=../../examples.html#environment>Setting up Your Environment for 
 * Building and Running the Examples</a>.
 * <p>
 * <ul><li>There are two pathnames required in your environment
 * 1) the directory in which the WebLogic shared library (native interface) resides, and
 * 2) the directory in which the vendor-supplied libraries from Oracle
 * reside (<i>bin</i> for NT or <i>lib</i> for UNIX). 
 * Be sure to include these pathnames in your path.
 * <p>
 * <li> For complete information, see <a
 * href="http://e-docs.bea.com/wls/docs60/oracle/install_jdbc.html#environment">Setting Up the Environment for Using 
 * WebLogic jDriver for Oracle</a>
 * in <i>Installing and Using WebLogic jDriver for Oracle</i></a>. 
 * The following sections contain instructions for specific platforms:
 * <p>
 * <ul>
 * <li><a
 * href="http://e-docs.bea.com/wls/docs60/oracle/install_jdbc.html#nt">Windows NT</a>
 * <li><a
 * href="http://e-docs.bea.com/wls/docs60/oracle/install_jdbc.html#solaris">Solaris</a>
 * <li><a
 * href="http://e-docs.bea.com/wls/docs60/oracle/install_jdbc.html#hp">HP</a>
 * </ul>
 * <p></ul>

 * <li> Change connection parameters to correspond to your Oracle configuration.
 * If you need more help, check the section on connecting
 * to a database in the programming guide, <a
 * href="http://e-docs.bea.com/wls/docs60/oracle/API_joci.html">Using WebLogic jDriver for Oracle</a>.
 * <p>
 * <li>Compile the example by executing the following command or by executing the <a href=../../examples.html#buildScripts>build script</a> 
 * provided for this example in the <font face="Courier New" size = -1>samples/examples/jdbc/oracle</font> 
 * directory. The script will perform the following step:
 * <p> 
 * <ul>
 * <li>Compile the simplesql class as shown in this example for <b>Windows NT/2000</b>:
 * <p>
 * <pre>  $ <b>javac -d %CLIENT_CLASSES% simplesql.java</b></pre>
 * </ol>
 * <p><h3>Run the Example</h3>
 * <ul>
 * <li>Execute the following command in your development shell: 
 * 
 * <pre><b>$ java examples.jdbc.oracle.simplesql</b></pre>
 * 
 * </ul>
 * <h3>There's More</h3>
 *
 * For more information about the WebLogic jDriver for Oracle, see   <a
 * href="http://e-docs.bea.com/wls/docs60/oracle/index.html"><i>Installing and Using WebLogic jDriver for Oracle</i></a>. 
 * <p>
 * @author Copyright (c) 1996-2000 by BEA Systems, Inc.  All Rights Reserved.
 */

public class simplesql {
  
  public static void main(String argv[])
  {
    java.sql.Connection conn = null;
    java.sql.Statement stmt  = null;
    
    try {
      Properties props = new Properties();
      props.put("user","scott");
      props.put("password","tiger");
      props.put("server","DEMO");

      Driver myDriver = (Driver) Class.forName("weblogic.jdbc.oci.Driver").newInstance();
      conn = myDriver.connect("jdbc:weblogic:oracle", props);
      stmt = conn.createStatement();
    
      try {
        stmt.execute("drop table empdemo");
        System.out.println("Table empdemo dropped.");
      }
      catch (SQLException e) {
        System.out.println("Table empdemo doesn't need to be dropped.");
      }
    
      stmt.execute("create table empdemo (empid integer, name varchar2(30), dept number(4))");
      System.out.println("Table empdemo created.");
    
      int numrows = stmt.executeUpdate("insert into empdemo values (0, 'John Smith', 12)");
      System.out.println("Number of rows inserted = " + numrows);
    
      numrows = stmt.executeUpdate("delete from empdemo where empid = 0");
      System.out.println("Number of rows deleted = " + numrows);
    } catch (Exception e) {
        System.out.println("SQLException was thrown: " + e.getMessage());
    } finally { //close connections and statements in a finally block
        try {
          if(stmt != null)
            stmt.close();
          if(conn != null)
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException was thrown: " + sqle.getMessage());
        }
    }
  }
}

