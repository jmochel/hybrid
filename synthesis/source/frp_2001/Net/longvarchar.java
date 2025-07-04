package examples.jdbc.oracle;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * This example is a standalone application demonstrating a method
 * for inserting and retrieving data from Oracle long raw columns.
 * <p>
 * To use this example, pass four command line parameters to the
 * <tt>main()</tt> method. The first three parameters are for database
 * access, and the fourth is the name of a file to be inserted, and 
 * then retrieved from the database.
 * <p>
 * Please note that this example uses a simple method for obtaining
 * result sets, update counts, and/or the output parameter values it may
 * generate. This is always possible with simple, known queries.
 * When executing complex or unknown possibly multi-statement SQL
 * or stored procedures, you should use the example in <tt><a href=../oracle/storedprocs.java>storedprocs.java</a></tt>
 * to ensure that all results become available.
 * <p>
 * <h3>Build the Example</h3>  
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
 * <li>Compile the longvarchar class as shown in this example for <b>Windows NT/2000</b>:
 * <p>
 * <pre>  $ <b>javac -d %CLIENT_CLASSES% longvarchar.java</b></pre>
 * </ol>
 * <p><h3>Run the Example</h3>
 * <ul>
 * <li>Execute the following command in your development shell: 
 * 
 * <pre><b>$ java examples.jdbc.oracle.longvarchar</font><I>USERNAME PASSWORD DBMS FILENAME</I></b></pre>
 *
 * <p>Where:
 * <ul>
 * <li><pre><I><b>USERNAME</b></I>: Username for the database connection.</pre>
 * <li><pre><i><b>PASSWORD</b></i>: Password for the user.</pre>
 * <li><pre><i><b>DBMS</b></i>: TNS alias for the DBMS server.</pre>
 * <li><pre><i><b>FILENAME</b></i>: Name of the file to be inserted into the DBMS.</pre>
 * <pre>The file returned by a query is the same, with ".out" appended.</pre>
 * </ul>
 * </ul>
 * <h3>There's More</h3>
 *
 * For more information about the WebLogic jDriver for Oracle, see <a
 * href="http://e-docs.bea.com/wls/docs60/oracle/index.html"><i>Installing and Using WebLogic jDriver for Oracle</i></a>. 
 * <p>
 * @author  Copyright (c) 1998-2000 by BEA Systems, Inc. All Rights Reserved.
 */
public class longvarchar {

  /**
   * Runs the application from the command lind. This app takes
   * four parameters:
   * <ul>
   * <li>Username for the database connection
   * <li>Password for the user
   * <li>TNS alias for the DBMS server
   * <li>Name of the file to be inserted into the DBMS.
   * The file returned by a query is the same, with ".out" appended.
   * </ul>
   * @param args              Username, password, DBMS, and filename
   */
  public static void main(String[] args) {
    
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    FileInputStream fis = null;
    FileOutputStream fos = null;
    
    if (args.length < 4) {
      System.out.println("Usage: java examples.jdbc.oracle.longvarchar " +
                         "USER PASSWORD SERVER SRCFILE");
      return;
    }

    try {
      // Set up connection properties and instantiate connection.
      Properties props = new Properties();
      props.put("user", args[0]);
      props.put("password", args[1]);
      props.put("server", args[2]);

      Driver myDriver = (Driver) Class.forName("weblogic.jdbc.oci.Driver").newInstance();   
      conn = myDriver.connect("jdbc:weblogic:oracle", props);
      
      // Clean up any remnants of an earlier invocation of this
      // example.
      stmt = conn.createStatement();
      try {
	stmt.executeUpdate("drop table WLLongVarChar");
      } 
      catch (SQLException sqe) {
	// ORA-00942: table or view does not exist any other exception
	// should be reported at this point
	if (sqe.getErrorCode() != 942)
	  sqe.printStackTrace();
      }
      // Create the table to be used for this example.
      stmt.executeUpdate("create table WLLongVarChar (col1 long raw)");
      stmt.close();

      // Open the file for read and attach the stream to a
      // PreparedStatement.
      fis = new FileInputStream(args[3]);
      ps = conn.prepareStatement("insert into WLLongVarChar values(?)");
      ps.setBinaryStream(1, fis, fis.available());

      // Execute the PreparedStatement and save the contents of the
      // file to the db.
      ps.executeUpdate();
      ps.close();
      fis.close();

      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from WLLongVarChar");
      while (rs.next()) {
        // Here, we select the column back from the db, and write the
        // contents of the long raw column to a new file, with the
        // extension ".out" appended to the input file's name.
	fos = new FileOutputStream(args[3] + ".out");
	BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream(1));
	BufferedOutputStream bos = new BufferedOutputStream(fos);
	int x;
	while ((x = bis.read()) != -1)
	  bos.write((byte) x);
	bos.flush();
	fos.close();
	bis.close();
	System.out.println("The output is in the file " + args[3] + ".out");
      }
      rs.close();
    }
    catch (Exception e) {
      e.printStackTrace();  
    }
    finally {
      // Always clean up resources you have used in a finally block so
      // that the cleanup takes place even in the event of a
      // failure.
      try {
	if (stmt != null) {
	  try {
	    stmt.executeUpdate("drop table WLLongVarChar");
	    stmt.close();
	  }
	  catch (Exception e) {}
	}
	conn.close();
      }
      catch (Exception e) {
	e.printStackTrace();
      }
    }
  }
}


    
