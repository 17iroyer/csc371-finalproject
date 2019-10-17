package program2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*; 

import com.mysql.jdbc.ResultSetMetaData;

public class Display4 extends JFrame {
  
  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_2;
  private JTextField txtNewInformation;
  private JTextField txtTables;
  private JTextField txtTableInfo;
  private JList list;
  private JButton btnSelect;
  private JScrollPane scrollPane;

  public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_16";
  public static final String LOGIN_NAME = "csc371_16";
  public static final String PASSWORD = "Password16";
  static Connection m_dbConn = null;
  private JTextField textField_1;
  
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Display4 frame = new Display4();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   * @throws SQLException 
   */
  public Display4() throws SQLException {
    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
    getContentPane().setBackground(Color.BLUE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1200, 800);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 259, 134, 162, 109, 154, 168, 0};
    gridBagLayout.rowHeights = new int[]{71, 269, 73, 80, 35, 181, 0, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    getContentPane().setLayout(gridBagLayout);
    
    txtTables = new JTextField();
    txtTables.setEditable(false);
    txtTables.setText("                         Tables");
    GridBagConstraints gbc_txtTables = new GridBagConstraints();
    gbc_txtTables.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtTables.insets = new Insets(0, 0, 5, 5);
    gbc_txtTables.gridx = 4;
    gbc_txtTables.gridy = 0;
    getContentPane().add(txtTables, gbc_txtTables);
    txtTables.setColumns(10);
    
    txtTableInfo = new JTextField();
    txtTableInfo.setHorizontalAlignment(SwingConstants.LEFT);
    txtTableInfo.setText("          Table Info");
    GridBagConstraints gbc_txtTableInfo = new GridBagConstraints();
    gbc_txtTableInfo.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtTableInfo.insets = new Insets(0, 0, 5, 5);
    gbc_txtTableInfo.gridx = 7;
    gbc_txtTableInfo.gridy = 0;
    getContentPane().add(txtTableInfo, gbc_txtTableInfo);
    txtTableInfo.setColumns(10);
    
    JList <String> list = new JList<String>();
    GridBagConstraints gbc_list = new GridBagConstraints();
    gbc_list.insets = new Insets(0, 0, 5, 5);
    gbc_list.fill = GridBagConstraints.BOTH;
    gbc_list.gridx = 4;
    gbc_list.gridy = 1;
    getContentPane().add(list, gbc_list);
    DefaultListModel<String> model = new DefaultListModel<String>();
    model.addElement("Player");
    model.addElement("Cartel_Group");
    model.addElement("Fleet");
    model.addElement("Cargo_Ship");
    model.addElement("Cruiser");
    model.addElement("Chat_System");
    model.addElement("Administrator");
    model.addElement("Oversees");
    model.addElement("Planet");
    model.addElement("Shipyard");
    model.addElement("Factory");
    model.addElement("Mine");
    model.addElement("Research_Center");
    list.setModel(model);
    
    JTable table = new JTable();
    
    
    btnSelect = new JButton("Select");
    btnSelect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selectAction(list, table);
      }});
    
    scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.gridwidth = 3;
    gbc_scrollPane.gridheight = 2;
    gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
    gbc_scrollPane.fill = GridBagConstraints.BOTH;
    gbc_scrollPane.gridx = 6;
    gbc_scrollPane.gridy = 1;
    getContentPane().add(scrollPane, gbc_scrollPane);
    GridBagConstraints gbc_btnSelect = new GridBagConstraints();
    gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
    gbc_btnSelect.gridx = 4;
    gbc_btnSelect.gridy = 2;
    getContentPane().add(btnSelect, gbc_btnSelect);
    scrollPane.setViewportView(table);
    
    
    txtNewInformation = new JTextField();
    txtNewInformation.setEditable(false);
    txtNewInformation.setText("                                           Command");
    GridBagConstraints gbc_txtNewInformation = new GridBagConstraints();
    gbc_txtNewInformation.gridwidth = 2;
    gbc_txtNewInformation.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtNewInformation.insets = new Insets(0, 0, 5, 5);
    gbc_txtNewInformation.gridx = 4;
    gbc_txtNewInformation.gridy = 4;
    getContentPane().add(txtNewInformation, gbc_txtNewInformation);
    txtNewInformation.setColumns(10);
    
    textField_2 = new JTextField();
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.gridwidth = 2;
    gbc_textField_2.insets = new Insets(0, 0, 5, 5);
    gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_2.gridx = 4;
    gbc_textField_2.gridy = 5;
    getContentPane().add(textField_2, gbc_textField_2);
    textField_2.setColumns(10);
    
    /**
     * Takes a command and trys to run it using Statement not Prepared Statement
     */
    JButton btnNewButton = new JButton("Run Command");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String runText = textField_2.getText();
        try {
          Statement stmt2 = m_dbConn.createStatement();
          stmt2.executeUpdate(runText);
          if(list.getSelectedValue() != null)
          {
            selectAction(list, table);
          }
          textField_1.setText("Success");
        } catch (SQLException e1) {
          textField_1.setText("Invalid Input Try Again !Check For Single Quotes!");
        }
      }
    });
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton.gridx = 7;
    gbc_btnNewButton.gridy = 5;
    getContentPane().add(btnNewButton, gbc_btnNewButton);
    
    textField_1 = new JTextField();
    textField_1.setEditable(false);
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.gridwidth = 2;
    gbc_textField_1.insets = new Insets(0, 0, 0, 5);
    gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_1.gridx = 4;
    gbc_textField_1.gridy = 6;
    getContentPane().add(textField_1, gbc_textField_1);
    textField_1.setColumns(10);
    textField_1.setText("Please put Insert, Delete, or Update Statement Above");
    
    
  }
  /**
   * Takes in a resultSet and changes it into a TableModel to be placed in the gui
   * Runs only after being called in selectAction
   * Based on "https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset"
   * @param rs
   * @return
   */
  public static TableModel resultSetToTableModel(ResultSet rs) {
    try {
        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
        int numCol = metaData.getColumnCount();
        Vector<String> colName = new Vector<String>();
        for (int col = 0; col < numCol; col++) {
            colName.addElement(metaData.getColumnLabel(col + 1));
        }
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

        while (rs.next()) {
            Vector<Object> newRow = new Vector<Object>();
            for (int x = 1; x <= numCol; x++) {
                newRow.addElement(rs.getObject(x));
            }
            rows.addElement(newRow);
        }
        return new DefaultTableModel(rows, colName);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
  /**
   * Gets the metadata of the table and gives it to TableModel
   * @param list
   * @param table
   */
  public void selectAction(JList <String> list, JTable table)
  {
    String printList = list.getSelectedValue();
    String showData = new String("Select * from " + printList);
    PreparedStatement stmt;
    try {
      stmt = m_dbConn.prepareStatement(showData);
      ResultSet set = stmt.executeQuery();
      TableModel dataModel = resultSetToTableModel(set);
      table.setModel(dataModel);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }  
  }

}
