//Name: Gregory Norton
//Student ID: 500766165
//Class: CPS 209


import javax.swing.JFrame;

/**
   This program allows the user to view font effects.
*/
public class AppointmentViewer
{  
	
   public static void main(String[] args)
   {  
      JFrame frame = new AppointmentFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Appointments");
      frame.setVisible(true);      
   }

}
