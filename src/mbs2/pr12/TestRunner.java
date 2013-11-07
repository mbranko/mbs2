package mbs2.pr12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import mbs2.util.ScriptRunner;

public class TestRunner {
  public static void main(String[] args) throws Exception {
    System.out.println("Kreiram demo bazu...");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/mbs2", "mbs2", "mbs2");
    BufferedReader init = new BufferedReader(new InputStreamReader(
        TestRunner.class.getResource("/mbs2/pr12/init.sql").openStream(), "UTF-8"));
    BufferedReader init2 = new BufferedReader(new InputStreamReader(
        TestRunner.class.getResource("/mbs2/pr12/init2.sql").openStream(), "UTF-8"));
    ScriptRunner sr = new ScriptRunner(conn, false, true);
    sr.setDelimiter(";", false);
    sr.runScript(init);
    sr.setDelimiter("//", false);
    sr.runScript(init2);
    conn.close();
    init.close();
    init2.close();
    
    System.out.println("Pokrecem primer 1...");
    Db1.main(new String[0]);
    System.out.println("Pokrecem primer 2...");
    Db2.main(new String[0]);
    System.out.println("Pokrecem primer 3...");
    Db3.main(new String[0]);
    
  }
}
