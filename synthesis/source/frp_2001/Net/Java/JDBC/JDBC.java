import java.io.*;
import java.sql.*;

/**
* This application is a simple database tool which allows the transmission of
* SQL statements to databases via the JDBC interface.
* In order to access a database you need an appropriate JDBC driver for that
* database. A large assortment of JDBC drivers can be found at
* <A HREF=http://java.sun.com/products/jdbc/jdbc.drivers.html>
* http://java.sun.com/<WBR>products/<WBR>jdbc/<WBR>jdbc.drivers.html </A>.
* The driver classes must be located in a place where the Java interpreter can
* find them (ie, either they are somewhere below the current working directory
* or the $CLASSPATH variable is set up accordingly). For databases which can
* be accessed via the JDBC-ODBC bridge you can use the JDBC-ODBC driver which
* already comes with the JDK (though there is no javadoc documentation for 
* this package in the JDK). The driver name for this driver is:
* <P> <CODE> sun.jdbc.odbc.JdbcOdbcDriver </CODE> <P>
* The JDBC application needs two command-line arguments to start up: first,
* the full name of the driver class and second, the full URL of the database.
* The name of the driver class depends on the driver you are using.
* The URL for the database must have the following format:
* <P>
* <CODE> jdbc:<I>subprotocol</I>://<I>hostname</I>:<I>port</I>/<I>database</I>
* </CODE> <P>
* Let's imagine, for example, that you have an mSQL database called "DB"
* running on the server "datahost.mathema.de". First, you need the mSQL-JDBC
* driver (supplied by Imaginary). The driver class name is:
* <P> <CODE> com.imaginary.sql.msql.MsqlDriver </CODE> <P>
* By default, the mSQL database is listening on port 1114. The sub-protocol for
* mSQL is "msql" (as one would expect). Therefore the correct database URL
* would be:
* <P> <CODE> jdbc:msql://datahost.mathema.de:1114/DB </CODE> <P>
* These two parameters must be specified when starting the application.
* If everything is alright the application will display some information about
* the database and the driver and will give you a prompt:
* <P> <CODE> jdbc&gt; </CODE> <P>
* At this prompt you can enter SQL statements which are then sent to the
* database. If the statement was a SELECT statement the result set of the call
* will be printed. For statements which return a row count the row count will
* be displayed. <BR>
* For example: <P>
* <CODE>
* jdbc&gt; <B> select * from Stock </B> <BR>
* Result set: symbol, price <BR>
* SunSoft, 123.25 <BR>
* JavaSoft, 271.75 <BR>
* MATHEMA, 314.25 <BR>
* jdbc&gt; <B> delete from Stock where price &lt; 300 </B> <BR>
* 2 rows updated <BR>
* jdbc&gt; <B> select * from Stock </B> <BR>
* Result set: symbol, price <BR>
* MATHEMA, 314.25 <BR>
* jdbc&gt; <B> quit </B> <BR>
* </CODE> <P>
* The QUIT command is not an SQL command and tells the JDBC application to
* exit.
* <P>
* This application demonstrates the following Java features:
* <UL>
* <LI>Instantiating a JDBC driver and establishing a connection
* <LI>Communicating with a database by sending queries and updates
* <LI>Handling result sets
* <LI>Creating shortcuts for frequently used Java method calls
* </UL>
* <B>&copy; 1998 MATHEMA Software GmbH <P>
* This class is not intended for commercial use! </B> <P>
* <DL>
* <DT><B>Author:</B>
* <DD>Mirko Raner, MATHEMA Software GmbH
* </DL>
* <P>
* <SMALL>
* <A HREF="JDBC.java">Click here for the documented source code.</A>
* </SMALL>
**/
public class JDBC
{
	private JDBC()
	{
		// This class does not need a constructor.
	}

	/**
	* This is the main method which starts the JDBC application.
	* The command-line for starting it is as follows: <P>
	* <CODE> java JDBC <I>driver</I> <I>database</I> </CODE> <P>
	* <I>driver</I> and <I>database</I> must be the driver class name and the
	* database URL as described above.
	**/
	public static void main(String[] arg)
	{
		// Check for correct command-line arguments:
		//
		if (arg.length != 2)
		{
			System.err.println("Usage: java JDBC <driver> <database>");
			System.exit(1);
		}
		if (!arg[1].startsWith("jdbc:"))
		{
			System.err.println("JDBC URLs must begin with \"jdbc:\"");
			System.exit(1);
		}

		String driver = arg[0];
		String database = arg[1];
		Connection connection = null;

		try
		{
			// Install the JDBC driver and establish the database connection:
			//
			Class.forName(driver);
			connection = DriverManager.getConnection(database);

			// Display useful information about the database:
			//
			DatabaseMetaData meta = connection.getMetaData();
			if (meta == null)
			{
				println("No database meta-data available.");
			}
			else
			{
				String database_name = meta.getDatabaseProductName();
				String database_version = meta.getDatabaseProductVersion();
				String driver_name = meta.getDriverName();
				String driver_version = meta.getDriverVersion();
				print(database_name + " " + database_version);
				print("(" + driver_name + " " + driver_version);
				println(")");
			}
		}
		catch (Exception error)
		{
			handle(error);
		}

		println("Type QUIT to quit.\n");
		prompt();

		// Interactive loop:
		//
		BufferedReader input;
		input = new BufferedReader(new InputStreamReader(System.in));
		String command;

		while (!(command = readLine(input)).equalsIgnoreCase("QUIT"))
		{
			try
			{
				// Create a new SQL statement:
				//
				Statement statement = connection.createStatement();

				// Distinguish between updates and queries. This is done in a
				// very simple way: if the command starts with the word SELECT
				// the statement is executed as a query; otherwise it is
				// executed as an update:
	
				if (command.toUpperCase().startsWith("SELECT"))
				{
					// Send a query:
					//
					ResultSet result = statement.executeQuery(command);
					ResultSetMetaData info = result.getMetaData();
					int columns = info.getColumnCount();

					print("Result set: ");
					String separator = "";

					// Print column names of the result set:
					//
					for (int index = 1; index <= columns; index++)
					{
						print(separator+info.getColumnName(index));
						separator = ", ";
					}
					println("");

					// Print rows of the result set data:
					//
					while (result.next())
					{
						// Print columns of each row:
						//
						separator = "";
						for (int entry = 1; entry <= columns; entry++)
						{
							print(separator+result.getObject(entry));
							separator = ", ";
						}
						println("");
					}
				}
				else
				{
					// Send an update:
					//
					int result = statement.executeUpdate(command);
					if (result > 0)
					{
						println(result+" row"+ (result==1?"":"s") +" updated");
					}
				}
				prompt();
			}
			catch (Exception error)
			{
				handle(error);
				prompt();
			}
		}
		println("\nGoodbye...");
	}

	/**
	* This method is a shortcut method which reads a line from a BufferedReader
	* and also performs essential exception handling.
	**/
	protected static String readLine(BufferedReader input)
	{
		try
		{
			return input.readLine();
		}
		catch (IOException error)
		{
			return "";
		}
	}

	/**
	* This method is a shortcut for System.out.print .
	**/
	protected static void print(String string)
	{
		System.out.print(string);
	}

	/**
	* This method is a shortcut for System.out.println .
	**/
	protected static void println(String string)
	{
		System.out.println(string);
	}

	/**
	* This method prints the prompt <CODE> jdbc&gt; </CODE>.
	**/
	protected static void prompt()
	{
		System.out.print("jdbc> ");
		System.out.flush();
	}

	/**
	* This method prints the type and the detail message of a given exception.
	**/
	protected static void handle(Exception error)
	{
		println(error.getClass().getName() + " " + error.getMessage());
	}
}

