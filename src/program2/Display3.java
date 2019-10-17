package program2;

import java.awt.BorderLayout;

import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DropMode;

/**
 * Display 3
 * 
 * @author bk3036 Allows users to assign resources to tasks such as building
 *         different kinds of ships and producing baubles. For now we are only
 *         working within one planet, besania0, for the purpose of the demo.
 */
public class Display3 {
  // Connection to our Database
  public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_16";
  public static final String LOGIN_NAME = "csc371_16";
  public static final String PASSWORD = "Password16";
  static Connection m_dbConn = null;

  // variables
  int contribution = 0;
  int cruiserCost = 5000;
  int cargoCost = 5000;
  int baubleCost = 3;
  int cruiserid = 13;
  int cargoid = 27;
  int baubles = 0;

  GraphicsConfiguration gc;
  private final JTextArea resourceCount = new JTextArea();
  private final JTextArea factoryCount = new JTextArea();
  private final JTextArea shipyardCount = new JTextArea();
  private final JTextArea txtrResourceAssignment = new JTextArea();
  private final JButton buildCruiser = new JButton("Build Cruiser");
  private final JButton buildCargoShip = new JButton("Build Cargo Ship");
  private final JButton produceBaubles = new JButton("Produce Baubles");
  private final JTextArea txtrPercentResourcesTo = new JTextArea();
  private final JTextField resourceContribution_1 = new JTextField();

  public Display3() throws SQLException {

    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);

    // Here we keep track of starting id so we can make new cruisers :)
    try {
      String selectData = new String("select * from Cruiser;");
      PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
      ResultSet rs = stmt.executeQuery(selectData);
      while (rs.next()) {
        cruiserid++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // Same thing here, except with Cargo ships. We want everything to be unique :)
    try {
      String selectData = new String("select * from Cargo_Ship;");
      PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
      ResultSet rs = stmt.executeQuery(selectData);
      while (rs.next()) {
        cargoid++;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    JFrame frame = new JFrame(gc);

    frame.getContentPane().setBackground(Color.GRAY);
    frame.getContentPane().setForeground(Color.BLACK);
    frame.setBackground(Color.CYAN);

    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
        Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
    frame.getContentPane().setLayout(gridBagLayout);

    GridBagConstraints gbc_resourceCount = new GridBagConstraints();
    gbc_resourceCount.fill = GridBagConstraints.HORIZONTAL;
    gbc_resourceCount.anchor = GridBagConstraints.NORTH;
    gbc_resourceCount.insets = new Insets(0, 0, 5, 5);
    gbc_resourceCount.gridx = 1;
    gbc_resourceCount.gridy = 0;
    resourceCount.setDropMode(DropMode.INSERT);
    resourceCount.setBackground(Color.GRAY);
    resourceCount.setEditable(false);
    resourceCount.setWrapStyleWord(true);

    // Sum up all the players' resources from all planets and print it out.
    String selectData = new String("select sum(Resources) from Planet;");
    int resources = 0;
    try {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      stmt = m_dbConn.prepareStatement(selectData);
      rs = stmt.executeQuery(selectData);
      while (rs.next()) {
        resources = rs.getInt(1);

      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    resourceCount.setText("Resources: " + resources);
    frame.getContentPane().add(resourceCount, gbc_resourceCount);

    GridBagConstraints gbc_factoryCount = new GridBagConstraints();
    gbc_factoryCount.anchor = GridBagConstraints.NORTH;
    gbc_factoryCount.insets = new Insets(0, 0, 5, 5);
    gbc_factoryCount.fill = GridBagConstraints.HORIZONTAL;
    gbc_factoryCount.gridx = 3;
    gbc_factoryCount.gridy = 0;
    factoryCount.setBackground(Color.GRAY);
    factoryCount.setEditable(false);

    // Display the number of factories that we have.
    selectData = new String("select count(ID_Number) from Factory;");
    int factories = 0;
    try {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      stmt = m_dbConn.prepareStatement(selectData);
      rs = stmt.executeQuery(selectData);
      while (rs.next()) {
        factories = rs.getInt(1);

      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    factoryCount.setText("Factories: " + factories);
    frame.getContentPane().add(factoryCount, gbc_factoryCount);

    // Display the number of shipyards that we have.
    selectData = new String("select count(ID_Number) from Shipyard;");
    int shipyards = 0;
    try {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      stmt = m_dbConn.prepareStatement(selectData);
      rs = stmt.executeQuery(selectData);
      while (rs.next()) {
        shipyards = rs.getInt(1);

      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    GridBagConstraints gbc_buildCruiser = new GridBagConstraints();
    gbc_buildCruiser.insets = new Insets(0, 0, 5, 5);
    gbc_buildCruiser.gridx = 1;
    gbc_buildCruiser.gridy = 3;
    buildCruiser.setBackground(Color.LIGHT_GRAY);
    // This is where the beginning of the fun part is. When we press the
    // build cruiser button, we want to successfully build a cruiser, assign
    // it to a fleet, and update the resources on both the database and the gui.
    buildCruiser.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        contribution = Integer.parseInt(resourceContribution_1.getText());
        String contributionString = Integer.toString(contribution);
        String selectData = "update Planet set Resources = Resources - " + contributionString + " where Resources >= "
            + contributionString + " and PlanetID = 'besania0';";
        contribution = Integer.parseInt(resourceContribution_1.getText());
        try {

          PreparedStatement stmt = null;

          stmt = m_dbConn.prepareStatement(selectData);
          stmt.executeUpdate(selectData);
          System.out.println("contribution made");
          // Loop through, making ships as long as we have enough resources to
          for (int i = contribution; i >= cruiserCost; i = i - cruiserCost) {
            makeCruiser();

          }
          stmt.close();
          // redisplay updated resources since we are spending some
          selectData = new String("call sumResources();");
          int resources = 0;
          try {
            stmt = null;
            ResultSet rs = null;
            stmt = m_dbConn.prepareStatement(selectData);
            rs = stmt.executeQuery(selectData);
            while (rs.next()) {
              resources = rs.getInt(1);

            }
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
          resourceCount.setText("Resources: " + resources);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });

    GridBagConstraints gbc_shipyardCount = new GridBagConstraints();
    gbc_shipyardCount.anchor = GridBagConstraints.NORTH;
    gbc_shipyardCount.insets = new Insets(0, 0, 5, 5);
    gbc_shipyardCount.fill = GridBagConstraints.HORIZONTAL;
    gbc_shipyardCount.gridx = 5;
    gbc_shipyardCount.gridy = 0;
    shipyardCount.setBackground(Color.GRAY);

    shipyardCount.setText("Shipyards: " + shipyards);
    frame.getContentPane().add(shipyardCount, gbc_shipyardCount);

    GridBagConstraints gbc_txtrResourceAssignment = new GridBagConstraints();
    gbc_txtrResourceAssignment.insets = new Insets(0, 0, 5, 5);
    gbc_txtrResourceAssignment.fill = GridBagConstraints.BOTH;
    gbc_txtrResourceAssignment.gridx = 1;
    gbc_txtrResourceAssignment.gridy = 2;
    txtrResourceAssignment.setBackground(Color.GRAY);
    txtrResourceAssignment.setEditable(false);
    txtrResourceAssignment.setText("Resource Assignment");
    frame.getContentPane().add(txtrResourceAssignment, gbc_txtrResourceAssignment);
    frame.getContentPane().add(buildCruiser, gbc_buildCruiser);

    GridBagConstraints gbc_txtrPercentResourcesTo = new GridBagConstraints();
    gbc_txtrPercentResourcesTo.insets = new Insets(0, 0, 5, 5);
    gbc_txtrPercentResourcesTo.fill = GridBagConstraints.BOTH;
    gbc_txtrPercentResourcesTo.gridx = 6;
    gbc_txtrPercentResourcesTo.gridy = 4;
    txtrPercentResourcesTo.setBackground(Color.GRAY);
    txtrPercentResourcesTo.setText("resource contribution");
    frame.getContentPane().add(txtrPercentResourcesTo, gbc_txtrPercentResourcesTo);

    GridBagConstraints gbc_buildCargoShip = new GridBagConstraints();
    gbc_buildCargoShip.insets = new Insets(0, 0, 5, 5);
    gbc_buildCargoShip.gridx = 1;
    gbc_buildCargoShip.gridy = 5;
    buildCargoShip.setBackground(Color.LIGHT_GRAY);
    // The brunt of the code for the build cargo ship button
    buildCargoShip.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        contribution = Integer.parseInt(resourceContribution_1.getText());
        String contributionString = Integer.toString(contribution);
        String selectData = "update Planet set Resources = Resources - " + contributionString + " where Resources > "
            + contributionString + " and PlanetID = 'besania0';";
        contribution = Integer.parseInt(resourceContribution_1.getText());
        try {

          PreparedStatement stmt = null;

          stmt = m_dbConn.prepareStatement(selectData);
          stmt.executeUpdate(selectData);
          System.out.println("contribution made");
          for (int i = contribution; i > cruiserCost; i = i - cruiserCost) {
            makeCargoShip();

          }
          stmt.close();
          // redisplay updated resources since we are spending some
          selectData = new String("call sumResources();");
          int resources = 0;
          try {
            stmt = null;
            ResultSet rs = null;
            stmt = m_dbConn.prepareStatement(selectData);
            rs = stmt.executeQuery(selectData);
            while (rs.next()) {
              resources = rs.getInt(1);

            }
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
          resourceCount.setText("Resources: " + resources);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }

      }
    });

    frame.getContentPane().add(buildCargoShip, gbc_buildCargoShip);
    resourceContribution_1.setText("0");
    resourceContribution_1.setColumns(10);

    GridBagConstraints gbc_resourceContribution_1 = new GridBagConstraints();
    gbc_resourceContribution_1.insets = new Insets(0, 0, 5, 5);
    gbc_resourceContribution_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_resourceContribution_1.gridx = 6;
    gbc_resourceContribution_1.gridy = 5;
    frame.getContentPane().add(resourceContribution_1, gbc_resourceContribution_1);
    resourceContribution_1.addMouseListener(new MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        // System.out.println("clicky");
        contribution = Integer.parseInt(resourceContribution_1.getText());
        // System.out.print(contribution);
      }
    });

    GridBagConstraints gbc_produceBaubles = new GridBagConstraints();
    gbc_produceBaubles.insets = new Insets(0, 0, 5, 5);
    gbc_produceBaubles.gridx = 1;
    gbc_produceBaubles.gridy = 7;
    produceBaubles.setBackground(Color.LIGHT_GRAY);
    produceBaubles.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    // This is where we press the produce baubles button.
    // Baubles have a rate of 3 Resources : 1 Bauble
    produceBaubles.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        contribution = Integer.parseInt(resourceContribution_1.getText());
        // We keep track of the amount of baubles we are going to make 
        // and then we call makeBaubles(baubles)
        for (int i = contribution; i >= baubleCost; i = i - baubleCost) {
          baubles++;

        }
        try {
          makeBaubles(baubles);
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        baubles = 0;
        // redisplay resources on gui to update after baubles are made
        PreparedStatement stmt = null;
        String selectData = new String("call sumResources();");
        int resource = 0;
        try {
          stmt = null;
          ResultSet rs = null;
          stmt = m_dbConn.prepareStatement(selectData);
          rs = stmt.executeQuery(selectData);
          while (rs.next()) {
            resource = rs.getInt(1);

          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
        resourceCount.setText("Resources: " + resource);

      }
    });
    frame.getContentPane().add(produceBaubles, gbc_produceBaubles);

    frame.setSize(706, 381);
    // frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    // I don't want anyone resizing my stuff man >:(
    frame.setResizable(false);

  }

  public static void main(String[] args) throws SQLException {
    Display3 test = new Display3();
  }
  // This method makes a cruiser with a unique id and adds it to a random fleet.
  public void makeCruiser() throws SQLException {
    String insertData = new String(
        "INSERT INTO Cruiser (ID_Number,Resources,Weapons,Upgrades,FleetID) VALUES (?,?,?,?,?)");
    PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
    String idnumber = Integer.toString(cruiserid);
    String fleet;
    Random random = new Random();
    int randomnumber = random.nextInt(4 - 1 + 1) + 1;
    stmt2.setString(1, "cruiser" + idnumber);
    stmt2.setString(2, "5000");
    stmt2.setString(3, "250");
    stmt2.setString(4, "5");
    if (randomnumber == 1) {
      stmt2.setString(5, "fleet2657");
    } else if (randomnumber == 2) {
      stmt2.setString(5, "fleet1234");
    } else if (randomnumber == 3) {
      stmt2.setString(5, "fleet5678");
    } else if (randomnumber == 4) {
      stmt2.setString(5, "fleet9101");
    }

    stmt2.executeUpdate();
    cruiserid++;
  }
// This method makes a cargo ship with a unique id and adds it to a random fleet.
  public void makeCargoShip() throws SQLException {
    String insertData = new String(
        "INSERT INTO Cargo_Ship (ID_Number,Resources,Weapons,Upgrades,FleetID) VALUES (?,?,?,?,?)");
    PreparedStatement stmt2 = m_dbConn.prepareStatement(insertData);
    String idnumber = Integer.toString(cargoid);
    Random random = new Random();
    int randomnumber = random.nextInt(4 - 1 + 1) + 1;
    stmt2.setString(1, "cargo" + idnumber);
    stmt2.setString(2, "10000");
    stmt2.setString(3, "100");
    stmt2.setString(4, "25");
    if (randomnumber == 1) {
      stmt2.setString(5, "fleet2657");
    } else if (randomnumber == 2) {
      stmt2.setString(5, "fleet1234");
    } else if (randomnumber == 3) {
      stmt2.setString(5, "fleet5678");
    } else if (randomnumber == 4) {
      stmt2.setString(5, "fleet9101");
    }
    stmt2.executeUpdate();
    cargoid++;

  }
// This method makes the required amount of baubles and updates the resources.F
  public void makeBaubles(int baubles) throws SQLException {
    contribution = Integer.parseInt(resourceContribution_1.getText());
    String contributionString = Integer.toString(contribution);
    String selectData = "update Planet set Resources = Resources - " + contributionString + " where Resources > "
        + contributionString + " and PlanetID = 'besania0';";
    try {
      PreparedStatement stmt = null;

      stmt = m_dbConn.prepareStatement(selectData);
      stmt.executeUpdate(selectData);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String baublesString = Integer.toString(baubles);
    String insertData = "update Planet set Baubles = Baubles + " + baublesString + " where PlanetID = 'besania0';";
    try {
      PreparedStatement stmt = m_dbConn.prepareStatement(insertData);
      stmt.executeUpdate(insertData);
      System.out.println("baubles produced");

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
