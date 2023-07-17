package Database;

import org.json.JSONObject;
import org.openqa.selenium.json.JsonOutput;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;


public class Database
{

    private final PGSimpleDataSource ds;
    public Database()
    {
        //Get the database info from the db Object(Not included in github)
        DatabaseConnectionInfo databaseConnectionInfo = new DatabaseConnectionInfo();

        //Set up the database
        this.ds = new PGSimpleDataSource();

        //Connect to the url
        this.ds.setURL(databaseConnectionInfo.connectionURL);

        //Connect to cluster
        this.ds.setUser(databaseConnectionInfo.databaseUsername);
        this.ds.setPassword(databaseConnectionInfo.databasePassword);

        //Request data from the database
        try (Connection connection = ds.getConnection())
        {
            //Create a statement
            Statement stmt = connection.createStatement();

            //Execute query
            ResultSet rs = stmt.executeQuery("CREATE TABLE defultdb.accounts(first_name STRING, last_name STRING, email STRING, username STRING, password STRING, salt STRING);");

            //Close result set
            rs.close();


        } catch (SQLException e)
        {
            System.out.printf("sql state = [%s]\ncause = [%s]\nmessage = [%s]\n",
                    e.getSQLState(), e.getCause(), e.getMessage());
        }

        
    }

    public String[] requestUserData(String userName)
    {
        //Request data from the database
        try (Connection connection = ds.getConnection())
        {
            //Create a statement
            Statement stmt = connection.createStatement();

            //Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM accounts WHERE username = " + userName);

            String[] userData = new String[6];
            for (int i = 0; i < 6; i++)
            {
                userData [i] = rs.getString(i);
            }

            //Close result set
            rs.close();

            return userData;

        } catch (SQLException e)
        {
            System.out.printf("sql state = [%s]\ncause = [%s]\nmessage = [%s]\n",
                    e.getSQLState(), e.getCause(), e.getMessage());
        }
        return new String[0];
    }
}
