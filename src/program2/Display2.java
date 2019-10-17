package program2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JList;
import java.awt.Font;
/**
 * Display 2
 * @author ir6921
 * Used to send/view messages sent between players
 */
public class Display2 extends JFrame {

  public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_16";
  public static final String LOGIN_NAME = "csc371_16";
  public static final String PASSWORD = "Password16";
  static Connection m_dbConn = null;
  private JPanel contentPane;
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Display2 frame = new Display2();
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
  public Display2() throws SQLException {
    m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 712, 459);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(30, 144, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[]{41, 143, 83, 165, 0, 165, 0};
    gbl_contentPane.rowHeights = new int[]{0, 43, 49, 0, 0, 0, 0};
    gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
    contentPane.setLayout(gbl_contentPane);

    //Fetch the list of player names and input them to the list initialization
    JList <String> listOfPlayers = new JList<String>();
    DefaultListModel<String>model = new DefaultListModel<String>();
    
    String selectData = new String("SELECT PlayerName FROM Player");
    PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
    ResultSet rs = stmt.executeQuery(selectData);
    while (rs.next()) {
      model.addElement(rs.getString("PlayerName"));
    }
    
    //Add the model to the list object
    listOfPlayers.setModel(model);
    
    
    JTextArea recentMessageText = new JTextArea();
    recentMessageText.setLineWrap(true);
    recentMessageText.setEditable(false);
    GridBagConstraints gbc_recentMessageText = new GridBagConstraints();
    gbc_recentMessageText.insets = new Insets(0, 0, 5, 0);
    gbc_recentMessageText.fill = GridBagConstraints.BOTH;
    gbc_recentMessageText.gridx = 5;
    gbc_recentMessageText.gridy = 3;
    contentPane.add(recentMessageText, gbc_recentMessageText);
    
    JTextArea senderText = new JTextArea();
    senderText.setLineWrap(true);
    senderText.setFont(new Font("Dialog", Font.BOLD, 16));
    senderText.setEditable(false);
    GridBagConstraints gbc_senderText = new GridBagConstraints();
    gbc_senderText.gridwidth = 3;
    gbc_senderText.insets = new Insets(0, 0, 5, 5);
    gbc_senderText.fill = GridBagConstraints.BOTH;
    gbc_senderText.gridx = 3;
    gbc_senderText.gridy = 1;
    contentPane.add(senderText, gbc_senderText);
    
    JButton selectSenderButton = new JButton("User->");    

    //Sender text box is updated by mouse click
    selectSenderButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String selectedPlayer = listOfPlayers.getSelectedValue();
        
        //Update the user text box 
        senderText.setText(selectedPlayer);
        
        //Updates the private message box to show the user's recent 
        String selectData = new String("SELECT PlayerName, Private_Messages from Player");
        try {
          PreparedStatement stmt = m_dbConn.prepareStatement(selectData);
          ResultSet rs = stmt.executeQuery(selectData);
          while(rs.next()) {
            String currentEval = rs.getString("PlayerName");
            if(currentEval.equals(selectedPlayer)) {
              recentMessageText.setText(rs.getString("Private_Messages")); 
            }
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    GridBagConstraints gbc_selectSenderButton = new GridBagConstraints();
    gbc_selectSenderButton.insets = new Insets(0, 0, 5, 5);
    gbc_selectSenderButton.gridx = 2;
    gbc_selectSenderButton.gridy = 1;
    contentPane.add(selectSenderButton, gbc_selectSenderButton);
    
    JTextArea recipientText = new JTextArea();
    recipientText.setFont(new Font("Dialog", Font.BOLD, 16));
    recipientText.setEditable(false);
    recipientText.setLineWrap(true);
    GridBagConstraints gbc_recipientText = new GridBagConstraints();
    gbc_recipientText.insets = new Insets(0, 0, 5, 5);
    gbc_recipientText.fill = GridBagConstraints.BOTH;
    gbc_recipientText.gridx = 3;
    gbc_recipientText.gridy = 2;
    contentPane.add(recipientText, gbc_recipientText);
    
    GridBagConstraints gbc_listOfPlayers = new GridBagConstraints();
    gbc_listOfPlayers.gridheight = 3;
    gbc_listOfPlayers.insets = new Insets(0, 0, 5, 5);
    gbc_listOfPlayers.fill = GridBagConstraints.BOTH;
    gbc_listOfPlayers.gridx = 1;
    gbc_listOfPlayers.gridy = 1;
    contentPane.add(listOfPlayers, gbc_listOfPlayers);
    
    JTextArea recentMessageLabel = new JTextArea();
    recentMessageLabel.setEditable(false);
    recentMessageLabel.setLineWrap(true);
    recentMessageLabel.setText("Most Recent Message:");
    GridBagConstraints gbc_recentMessageLabel = new GridBagConstraints();
    gbc_recentMessageLabel.insets = new Insets(0, 0, 5, 0);
    gbc_recentMessageLabel.fill = GridBagConstraints.BOTH;
    gbc_recentMessageLabel.gridx = 5;
    gbc_recentMessageLabel.gridy = 2;
    contentPane.add(recentMessageLabel, gbc_recentMessageLabel);
    
    JTextArea messageTextSending = new JTextArea();
    messageTextSending.setLineWrap(true);
    GridBagConstraints gbc_messageTextSending = new GridBagConstraints();
    gbc_messageTextSending.insets = new Insets(0, 0, 5, 5);
    gbc_messageTextSending.fill = GridBagConstraints.BOTH;
    gbc_messageTextSending.gridx = 3;
    gbc_messageTextSending.gridy = 3;
    contentPane.add(messageTextSending, gbc_messageTextSending);
    
    JButton selectRecipientButton = new JButton("Recipient->");
    
    //Recipient textBox is updated by button click
    selectRecipientButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        recipientText.setText((String) listOfPlayers.getSelectedValue());
      }
    });
    
    GridBagConstraints gbc_selectRecipientButton = new GridBagConstraints();
    gbc_selectRecipientButton.insets = new Insets(0, 0, 5, 5);
    gbc_selectRecipientButton.gridx = 2;
    gbc_selectRecipientButton.gridy = 2;
    contentPane.add(selectRecipientButton, gbc_selectRecipientButton);
    
    JButton sendButton = new JButton("Send ->");
    
    //Send the message to the recipient
    //Form is {sender}:{message}
    sendButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        //Checks if the sender and recipient are the same
        //You can't send a private message to yourself
        if(senderText.getText().equals(recipientText.getText())) {
          return;
        }
        
        //Checks if either the sender or recipient box is empty, then updates the table
        if(senderText.getText() != null && recipientText != null) {
          String message = senderText.getText() + " --> " + messageTextSending.getText();
          String updatedData = new String("UPDATE Player SET Private_Messages = ? WHERE PlayerName = ?");
          PreparedStatement stmt;
          try {
            stmt = m_dbConn.prepareStatement(updatedData);
            stmt.setString(1, message);
            stmt.setString(2, recipientText.getText());
            stmt.executeUpdate();
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
        }
      }
    });
    
    GridBagConstraints gbc_sendButton = new GridBagConstraints();
    gbc_sendButton.insets = new Insets(0, 0, 5, 5);
    gbc_sendButton.gridx = 3;
    gbc_sendButton.gridy = 4;
    contentPane.add(sendButton, gbc_sendButton);
  }
  
  /**
   * Connects to the database
   * @return
   */
  public boolean activateJDBC() {
    try {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return true;
  }
  
}
