//public class LoginForm {}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*; //not import java.sql.Connection??!!;


public class LoginForm extends JFrame{
  final private Font mainFont = new Font("Segoe print",Font.BOLD,18);
  JTextField tfEmail;
  JTextField tfID;
  JPasswordField pfPassword;
  
   public void initialize(){
    JLabel lbLoginForm = new JLabel("Login Form", SwingConstants.CENTER);
    lbLoginForm.setFont(mainFont);
   
    JLabel lbEmail = new JLabel("Email");
    lbEmail.setFont(mainFont);
    tfEmail = new JTextField();     
    tfEmail.setFont(mainFont);
    JLabel lbPassword = new JLabel("Password");
    lbPassword.setFont(mainFont);   
    pfPassword = new JPasswordField();  
    pfPassword.setFont(mainFont);
    JLabel lbID = new JLabel("ID");
    lbID .setFont(mainFont);
    tfID = new JTextField();     
    tfID.setFont(mainFont);
    JPanel formPanel = new JPanel();
     formPanel.setLayout(new GridLayout(0,1,10,10));
     formPanel.add(lbLoginForm);
     formPanel.add(lbEmail);
     formPanel.add(tfEmail);
     formPanel.add(lbID);
     formPanel.add(tfID);
     //formPanel.add(lbPassword);
     //formPanel.add(pfPassword);//} 
     /*****Button Panel****/
     JButton btnLogin = new JButton("Login");
     btnLogin.setFont(mainFont);   
     btnLogin.addActionListener(new ActionListener(){
       @Override
 	public void actionPerformed(ActionEvent e){      
           String email = tfEmail.getText();
 	 //String password = String.valueOf(pfPassword.getPassword());
      String ID = tfID.getText();
     
         User user = getAuthenticatedUser(email,ID);
         //getAuthenticatedUser(email,password);
        if(user!=null){
           MainFrame mainFrame = new MainFrame();
	   mainFrame.initialize(user);
           dispose();   }	
        else{ JOptionPane.showMessageDialog(LoginForm.this,
      "Email or Password Invalid",
       "Try Again",
        JOptionPane.ERROR_MESSAGE); }
     
}     });
JButton btnCancel = new JButton("Cancel");
btnCancel.setFont(mainFont);
btnCancel.addActionListener(new ActionListener(){
 
  @Override
  public void actionPerformed(ActionEvent e){
       dispose();
  }

});
  JPanel buttonsPanel = new JPanel();
   buttonsPanel.setLayout(new GridLayout(1,2,10,0));
  buttonsPanel.add(btnLogin);
  buttonsPanel.add(btnCancel);

     /*****Initialize the frame ******/
 add(formPanel,BorderLayout.NORTH); 
add(buttonsPanel,BorderLayout.SOUTH);  
setTitle("Login Form");
  setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  setSize(400,500);
  setMinimumSize(new Dimension(350,450));
   setLocationRelativeTo(null);      
   setVisible(true);
} 
private User getAuthenticatedUser(String email, String ID){
//careful as the db_url string should be about the same as in JavaMysql
final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
User user = null;  
final String DB_URL ="jdbc:mysql://localhost:3306/lzhou?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//final String DB_URL= "jdbc:mysql://localhost:3306/lzhou?serverTimezone=UTC"+"autoReconnect=true&useSSL=false";
//final String DB_URL= "jdbc:mysql://localhost:3306/lzhou?serverTimezone=UTC"+"autoReconnect=true&useSSL=false";//"";
final String USERNAME = "root";
final String PASSWORD = "God@2022";
System.setProperty("jdbc.drivers", JDBC_DRIVER);
try{
    //Class.forName(JDBC_DRIVER);
 // Connection conn = database(DB_URL,USERNAME,PASSWORD,JDBC_DRIVER,"jdbc:mysql://localhost:3306/dbname");
 Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
  //be sure to create a users table preferably in lzhou database
  String sql = "SELECT * FROM Users WHERE email = 'John@gmail.com' AND ID = '2'";
  PreparedStatement preparedStatement = conn.prepareStatement(sql);
 
  //preparedStatement.setString(1,email);
   //preparedStatement.setString(2,ID);   

  ResultSet resultSet = preparedStatement.executeQuery(); 
  if(resultSet.next()){ user = new User();
      user.name = resultSet.getString("name");
      user.email = resultSet.getString("email");
     user.phone = resultSet.getString("phone");
     user.address = resultSet.getString("address");
     user.password = resultSet.getString("password");
 } 
preparedStatement.close();
conn.close(); }
catch(Exception e){
   System.out.println("Database connection failed!");  
} return user;
   } public static void main(String[] args){
    LoginForm loginForm = new LoginForm();
    loginForm.initialize();
 
 } }