//public class MainFrame {
   
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
      public void initialize(User user){
      
       //infoPanel.setLayout(new GridLay);         

     JPanel infoPanel = new JPanel();      
        infoPanel.setLayout(new GridLayout(0,2,5,5));
      infoPanel.add(new JLabel("Name"));      
     infoPanel.add(new JLabel(user.name));      
     infoPanel.add(new JLabel("Email"));      
     infoPanel.add(new JLabel(user.email));  
 infoPanel.add(new JLabel("Phone"));      
     infoPanel.add(new JLabel(user.phone));  
 infoPanel.add(new JLabel("Address"));      
     infoPanel.add(new JLabel(user.address));  

  add(infoPanel,BorderLayout.NORTH);      
 setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100,650); 
         setLocationRelativeTo(null);
      setVisible(true);
     }
//I did not add the border like in the tutorial
   //nor use anything called flatlaf..but all should be good

   public static void main(String[] args){
   LoginForm loginForm = new LoginForm();
   loginForm.initialize();

}}///////////// 
//}
