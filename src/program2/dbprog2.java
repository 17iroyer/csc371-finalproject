package program2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbprog2 {

  public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_16";
  public static final String LOGIN_NAME = "csc371_16";
  public static final String PASSWORD = "Password16";
  static Connection m_dbConn = null;
  
  int factoryID = 78;
  int mineID = 1;
  int rcID = 150;
  int sID = 27;

  public dbprog2() throws SQLException {
    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
    DatabaseMetaData meta = m_dbConn.getMetaData();

  }

  public boolean activateJDBC() {
    try {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return true;
  }

  public static void main(String[] args) throws SQLException {
    dbprog2 connector = new dbprog2();

    Boolean connected = connector.activateJDBC();
    if (connected) {
      System.out.println("connected");
    }
    //connector.createTables("CREATE TABLE Player (PlayerName VARCHAR(25) NOT NULL, Password VARCHAR(25) NOT NULL, Money INT NOT NULL, Private_Messages VARCHAR(300), Private_Chat_System VARCHAR(300), PRIMARY KEY (PlayerName));");
    //connector.createTables("Create Table Cartel_Group (CartelName VarChar(25), Resources INT, MessageBoard VarChar(300)," + "Primary Key(CartelName));" + "");
    //connector.createTables("ALTER TABLE Player ADD CartelName VARCHAR(25), ADD FOREIGN KEY(CartelName) REFERENCES Cartel_Group(CartelName) ON DELETE SET NULL ON UPDATE CASCADE;");
    //connector.createTables("Alter Table Cartel_Group add Leader VarChar(25), add foreign key(Leader) references Player(PlayerName) on Delete no action on update cascade;");
    //connector.createTables("Create Table Fleet (FleetID VarChar(25) NOT NULL, PlayerName VarChar(25), Primary key(FleetID),FOREIGN KEY (PlayerName) REFERENCES Player(PlayerName) on Delete set  null on update cascade);");
    //connector.createTables("Create Table Cargo_Ship (ID_Number VarChar(25) NOT NULL,Resources INT NOT NULL,Weapons INT NOT NULL,Upgrades INT NOT NULL,FleetID VarChar(25) NOT NULL,Primary key(ID_Number),FOREIGN KEY (FleetID) REFERENCES Fleet(FleetID) ON DELETE NO ACTION ON UPDATE CASCADE);");
    //connector.createTables("Create Table Cruiser (ID_Number VarChar(25) NOT NULL, Resources INT NOT NULL, Weapons INT NOT NULL, Upgrades INT NOT NULL, FleetID VarChar(25) NOT NULL, Primary key(ID_Number), Foreign key (FleetID) references Fleet(FleetID) on Delete no action on update cascade);");
    //connector.createTables("Create Table Chat_System (PrivateChatSystem VarChar(300),CartelName VarChar(25),Foreign key (CartelName) references Cartel_Group(CartelName) on Delete no action on update cascade, Primary Key(CartelName));");
    //connector.createTables("Create Table Administrator (AdminName VarChar(25) NOT NULL,PassCode VarChar(25) NOT NULL, Primary Key(AdminName));");
    //connector.createTables("Create Table Oversees(AdminName VarChar(25) NOT NULL, PlayerName VarChar(25) NOT NULL,Foreign key (AdminName) references Administrator(AdminName) on Delete no action on update cascade,Foreign key (PlayerName) references Player(PlayerName) on Delete no action on update cascade);");
    //connector.createTables("Create Table Planet (PlanetID VarChar(25) NOT NULL,Baubles INT NOT NULL,Resources INT NOT NULL,X_Coordinate FLOAT NOT NULL,Y_Coordinate FLOAT NOT NULL,Z_Coordinate FLOAT NOT NULL,PlayerName VarChar(25),Primary Key(PlanetID),Foreign key (PlayerName) references Player(PlayerName) on delete set null on update cascade);");
    //connector.createTables("Create Table Shipyard (ID_Number VarChar(25) NOT NULL,CostOfBuilding INT,PlanetID VarChar(25),Primary Key(ID_Number),Foreign key (PlanetID) references Planet(PlanetID) on delete no action on update cascade);");
    //connector.createTables("Create Table Factory (ID_Number VarChar(25) NOT NULL,CostOfBuilding INT,PlanetID VarChar(25),Primary Key(ID_Number),Foreign key (PlanetID) references Planet(PlanetID) on delete no action on update cascade);");
    //connector.createTables("Create Table Mine (ID_Number VarChar(25) NOT NULL,CostOfBuilding INT,PlanetID VarChar(25),Primary Key(ID_Number),Foreign key (PlanetID) references Planet(PlanetID) on delete no action on update cascade);");
    //connector.createTables("Create Table Research_Center (ID_Number VarChar(25) NOT NULL, CostOfBuilding INT,CurrentResearchLevel INT NOT NULL, PlanetID VarChar(25),Primary Key(ID_Number), Foreign key (PlanetID) references Planet(PlanetID) on delete set null on update cascade);");
    //################# Start Populate Tables ########################
    //connector.populateTablePlayer();
    //connector.populateTableCartelGroup();
    //connector.populateTableChatSystem();
    //connector.populateTableAdmin();
    //connector.populateTableFleet();
    //connector.populateTableCruiser();
    //connector.populateTableCargoShip();
    //connector.populateTablePlanet();
    //connector.populateTableShipyard();
    //connector.populateTableFactory();
    //connector.populateTableMine();
    connector.populateTableResearchCenter();
    
  }
  

  public void createTables(String statement) throws SQLException {
    String insertData = new String(statement);
    PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
    int rowsAdded = stmt2.executeUpdate();
    if (rowsAdded == 1) {
      System.out.println("Added");
    }
  }
  
  public void populateTablePlayer() throws SQLException {
	  String insertData = new String("INSERT INTO Player (PlayerName,Password,Money,Private_Messages,Private_Chat_System,CartelName) VALUES (?,?,?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "Best Bill");
      stmt2.setString(2, "password123");
      stmt2.setString(3, "300003");
      stmt2.setString(4, "Hey ho hey hee");
      stmt2.setString(5, "Let's not play ever again");
      stmt2.setString(6, "The Best Boys");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableCartelGroup() throws SQLException {
	  String insertData = new String("INSERT INTO Cartel_Group (CartelName,Resources,MessageBoard,Leader) VALUES (?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "The Best Boys");
      stmt2.setString(2, "1000000");
      stmt2.setString(3, "Message of the day");
      stmt2.setString(4, "Best Bill");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableChatSystem() throws SQLException {
	  String insertData = new String("INSERT INTO Chat_System (PrivateChatSystem,CartelName) VALUES (?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "Hello you person you");
      stmt2.setString(2, "The Best Boys");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableAdmin() throws SQLException {
	  String insertData = new String("INSERT INTO Administrator (AdminName,PassCode) VALUES (?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "watcher20");
      stmt2.setString(2, "thewatchers");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableFleet() throws SQLException {
	  String insertData = new String("INSERT INTO Fleet (FleetID,PlayerName) VALUES (?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "fleet9101");
      stmt2.setString(2, "Bill");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableCruiser() throws SQLException {
	  String insertData = new String("INSERT INTO Cruiser (ID_Number,Resources,Weapons,Upgrades,FleetID) VALUES (?,?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "cruiser17");
      stmt2.setString(2, "5000");
      stmt2.setString(3, "250");
      stmt2.setString(4, "5");
      stmt2.setString(5, "fleet2657");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableCargoShip() throws SQLException {
	  String insertData = new String("INSERT INTO Cargo_Ship (ID_Number,Resources,Weapons,Upgrades,FleetID) VALUES (?,?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "cargo31");
      stmt2.setString(2, "10000");
      stmt2.setString(3, "100");
      stmt2.setString(4, "25");
      stmt2.setString(5, "fleet5678");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTablePlanet() throws SQLException {
	  String insertData = new String("INSERT INTO Planet (PlanetID,Baubles,Resources,X_Coordinate,Y_Coordinate, Z_Coordinate, PlayerName) VALUES (?,?,?,?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "besania15");
      stmt2.setString(2, "10000");
      stmt2.setString(3, "25000");
      stmt2.setString(4, "756");
      stmt2.setString(5, "-111");
      stmt2.setString(6, "25");
      stmt2.setString(7, "Joey");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableShipyard() throws SQLException {
	  String insertData = new String("INSERT INTO Shipyard (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "shipyard31");
      stmt2.setString(2, "100");
      stmt2.setString(3, "besania15");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableShipyard(String name) throws SQLException {
	  String planet = "";
	  String selectData = new String("call GetPlanetId('" + name + "');");
	  PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	  ResultSet rs = stmt.executeQuery(selectData);
	  
	  if (rs.next()) {
		  //money = rs.getInt("Money");
		  planet = rs.getString("PlanetID");
	  }
	  
	  if(!((getMoney(name) - 100) > 0)) {
		  return;
	  }

      String updateData = "update Player set Money = Money - 100 where PlayerName = '" + name + "';";
      PreparedStatement stmt3 = m_dbConn.prepareStatement(updateData);
      stmt3.executeUpdate(updateData);
      
	  String insertData = new String("INSERT INTO Shipyard (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "shipyard" + sID++);
      stmt2.setString(2, "100");
      stmt2.setString(3, planet);

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
      
  }
  
  public void populateTableFactory() throws SQLException {
	  String insertData = new String("INSERT INTO Factory (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "factory82");
      stmt2.setString(2, "500");
      stmt2.setString(3, "besania15");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableFactory(String name) throws SQLException {
	  String planet = "";
	  String selectData = new String("select * from Planet where PlayerName = '" + name + "';");
	  PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	  ResultSet rs = stmt.executeQuery(selectData);
	  
	  if (rs.next()) {
		  //money = rs.getInt("Money");
		  planet = rs.getString("PlanetID");
	  }
	  
	  if(!((getMoney(name) - 500) > 0)) {
		  return;
	  }

      String updateData = "update Player set Money = Money - 500 where PlayerName = '" + name + "';";
      PreparedStatement stmt3 = m_dbConn.prepareStatement(updateData);
      stmt3.executeUpdate(updateData);
      
	  String insertData = new String("INSERT INTO Factory (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "factory" + factoryID++);
      stmt2.setString(2, "500");
      stmt2.setString(3, planet);

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
      
  }
  
  public void populateTableMine(String name) throws SQLException {
	  String planet = "";
	  String selectData = new String("select * from Planet where PlayerName = '" + name + "';");
	  PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	  ResultSet rs = stmt.executeQuery(selectData);
	  
	  if (rs.next()) {
		  planet = rs.getString("PlanetID");
	  }
	  
	  if(!((getMoney(name) - 25) > 0)) {
		  return;
	  }

      String updateData = "update Player set Money = Money - 25 where PlayerName = '" + name + "';";
      PreparedStatement stmt3 = m_dbConn.prepareStatement(updateData);
      stmt3.executeUpdate(updateData);
      
	  String insertData = new String("INSERT INTO Mine (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "mine" + mineID++);
      stmt2.setString(2, "25");
      stmt2.setString(3, planet);

      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
      
  }
  
  public void populateTableMine() throws SQLException {
	  String insertData = new String("INSERT INTO Mine (ID_Number,CostOfBuilding,PlanetID) VALUES (?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "mine5");
      stmt2.setString(2, "500");
      stmt2.setString(3, "besania15");

      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableResearchCenter() throws SQLException {
	  String insertData = new String("INSERT INTO Research_Center (ID_Number,CostOfBuilding,CurrentResearchLevel,PlanetID) VALUES (?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "research154");
      stmt2.setString(2, "2500");
      stmt2.setString(3, "52");
      stmt2.setString(4, "besania15");
      
      // When I need to set a primitive type as null.
      //stmt2.setNull(2, java.sql.Types.INTEGER);
      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
  }
  
  public void populateTableResearchCenter(String name) throws SQLException {
	  String planet = "";
	  String selectData = new String("select * from Planet where PlayerName = '" + name + "';");
	  PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	  ResultSet rs = stmt.executeQuery(selectData);
	  
	  if (rs.next()) {
		  planet = rs.getString("PlanetID");
	  }
	  
	  if(!((getMoney(name) - 2500) > 0)) {
		  return;
	  }

      String updateData = "update Player set Money = Money - 2500 where PlayerName = '" + name + "';";
      PreparedStatement stmt3 = m_dbConn.prepareStatement(updateData);
      stmt3.executeUpdate(updateData);
      
	  String insertData = new String("INSERT INTO Research_Center (ID_Number,CostOfBuilding,CurrentResearchLevel,PlanetID) VALUES (?,?,?,?)");
      PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
      stmt2.setString(1, "research" + rcID++);
      stmt2.setString(2, "2500");
      stmt2.setString(3, "52");
      stmt2.setString(4, planet);

      int rowsAdded = stmt2.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
      
  }
  
  public int getMoney(String name) throws SQLException {
	  int money = 0;

	  String selectData = new String("select * from Player where PlayerName = '" + name + "';");
	  PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	  ResultSet rs = stmt.executeQuery(selectData);
	  
	  if (rs.next()) {
		  money = rs.getInt("Money");
	  }

	  
	  return money;
  }
  
  public void fIdTracker() {
	    try {
	        String selectData = new String("select * from Factory;");
	        PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	        ResultSet rs = stmt.executeQuery(selectData);
	        while (rs.next()) {
	          factoryID++;
	        }
	        System.out.println("" + factoryID);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
  }
  
  public void mIdTracker() {
	    try {
	        String selectData = new String("select * from Mine;");
	        PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	        ResultSet rs = stmt.executeQuery(selectData);
	        while (rs.next()) {
	          mineID++;
	        }
	        System.out.println("" + mineID);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
  }
  
  public void rIdTracker() {
	    try {
	        String selectData = new String("select * from Research_Center;");
	        PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	        ResultSet rs = stmt.executeQuery(selectData);
	        while (rs.next()) {
	          rcID++;
	        }
	        System.out.println("" + rcID);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
  }
  
  public void sIdTracker() {
	    try {
	        String selectData = new String("select * from Shipyard;");
	        PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
	        ResultSet rs = stmt.executeQuery(selectData);
	        while (rs.next()) {
	          sID++;
	        }
	        System.out.println("" + sID);
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
  }
  

}
