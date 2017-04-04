//Name: Gregory Norton
//Student ID: 500766165
//Class: CPS 209


//importing java libraries
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.Arrays;
import java.util.Locale;
import java.time.DayOfWeek;
import java.time.format.TextStyle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class AppointmentFrame extends JFrame
{
    //instance variables for window size
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    
    //variables used for the appointments
    private Calendar date;
    private SimpleDateFormat sdf;
    private SimpleDateFormat sdfMonth;
    private ArrayList<Appointment> appointments;
    private Stack<Appointment> appointmentsStack;
    
    //gui instance variables
    private JPanel centralPanel;
    
    private JPanel leftPanel;
    private JPanel rightPanel;
    
    
    //leftpanel instance variables
    private JLabel dateLabel;
    private JTextArea textArea;
    
    private JScrollPane scrollPane;
    
    private JPanel controlPanel;
    
    private TitledBorder dateBorder;
    private JPanel datePanel;
    
    private JPanel subDatePanelA;
    private JButton leftButton;
    private JButton rightButton;
    
    private JPanel subDatePanelB;
    private JLabel monthInputLabel;
    private JTextField monthInput;
    private JLabel dayInputLabel;
    private JTextField dayInput;
    private JLabel yearInputLabel;
    private JTextField yearInput;
    
    private JPanel subDatePanelC;
    private JButton showButton;
    
    private TitledBorder appointmentBorder;
    private JPanel appointmentPanel;
    
    private JPanel subAppointmentPanelA;
    private JLabel hourLabel;
    private JLabel minuteLabel;
    private JTextField hourInput;
    private JTextField minuteInput;
    
    private JPanel subAppointmentPanelB;
    private JButton createButton;
    private JButton cancelButton;
    private JButton recallButton;
    
    private JMenuBar menuBar;
    private JMenu manageMenu;
    private JMenuItem clearAppointments;
    private JMenuItem addSampleAppointments;
    
    //rightPanel instance variables
    private JLabel monthLabel;
    
    private JPanel calendarPanel;
    private JPanel calendarSubPanelA;
    private JPanel calendarSubPanelB;
    
    private JPanel contactDescriptionPanel;
    
    private JPanel contactPanel;
    private JPanel contactSubPanelA;
    private TitledBorder contactBorder;
    private JLabel lastNameLabel;
    private JTextField lastNameInput;
    private JLabel firstNameLabel;
    private JTextField firstNameInput;
    private JLabel telephoneLabel;
    private JTextField telephoneInput;
    private JLabel emailLabel;
    private JTextField emailInput;
    private JPanel contactSubPanelB;
    private JLabel addressLabel;
    private JTextField addressInput;
    private JPanel contactSubPanelC;
    private JButton findButton;
    private JButton clearButton;
    
    private JPanel descriptionPanel;
    private TitledBorder descriptionBorder;
    private JTextArea description;
    
    /**
     * constructor for the frame
     */
    public AppointmentFrame()
    {
        super();                                                                        //call superclass constructor
        date = new GregorianCalendar();                                                 //initialize variable date as a pointer to a new GregorianCalendar object
        sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");                                //initialize variable sdf as a pointer to a new SimpleDateFormat
        sdfMonth = new SimpleDateFormat("MMM");
        appointments = new ArrayList<Appointment>();                                    //initialize appointments as a pointer to a new ArrayList for storing Appointment objects
        appointmentsStack = new Stack<Appointment>();                                   //initialize appointmentsStack as a pointer to a new stack to store Appointment objects as they are added
        
        centralPanel = new JPanel(new GridLayout(1, 2));
        add(centralPanel);
        
        leftPanel = new JPanel(new BorderLayout());
        centralPanel.add(leftPanel);
        rightPanel = new JPanel(new BorderLayout());
        centralPanel.add(rightPanel);
        
        createLeftPanel();
        createRightPanel();
        
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);                                           //sets the size of the JFrame to the constants WINDOW_WIDTH and WINDOW_HEIGHT
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));                     //sets the minimum size of the JFrame to the same constants
    }
    
    /**
     * create the left panel
     */
    private void createLeftPanel()
    {
        createDateLabel();                                                              //run createLabel method
        createTextArea();                                                               //run createTextArea method
        createControlPanel();                                                           //run createControlPanel method
        createMenuBar();                                                                //run createMenuBar method
    }
    
    /**
     * create the right panel
     */
    private void createRightPanel()
    {
        createMonthLabel();
        createCalendar();
        createContactDescriptionPanel();
    }
    
    /**
     * method to create JLabel for frame
     */
    private void createDateLabel()
    {
        dateLabel = new JLabel(sdf.format(date.getTime()));                             //initializes the dateLabel variable to point to a new JLabel object which displays the current date
        leftPanel.add(dateLabel, BorderLayout.NORTH);                                             //add the label object to the north part of the JFrame
    }
    
    /**
     * method to create main text area
     */
    private void createTextArea()
    {
        textArea = new JTextArea();                                                     //initialize the textArea as a pointer to a new JTextArea object
        textArea.setEditable(false);                                                    //disable editing the text in the JTextArea
        scrollPane = new JScrollPane(textArea);                                         //create a new JScrollPane fromm the JTextArea to make it scrollable
        leftPanel.add(scrollPane, BorderLayout.CENTER);                                           //add the scrollPane to the center of the JFrame
    }
    
    
    /**
     * method to create control panel of the frame
     */
    private void createControlPanel()
    {
        controlPanel = new JPanel(new BorderLayout());                                  //create a new panel for the control panel
        createDateSubpanel();                                                           //call the createDateSubpanel method
        createAppointmentSubpanel();                                                         //call the createActionSubpanel method
        
        leftPanel.add(controlPanel, BorderLayout.SOUTH);                                          //add the controlPanel to the south of the JFrame
    }
    
    
    /**
     * create the subpanel to adjust the date
     */
    private void createDateSubpanel()
    {
        dateBorder = new TitledBorder("Date");                                          //create a new TitledBorder for the datePanel with title Date
        dateBorder.setTitleJustification(TitledBorder.LEFT);                            //set the justification of the title to the left
        datePanel = new JPanel(new BorderLayout());                                     //create a new subpanel for the date controls
        datePanel.setBorder(dateBorder);                                                //set the border of the panel to the titleborder
        
        subDatePanelA = new JPanel(new GridLayout(1, 2));                               //create a subpanel with the a 1x2 gridlayout to contain the left and right buttons
        datePanel.add(subDatePanelA, BorderLayout.NORTH);                               //add the subpanel to the main datePanel
        createLeftButton();                                                             //call the createLeftButton method
        createRightButton();                                                            //call the createRightButton method
        
        subDatePanelB = new JPanel();                                                   //create another subpanel with the flow layout to store the inputs and labels
        
        dayInputLabel = new JLabel("Day");                                                   //initialize the dayLabel as a pointer to a new JLabel object with label Day
        subDatePanelB.add(dayInputLabel);                                                    //add the label to the subpanel
        dayInput = new JTextField(Integer.toString(date.get(Calendar.DAY_OF_MONTH)), 2);//initialize the dayInput as a pointer to a new JTextField with default text of the current day and a size of 2 columns
        subDatePanelB.add(dayInput);                                                    //add the dayinput to the subpanel
        
        monthInputLabel = new JLabel("Month");                                               //initialize the monthInputLabel as a pointer to a new JLabel object with label Month
        subDatePanelB.add(monthInputLabel);                                                  //add the label to the subpanel
        monthInput = new JTextField(Integer.toString(date.get(Calendar.MONTH)+1), 2);   //initialize the monthInput as a pointer to a new JTextField with default text of the current month and a size of 2 columns
        subDatePanelB.add(monthInput);                                                  //add the monthInput to the subpanel
        
        yearInputLabel = new JLabel("Year");                                                 //initialize the yearLabel as a pointer to a new JLabel object with label Year
        subDatePanelB.add(yearInputLabel);                                                   //add the label to the subpanel
        yearInput = new JTextField(Integer.toString(date.get(Calendar.YEAR)), 4);       //initialize the yearInput as a pointer to a new JTextField with default text of the current year and a size of 4 columns
        subDatePanelB.add(yearInput);                                                   //add the yearInput to the subpanel
        datePanel.add(subDatePanelB, BorderLayout.CENTER);                              //add the subpanel to the datePanel
        
        subDatePanelC = new JPanel();                                                   //create another subpanel for the show button
        createShowButton();                                                             //call the createShowButton method
        datePanel.add(subDatePanelC, BorderLayout.SOUTH);                               //add the subpanel to the datePanel
        
        controlPanel.add(datePanel, BorderLayout.NORTH);                                //add the datePanel to the north of the controlPanel
    }
    
    /**
     * create the left button
     */
    private void createLeftButton()
    {
        class LeftButtonListener implements ActionListener                              //create a nested class to implement the ActionListener interface
        {
            public void actionPerformed(ActionEvent evt)                                //override the actionPerformed method
            {
                date.add(Calendar.DAY_OF_MONTH, -1);                                    //add -1 days to the current date
                monthLabel.setText(sdfMonth.format(date.getTime()));
                dateLabel.setText(sdf.format(date.getTime()));                          //set the content of the dateLabel to be the new date
                getTodaysAppointments();                                                //run the getTodaysAppointments method
            }
        }
        
        leftButton = new JButton("<");                                                  //initialize the leftButton to point to a new JButton object with label <
        leftButton.addActionListener(new LeftButtonListener());                         //add the new listener class to the button
        
        subDatePanelA.add(leftButton);                                                  //add the leftButton to the first date subpanel
    }
    
    /**
     * create the right button
     */
    private void createRightButton()
    {
        class RightButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)                                //create a nested class to implement the ActionListener interface
            {
                date.add(Calendar.DAY_OF_MONTH, 1);                                     //add 1 day to the current date
                monthLabel.setText(sdfMonth.format(date.getTime()));
                dateLabel.setText(sdf.format(date.getTime()));                          //set the content of the dateLabel to be the new date
                getTodaysAppointments();                                                //run the getTodaysAppointments method
            }
        }
        
        rightButton = new JButton(">");                                                 //initialize the rightButton to point to a new JButton object with label >
        rightButton.addActionListener(new RightButtonListener());                       //add the new listener class to the button
        
        subDatePanelA.add(rightButton);                                                 //add the rightButton to the first date subpanel
    }
    
    /**
     * create the show button
     */
    private void createShowButton()
    {
        showButton = new JButton("Show");                                               //set the showButton to point to a new JButton with label Show
        class ShowButtonListener implements ActionListener                              //subclass that implements the ActionListener interface
        {
            public void actionPerformed(ActionEvent evt)                                //override the actionPerformed method
            {                
                String year = yearInput.getText();                                      //get input from JTextFields
                String month = monthInput.getText();
                String day = dayInput.getText();
                if(!year.equals("") &&                                                  //check if the date is a valid value
                    !month.equals("") && Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13 &&
                    !day.equals("") && Integer.parseInt(day) > 0 && Integer.parseInt(day) < date.getActualMaximum(Calendar.DAY_OF_MONTH))
                {
                    date.set(Calendar.YEAR, Integer.parseInt(yearInput.getText()));     //set the date to the one that was input
                    date.set(Calendar.MONTH, Integer.parseInt(monthInput.getText())-1);
                    date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayInput.getText()));
                }
                else
                {
                    System.out.println("Invalid date entered");                         //print a message
                }
                yearInput.setText(Integer.toString(date.get(Calendar.YEAR)));           //set the text in the textFields for the year
                monthInput.setText(Integer.toString(date.get(Calendar.MONTH)+1));       //month, and day to represent the current date
                dayInput.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
                monthLabel.setText(sdfMonth.format(date.getTime()));
                dateLabel.setText(sdf.format(date.getTime()));                          //set the text of the dateLabel to the current date
                getTodaysAppointments();                                                //run getTodaysAppointments to show the appointments for that date
            }
        }
        showButton.addActionListener(new ShowButtonListener());                         //add the actionListener to the showButton
        subDatePanelC.add(showButton, BorderLayout.CENTER);                             //add the showButton the the third date subpanel
    }
    
    /**
     * method to get and print the
     * appointments for the day to
     * the JTextArea
     */
    private void getTodaysAppointments()
    {
        textArea.setText("");                                                           //clear the textArea of all old appointments
        Collections.sort(appointments);                                                 //sort the appointments ArrayList
        for(int i = 0; i < appointments.size(); i++)                                    //for every index in the ArrayList
        {
            Calendar apptDate = appointments.get(i).getDate();                          //get the date of the appointment
               
            if(apptDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&                //if the current date is equal to the date of the appointment
               apptDate.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
               apptDate.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
            {
                textArea.append(appointments.get(i).print());                           //add the appointment to the textArea
                textArea.append("\n\n");                                                //add two newlines to the textArea
            }
        }
    }
 
    /**
     * create the subpanel for the actions
     */
    private void createAppointmentSubpanel()
    {
        appointmentBorder = new TitledBorder("Appointment");                            //create a new TitledBorder with title Action
        appointmentBorder.setTitleJustification(TitledBorder.LEFT);                     //set the justification for the title to left
        appointmentPanel = new JPanel(new BorderLayout());                              //create a new JPanel
        appointmentPanel.setBorder(appointmentBorder);                                  //set the border of the new JPanel to the new border
        
        subAppointmentPanelA = new JPanel();                                            //create a subpanel
        hourLabel = new JLabel("Hour");                                                 //set the hourLabel to point to a new JLabel object with label Hour
        subAppointmentPanelA.add(hourLabel);                                            //add the hourLabel to the subpanel
        hourInput = new JTextField(2);                                                  //create a new JTextField object with 2 columns
        subAppointmentPanelA.add(hourInput);                                            //add the hourInput to the subpanel
        minuteLabel = new JLabel("Minute");                                             //repeat this for minutes
        subAppointmentPanelA.add(minuteLabel);
        minuteInput = new JTextField(2);
        subAppointmentPanelA.add(minuteInput);
        appointmentPanel.add(subAppointmentPanelA, BorderLayout.NORTH);                 //add the subpanel to the north of the main actionPanel
        
        subAppointmentPanelB = new JPanel();                                            //make another subpanel
        createCreateButton();                                                           //run the createCreateButton method
        createCancelButton();                                                           //run the createCancelButton method
        createRecallButton();
        appointmentPanel.add(subAppointmentPanelB, BorderLayout.CENTER);                //add the subpanel to the main actionPanel
        
        controlPanel.add(appointmentPanel, BorderLayout.CENTER);                        //add the actionPanel to the controlPanel
    }
    
    /**
     * create the create button
     */
    private void createCreateButton()
    {
        createButton = new JButton("Create");                                           //create a new JButton with label Create
        class CreateButtonListener implements ActionListener                            //make a subclass that implements the ActionListener interface
        {
            public void actionPerformed(ActionEvent evt)                                //override the actionPerformed method
            {
                boolean create = true;                                                  //boolean to store wheather the appointment should be made or not
                int year = date.get(Calendar.YEAR);                                     //easy access to the year,
                int month = date.get(Calendar.MONTH)+1;                                 //month,
                int day = date.get(Calendar.DAY_OF_MONTH);                              //and day
                
                String hour = hourInput.getText();                                      //get the hour from the hourInput JTextField
                String minute = minuteInput.getText();                                  //get the minute from the minuteInput JTextField
                if(hour.equals(""))                                                     //if the given hours are blank
                {
                    description.setText("ERROR");                                       //set the description box to say ERROR
                }
                else                                                                    //otherwise
                {
                    if(minute.equals(""))                                               //if the given minutes are blank
                    {
                        minute = "0";                                                   //set minutes to 0
                    }
                    
                    for(int i = 0; i < appointments.size(); i++)                        //for every index in appointments
                    {
                        if(appointments.get(i).occursOn(year, month, day, Integer.parseInt(hour), Integer.parseInt(minute)))    //check if the appointment occurs at the given time
                        {
                            create = false;                                             //set the create variable to false
                            description.setText("CONFLICT");                            //set the text of the description box to say CONFLICT
                            break;                                                      //break the loop
                        }
                    }
                    
                    if(create)                                                          //if create was not set to false
                    {
                        Appointment newAppointment = new Appointment(year, month, day, Integer.parseInt(hour), Integer.parseInt(minute), description.getText());    //make a new appointment at the given time
                        description.setText("");                                        //clear the description text box
                        appointments.add(newAppointment);                               //add the new appointment to the ArrayList
                        appointmentsStack.push(newAppointment);                         //add the new appointment to the Stack
                    }
                    getTodaysAppointments();                                            //run the getTodaysAppointments method to print them to the screen
                }
            }
        }
        createButton.addActionListener(new CreateButtonListener());                     //add the new ActionListener to the JButton
        subAppointmentPanelB.add(createButton);                                         //add the createbutton to the second action subpanel
    }

    /**
     * create the cancel button
     */
    private void createCancelButton()
    {
        cancelButton = new JButton("Cancel");                                           //create a new JButton with label Cancel
        class CancelButtonListener implements ActionListener                            //create a subclass that implements the ActionListener interface
        {
            public void actionPerformed(ActionEvent evt)                                //override the actionPerformed method
            {
                int year = date.get(Calendar.YEAR);                                     //store the current year,
                int month = date.get(Calendar.MONTH)+1;                                 //month,
                int day = date.get(Calendar.DAY_OF_MONTH);                              //and day
                String hour = hourInput.getText();                                      //get the hour from the hourInput textField
                String minute = minuteInput.getText();                                  //get the minute from the minnuteInput textField
                if(hour.equals(""))                                                     //if there was no hour specified
                {
                    description.setText("ERROR");                                       //set the description box to ERROR
                }
                else                                                                    //otherwise
                {
                    if(minute.equals(""))                                               //if there was no minute specified
                    {
                        minute = "0";                                                   //set the minutes to 0
                    }
                    for(int i = 0; i < appointments.size(); i++)                        //for every index in the ArrayList
                    {
                        if(appointments.get(i).occursOn(year, month, day, Integer.parseInt(hour), Integer.parseInt(minute)))    //if the appointment occurs on the given time
                        {
                            appointments.remove(i);                                     //remove the appointment from the arrayList
                            break;                                                      //break the loop
                        }
                    }
                    for(int i = 0; i < appointmentsStack.size(); i++)
                    {
                        if(appointmentsStack.get(i).occursOn(year, month, day, Integer.parseInt(hour), Integer.parseInt(minute)))
                        {
                            appointmentsStack.remove(i);
                            break;
                        }
                    }
                    getTodaysAppointments();                                            //run the getTodaysAppointments method to display the remaining appointments
                }
            }
        }
        cancelButton.addActionListener(new CancelButtonListener());                     //add the ActionListener to the cancelButton
        subAppointmentPanelB.add(cancelButton);                                              //add the cancelButton to the second action subpanel
    }
    
    /**
     * create the recall button
     */
    private void createRecallButton()
    {
        recallButton = new JButton("Recall");
        class RecallButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(!appointmentsStack.empty())
                {
                    //SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
                    //System.out.println("Recall button was pressed, replace later");
                    Appointment lastAppointment = appointmentsStack.peek();
                    Calendar appointmentDate = lastAppointment.getDate();
                    int day = appointmentDate.get(Calendar.DAY_OF_MONTH);
                    int month = appointmentDate.get(Calendar.MONTH) + 1;
                    int year = appointmentDate.get(Calendar.YEAR);
                    int hour = appointmentDate.get(Calendar.HOUR_OF_DAY);
                    int minutes = appointmentDate.get(Calendar.MINUTE);
                    //System.out.println(minutes);
                    dayInput.setText(Integer.toString(day));
                    monthInput.setText(Integer.toString(month));
                    yearInput.setText(Integer.toString(year));
                    hourInput.setText(Integer.toString(hour));
                    minuteInput.setText(Integer.toString(minutes));
                    date = appointmentDate;
                    monthLabel.setText(sdfMonth.format(date.getTime()));
                    dateLabel.setText(sdf.format(date.getTime()));                          //set the text of the dateLabel to the current date
                    getTodaysAppointments();                                                //run getTodaysAppointments to show the appointments for that date
            
                }
                else
                {
                    dayInput.setText("");
                    monthInput.setText("");
                    yearInput.setText("");
                    hourInput.setText("");
                    minuteInput.setText("");
                }
            }
        }
        recallButton.addActionListener(new RecallButtonListener());
        subAppointmentPanelB.add(recallButton);
    }
    
    /**
     * creates the menubar
     */
    private void createMenuBar()
    {
        menuBar = new JMenuBar();                                       //initialize the menuBar
        manageMenu = new JMenu("Manage");                               //make a new menu with label Manage
        menuBar.add(manageMenu);                                        //add the Manage menu to the menubar
        
        createClearAppointments();                                      //create the clear appointments item
        createAddSamples();                                             //create the add samples item
        
        setJMenuBar(menuBar);                                           //set the menu bar of the frame
    }
    
    /**
     * create the clear appointments menu option
     */
    private void createClearAppointments()
    {
        clearAppointments = new JMenuItem("Clear Appointments");                //initialize clearAppointments as a new MenuItem
        class ClearAppointmentsListener implements ActionListener               //subclass that implements the ActionListener interface
        {
            public void actionPerformed(ActionEvent e)                          //override the actionPerformed method
            {
                while(appointments.size() != 0)                                 //while there are still values in the appointment array
                {
                    appointments.remove(0);                                     //remove the first element
                }
                getTodaysAppointments();                                        //refresh the textArea
            }
        }
        clearAppointments.addActionListener(new ClearAppointmentsListener());   //add the ActionListener to the clearAppointments menuItem
        manageMenu.add(clearAppointments);                                      //add the menuItem to the manageMenu
    }
    
    /**
     * create the add samples menu option
     */
    private void createAddSamples()
    {
        addSampleAppointments = new JMenuItem("Add Sample Appointments");                   //initialize the addSampleAppointments item
        class AddSampleListener implements ActionListener                                   //new subclass that implements the ActionListener interface
        {
            private Appointment[] samples = {                                               //array of sample Appointment objects
                new Appointment(2017, 12, 12, 12, 12, "It will be 12:12 on 12/12"),
                new Appointment(2011, 11, 11, 11, 11, "It was 11:11 on 11/11/11"),
                new Appointment(1983, 4, 13, 3, 23, "This was a random date"),
                new Appointment(2117, 3, 20, 11, 59, "100 years after this assignment is due"),
                new Appointment(2006, 7, 3, 1, 12, "It is late"),
                new Appointment(2017, 12, 12, 12, 13, "This also happens")
            };
            
            public void actionPerformed(ActionEvent e)
            {
                String message = "Appointments were added to the following time\n";
                for(int j = 0; j < samples.length; j++)                                     //for every sample appointment
                {
                    boolean create = true;                                                  //boolean to store wheather the appointment should be made or not
                    int year = samples[j].getDate().get(Calendar.YEAR);                     //easy access to the year,
                    int month = samples[j].getDate().get(Calendar.MONTH);                   //month,
                    int day = samples[j].getDate().get(Calendar.DAY_OF_MONTH);              //day,
                    int hour = samples[j].getDate().get(Calendar.HOUR);                     //hour,
                    int minute = samples[j].getDate().get(Calendar.MINUTE);                 //and minute
                    SimpleDateFormat sampleFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    
                    for(int i = 0; i < appointments.size(); i++)                            //for every index in appointments
                    {
                        if(appointments.get(i).occursOn(year, month, day, hour, minute))    //check if the appointment occurs at the given time
                        {
                            create = false;                                                 //set the create variable to false
                            description.setText("CONFLICT");                                //set the text of the description box to say CONFLICT
                            break;                                                          //break the loop
                        }
                    }
                    
                    if(create)                                                              //if create was not set to false
                    {
                        appointments.add(samples[j]);                                       //add the new appointment to the ArrayList
                        appointmentsStack.push(samples[j]);                                 //add the new appointment to the Stack
                        message += sampleFormat.format(samples[j].getDate().getTime());     //add the sample date to the message
                        message += "\n";                                                    //add a newline character to the message
                    }
                }
                System.out.println(message);                                                //print the message to console to be able to retain the dates
                JOptionPane.showMessageDialog(null, message);                               //show a message dialog with the sample dates added
                getTodaysAppointments();                                                    //run the getTodaysAppointments method to print them to the screen

            }
        }
        addSampleAppointments.addActionListener(new AddSampleListener());                   //add the ActionListener to the menu item
        manageMenu.add(addSampleAppointments);                                              //add the menu item to the manage menu
    }

    /**
     * create the label with the current month
     */
    private void createMonthLabel()
    {
        monthLabel = new JLabel(sdfMonth.format(date.getTime()));
        rightPanel.add(monthLabel, BorderLayout.NORTH);
    }
    
    /**
     * create the calendar buttons
     */
    private void createCalendar()
    {
        calendarPanel = new JPanel(new BorderLayout());
        
        calendarSubPanelA = new JPanel(new GridLayout(1, 7));
        for(int i = 0; i < 7; i++)
        {
            if(i != 0)
            {
                calendarSubPanelA.add(new JLabel(DayOfWeek.values()[i-1].getDisplayName(TextStyle.SHORT, Locale.CANADA)));
            }
            else
            {
                calendarSubPanelA.add(new JLabel(DayOfWeek.values()[6].getDisplayName(TextStyle.SHORT, Locale.CANADA)));
            }
        }
        calendarPanel.add(calendarSubPanelA, BorderLayout.NORTH);
        
        calendarSubPanelB = new JPanel(new GridLayout(5, 7));
        int currentYear = date.get(Calendar.YEAR);
        int currentMonth = date.get(Calendar.MONTH);
        int previousMonth = currentMonth - 1;
        int nextMonth = currentMonth + 1;
        int currentDay = date.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        //Calendar currentMonthCalendar = new GregorianCalendar(
        //for(int i = 1; i <= numberOfDays; i++)
        for(int i = 1; i <= 35; i++)
        {
            JButton calendarButton = new JButton(Integer.toString(i));
            if(currentDay == Integer.parseInt(calendarButton.getText()))
            {
                calendarButton.setBackground(Color.RED);
            }
            class CalendarButtonListener implements ActionListener
            {
                private int index;
                public CalendarButtonListener(int i)
                {
                    this.index = i;
                }
                
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Button " + index + " was pressed, replace later");
                }
            }
            calendarButton.addActionListener(new CalendarButtonListener(i));
            calendarSubPanelB.add(calendarButton);
        }
        calendarPanel.add(calendarSubPanelB, BorderLayout.CENTER);
        rightPanel.add(calendarPanel);
    }
    
    /**
     * create the panel that holds the contacts and the description
     */
    private void createContactDescriptionPanel()
    {
        contactDescriptionPanel = new JPanel(new BorderLayout());
        createContactPanel();
        createDescriptionPanel();
        rightPanel.add(contactDescriptionPanel, BorderLayout.SOUTH);
    }
    
    /**
     * create the contact panel
     */
    private void createContactPanel()
    {
        contactBorder = new TitledBorder("Contact");
        contactBorder.setTitleJustification(TitledBorder.LEFT);
        contactPanel = new JPanel(new BorderLayout());
        contactPanel.setBorder(contactBorder);
        
        contactSubPanelA = new JPanel(new GridLayout(4, 2));
        lastNameLabel = new JLabel("Last Name");
        firstNameLabel = new JLabel("First Name");
        contactSubPanelA.add(lastNameLabel);
        contactSubPanelA.add(firstNameLabel);
        lastNameInput = new JTextField();
        firstNameInput = new JTextField();
        contactSubPanelA.add(lastNameInput);
        contactSubPanelA.add(firstNameInput);
        telephoneLabel = new JLabel("Telephone");
        emailLabel = new JLabel("email");
        contactSubPanelA.add(telephoneLabel);
        contactSubPanelA.add(emailLabel);
        telephoneInput = new JTextField();
        emailInput = new JTextField();
        contactSubPanelA.add(telephoneInput);
        contactSubPanelA.add(emailInput);
        
        contactSubPanelB = new JPanel(new BorderLayout());
        addressLabel = new JLabel("Address");
        contactSubPanelB.add(addressLabel, BorderLayout.NORTH);
        addressInput = new JTextField();
        contactSubPanelB.add(addressInput);
        
        contactSubPanelC = new JPanel();
        createFindButton();
        createClearButton();
        
        contactPanel.add(contactSubPanelA, BorderLayout.NORTH);
        contactPanel.add(contactSubPanelB, BorderLayout.CENTER);
        contactPanel.add(contactSubPanelC, BorderLayout.SOUTH);
        
        contactDescriptionPanel.add(contactPanel, BorderLayout.NORTH);
    }
    
    /**
     * create the find button
     */
    private void createFindButton()
    {
        findButton = new JButton("Find");
        class FindButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Find button was pressed, replace later");
            }
        }
        findButton.addActionListener(new FindButtonListener());
        contactSubPanelC.add(findButton);
    }
    
    /**
     * create the clear button
     */
    private void createClearButton()
    {
        clearButton = new JButton("Clear");
        class ClearButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Clear button was pressed, replace later");
            }
        }
        clearButton.addActionListener(new ClearButtonListener());
        contactSubPanelC.add(clearButton);
    }
    
    /**
     * creates the description subpanel
     */
    private void createDescriptionPanel()
    {
        descriptionBorder = new TitledBorder("Description");                            //create a new TitledBorder with title Description
        descriptionBorder.setTitleJustification(TitledBorder.LEFT);                     //set the justification for the title to left
        descriptionPanel = new JPanel(new BorderLayout());                              //create a new JPanel with the BorderLayout
        descriptionPanel.setBorder(descriptionBorder);                                  //set the border for the panel to the TitledBorder
        
        description = new JTextArea(6, 10);                                             //create a new JTextArea with 4 rows and 10 columns
        descriptionPanel.add(description, BorderLayout.CENTER);                         //add the description JTextArea to the center of the descriptionPanel
        contactDescriptionPanel.add(descriptionPanel, BorderLayout.SOUTH);                         //add the descriptionPanel to the south of the controlPanel
    }
    
}
