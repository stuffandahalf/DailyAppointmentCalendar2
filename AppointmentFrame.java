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
import java.util.InputMismatchException;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.lang.Math;
import java.io.IOException;

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
import javax.swing.border.LineBorder;



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
    private Contacts contacts;
    
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
        sdfMonth = new SimpleDateFormat("MMM");                                         //initialize varabke sdfMonth as a pointer to a new SimpleDateFormat to format the date
        appointments = new ArrayList<Appointment>();                                    //initialize appointments as a pointer to a new ArrayList for storing Appointment objects
        appointmentsStack = new Stack<Appointment>();                                   //initialize appointmentsStack as a pointer to a new stack to store Appointment objects as they are added
        centralPanel = new JPanel(new GridLayout(1, 2));                                //create a central panel to split the fram into left and right sections
        add(centralPanel);                                                              //add the panel to the frame
        
        leftPanel = new JPanel(new BorderLayout());                                     //initialize the leftPanel as a new JPanel with a BorderLayout
        centralPanel.add(leftPanel);                                                    //add the leftPanel to the centralPanel
        rightPanel = new JPanel(new BorderLayout());                                    //repeat for the rightPanel
        centralPanel.add(rightPanel);
        
        createLeftPanel();                                                              //run the method to construct and add the contents of the leftPanel
        createRightPanel();                                                             //run the method to construct and add the contents of the rightPanel
        
        try                                                                             //try
        {
            contacts = new Contacts("contacts.txt");                                    //to initialize contacts as a new Contacts object with input file of contacts.txt
        }
        catch(IOException e)                                                            //catch IOException
        {
            description.setText(e.getMessage());                                        //write it to the description box
        }
        catch(InputMismatchException e)                                                 //catch InputMismatchException
        {
            description.setText(e.getMessage());                                        //write it to the description box
        }
        
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
        createMonthLabel();                                             //run the createMonthLabel method
        createCalendar();                                               //run the createCalendar method
        createContactDescriptionPanel();                                //run the createContactDescriptionPanel
    }
    
    /**
     * method to create JLabel for frame
     */
    private void createDateLabel()
    {
        dateLabel = new JLabel(sdf.format(date.getTime()));             //initializes the dateLabel variable to point to a new JLabel object which displays the current date
        leftPanel.add(dateLabel, BorderLayout.NORTH);                   //add the label object to the north part of the JFrame
    }
    
    /**
     * method to create main text area
     */
    private void createTextArea()
    {
        textArea = new JTextArea();                                     //initialize the textArea as a pointer to a new JTextArea object
        textArea.setEditable(false);                                    //disable editing the text in the JTextArea
        scrollPane = new JScrollPane(textArea);                         //create a new JScrollPane fromm the JTextArea to make it scrollable
        leftPanel.add(scrollPane, BorderLayout.CENTER);                 //add the scrollPane to the center of the JFrame
    }
    
    
    /**
     * method to create control panel of the frame
     */
    private void createControlPanel()
    {
        controlPanel = new JPanel(new BorderLayout());                  //create a new panel for the control panel
        createDateSubpanel();                                           //call the createDateSubpanel method
        createAppointmentSubpanel();                                    //call the createActionSubpanel method
        
        leftPanel.add(controlPanel, BorderLayout.SOUTH);                //add the controlPanel to the south of the JFrame
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
        
        dayInputLabel = new JLabel("Day");                                              //initialize the dayLabel as a pointer to a new JLabel object with label Day
        subDatePanelB.add(dayInputLabel);                                               //add the label to the subpanel
        dayInput = new JTextField(Integer.toString(date.get(Calendar.DAY_OF_MONTH)), 2);//initialize the dayInput as a pointer to a new JTextField with default text of the current day and a size of 2 columns
        subDatePanelB.add(dayInput);                                                    //add the dayinput to the subpanel
        
        monthInputLabel = new JLabel("Month");                                          //initialize the monthInputLabel as a pointer to a new JLabel object with label Month
        subDatePanelB.add(monthInputLabel);                                             //add the label to the subpanel
        monthInput = new JTextField(Integer.toString(date.get(Calendar.MONTH)+1), 2);   //initialize the monthInput as a pointer to a new JTextField with default text of the current month and a size of 2 columns
        subDatePanelB.add(monthInput);                                                  //add the monthInput to the subpanel
        
        yearInputLabel = new JLabel("Year");                                            //initialize the yearLabel as a pointer to a new JLabel object with label Year
        subDatePanelB.add(yearInputLabel);                                              //add the label to the subpanel
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
                monthLabel.setText(sdfMonth.format(date.getTime()));                    //update the monthLabel
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
                monthLabel.setText(sdfMonth.format(date.getTime()));                    //update the monthLabel
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
                else                                                                    //if it is not
                {
                    description.setText("ERROR: INVALID DATE ENTERED");                 //set the description box to the following message
                }
                yearInput.setText(Integer.toString(date.get(Calendar.YEAR)));           //set the text in the textFields for the year
                monthInput.setText(Integer.toString(date.get(Calendar.MONTH)+1));       //month, and day to represent the current date
                dayInput.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
                monthLabel.setText(sdfMonth.format(date.getTime()));                    //update the monthLabel
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
        calendarPanel.removeAll();                                                      //remove everything from the calendarPanel
        createCalendar();                                                               //rerun the createCalendar method
        calendarPanel.revalidate();                                                     //revalidate the calendarPanel
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
        createRecallButton();                                                           //run the createRecallButton method
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
                if(hour.equals("") || Integer.parseInt(hour) < 0 || Integer.parseInt(hour) > 23)	//if the given hours are blank or invalid
                {
                    description.setText("ERROR");                                       //set the description box to say ERROR
                }
                else                                                                    //otherwise
                {
                    if(minute.equals("") && !(Integer.parseInt(minute) < 0 || Integer.parseInt(minute) > 59))       //if the given minutes are blank
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
                        Appointment newAppointment;                                     //create an Appointment variable
                        if(lastNameInput.getText().equals("") ||                        //if there is not enough information
                           firstNameInput.getText().equals("") ||                       //for a person object
                           addressInput.getText().equals(""))
                        {
                            newAppointment = new Appointment(year,                      //create a new Appointment without a Person
                                                             month,                     //at the given time
                                                             day,
                                                             Integer.parseInt(hour),
                                                             Integer.parseInt(minute),
                                                             description.getText());
                        }
                        else                                                            //otherwise
                        {
                            newAppointment = new Appointment(year,                      //create a new Appointment with a Person object
                                                             month,
                                                             day,
                                                             Integer.parseInt(hour),
                                                             Integer.parseInt(minute),
                                                             description.getText(),
                                                             lastNameInput.getText(),
                                                             firstNameInput.getText(),
                                                             addressInput.getText(),
                                                             telephoneInput.getText(),
                                                             emailInput.getText());
                        }
                        description.setText("");                                        //clear the description text box
                        appointments.add(newAppointment);                               //add the new appointment to the ArrayList
                        appointmentsStack.push(newAppointment);                         //add the new appointment to the Stack
                    }
                    hourInput.setText("");                                              //clear the hourInput
                    minuteInput.setText("");                                            //anf the minuteInput
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
                    for(int i = 0; i < appointmentsStack.size(); i++)                   //repeat the same for the AppointmentStack
                    {
                        if(appointmentsStack.get(i).occursOn(year, month, day, Integer.parseInt(hour), Integer.parseInt(minute)))
                        {
                            appointmentsStack.remove(i);
                            break;
                        }
                    }
                    hourInput.setText("");                                              //clear the HourInput
                    minuteInput.setText("");                                            //and the minuteInput
                    getTodaysAppointments();                                            //run the getTodaysAppointments method to display the remaining appointments
                }
            }
        }
        cancelButton.addActionListener(new CancelButtonListener());                     //add the ActionListener to the cancelButton
        subAppointmentPanelB.add(cancelButton);                                         //add the cancelButton to the second action subpanel
    }
    
    /**
     * create the recall button
     */
    private void createRecallButton()
    {
        recallButton = new JButton("Recall");
        class RecallButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent evt)                                    //override the actionPerformed method from interface ActionListener
            {
                if(!appointmentsStack.empty())                                              //if the appointmentsStack is not empty
                {
                    Appointment lastAppointment = appointmentsStack.peek();                 //retrieve the last added appointment
                    Calendar appointmentDate = lastAppointment.getDate();                   //store the date of the appointment in a Calendar variable
                    int day = appointmentDate.get(Calendar.DAY_OF_MONTH);                   //store the day,
                    int month = appointmentDate.get(Calendar.MONTH) + 1;                    //month
                    int year = appointmentDate.get(Calendar.YEAR);                          //year
                    int hour = appointmentDate.get(Calendar.HOUR_OF_DAY);                   //hour
                    int minutes = appointmentDate.get(Calendar.MINUTE);                     //and minutes in their own variables
                    dayInput.setText(Integer.toString(day));                                //set the dayInput text to the day
                    monthInput.setText(Integer.toString(month));                            //monthInput to month
                    yearInput.setText(Integer.toString(year));                              //yearInput to year
                    hourInput.setText(Integer.toString(hour));                              //hourInput to hour
                    minuteInput.setText(Integer.toString(minutes));                         //minuteInput to minutes
                    date = appointmentDate;                                                 //set the date to the date of the last appointment added
                    monthLabel.setText(sdfMonth.format(date.getTime()));                    //set the mothLabel to the new month
                    dateLabel.setText(sdf.format(date.getTime()));                          //set the text of the dateLabel to the current date
                    getTodaysAppointments();                                                //run getTodaysAppointments to show the appointments for that date
            
                }
                else                                                                        //if there are no appointments in the stack
                {
                    description.setText("ERROR: NO APPOINTMENTS TO RECALL");                //set the description text to inform the user
                }
            }
        }
        recallButton.addActionListener(new RecallButtonListener());                         //add the actionListener to the recallButton
        subAppointmentPanelB.add(recallButton);                                             //add the recallButton to the appointmentPanel
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
                new Appointment(2017, 04, 13, 11, 30, "This assignment is due"),
                new Appointment(2017, 04, 22, 9, 0, "CPS 310 Exam"),
                new Appointment(2017, 04, 24, 8, 0, "CPS 209 Exam"),
                new Appointment(2017, 04, 25, 8, 0, "MTH 207 Exam"),
                new Appointment(2017, 04, 25, 12, 0, "FNU 101 Exam"),
                new Appointment(2017, 04, 27, 9, 0, "CPS 393 Exam")
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
        monthLabel = new JLabel(sdfMonth.format(date.getTime()));       //initialize the monthLabel as a new JLabel with the text set to the current month
        rightPanel.add(monthLabel, BorderLayout.NORTH);                 //add the monthLabel to the north slot of the rightPanel
    }
    
    /**
     * create the calendar buttons
     */
    private void createCalendar()
    {
        calendarPanel = new JPanel(new BorderLayout());                 //initialize the calendarPanel as a new JPanel with a BorderLayout
        
        calendarSubPanelA = new JPanel(new GridLayout(1, 7));           //initialize the first subPanel as a new JPanel with a 1x7 GridLayout
        for(int i = 0; i < 7; i++)                                      //for every slot in the subPanel
        {
            if(i != 0)                                                  //if i is not 0
            {
                calendarSubPanelA.add(new JLabel(DayOfWeek.values()[i-1].getDisplayName(TextStyle.SHORT, Locale.CANADA)));  //add a new JLabel to the current slot in the JPanel with the day of the week
            }
            else                                                        //if i is 0
            {
                calendarSubPanelA.add(new JLabel(DayOfWeek.values()[6].getDisplayName(TextStyle.SHORT, Locale.CANADA)));    //add a new JLabel to the 0th position with the label for sunday
            }
        }
        calendarPanel.add(calendarSubPanelA, BorderLayout.NORTH);       //add the subpanel to the north part of the calendarPanel
        
        int counter = 0;                                                //count the number of days added to the calendar
        
        calendarSubPanelB = new JPanel(new GridLayout(6, 7));           //initialize the second subPanel as a new JPanel with a 6x7 GridLayout
        int currentYear = date.get(Calendar.YEAR);                      //store the currentYear
        int currentMonth = date.get(Calendar.MONTH);                    //currentMonth
        int previousMonth = currentMonth - 1;                           //previousMonth
        int nextMonth = currentMonth + 1;                               //nextMonth
        int currentDay = date.get(Calendar.DAY_OF_MONTH);               //currentDay
        Calendar currentMonthCalendar = new GregorianCalendar(currentYear, currentMonth, 1);    //create a new Calendar for the current month starting at 1
        int remainingDays = currentMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1;                 //get the remaining days in the current week
        int numberOfDays = currentMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);        //get the total number of days in the month
        Calendar previousMonthCalendar = new GregorianCalendar(currentYear, previousMonth, 1);  //create a new Calendar for the previous month starting at 1
        previousMonthCalendar.set(Calendar.DAY_OF_MONTH, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));    //set the date to the last day of the month
        Calendar nextMonthCalendar = new GregorianCalendar(currentYear, nextMonth, 1);      //create another new Calendar for the next month
        currentMonthCalendar.set(Calendar.DAY_OF_MONTH, currentMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));      //set the currentMonthCalendara to the last day of the month
        int leftoverDays = 7 - currentMonthCalendar.get(Calendar.DAY_OF_WEEK);              //store the remaining days in that week
        while(remainingDays > 0)                                                            //while there are days remaining in the previous month
        {
            int i = previousMonthCalendar.get(Calendar.DAY_OF_MONTH) - remainingDays + 1;   //the day of the previous month
            JButton calendarButton = new JButton(Integer.toString(i));                      //create a new JButton
            calendarButton.setBackground(Color.GRAY);                                       //set the colour of the JButton to grey
            for(int j = 0; j < appointments.size(); j++)                                    //for every appointment
            {
                if(appointments.get(j).getDate().get(Calendar.MONTH) == previousMonth &&    //if the appointment occurs on the day of the button
                    appointments.get(j).getDate().get(Calendar.DAY_OF_MONTH) == i)
                {
                    calendarButton.setBorder(new LineBorder(Color.ORANGE));                 //add an orange border to the button
                    break;                                                                  //break the loop
                }
            }
            class CalendarButtonListener implements ActionListener
            {
                public void actionPerformed(ActionEvent e)                                  //override the actionPerformed method from the interface ActionListener
                {
                    date.set(Calendar.MONTH, date.get(Calendar.MONTH)-1);                   //set the date to the one represented by the button
                    date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(calendarButton.getText()));
                    monthLabel.setText(sdfMonth.format(date.getTime()));                    //set the monthLabel to the new date
                    dateLabel.setText(sdf.format(date.getTime()));                          //set the dateLabel to the new date
                    getTodaysAppointments();                                                //check for appointments on the day and redraw the calendar
                }
            }
            calendarButton.addActionListener(new CalendarButtonListener());                 //add the actionListener to the button
            calendarSubPanelB.add(calendarButton);                                          //add the button to the calendarSubPanelb
            remainingDays--;                                                                //decrement remainingDays
            counter++;                                                                      //increment the counter
        }
        for(int i = 1; i <= numberOfDays; i++)                                              //for every day in the current month
        {
            JButton calendarButton = new JButton(Integer.toString(i));                      //create a new JButton with a label containing the current day of the month
            if(currentDay == Integer.parseInt(calendarButton.getText()))                    //if the text in the button matches the current day of the month
            {
                calendarButton.setBackground(Color.RED);                                    //set the colour of the button to red
            }
            for(int j = 0; j < appointments.size(); j++)                                    //for every appointment
            {
                if(appointments.get(j).getDate().get(Calendar.MONTH) == currentMonth &&     //if there is an appointment on the day the button represents
                    appointments.get(j).getDate().get(Calendar.DAY_OF_MONTH) == i)
                {
                    calendarButton.setBorder(new LineBorder(Color.ORANGE));                 //add an orange outline to the button
                    break;                                                                  //break the loop
                }
            }
            class CalendarButtonListener implements ActionListener
            {
                public void actionPerformed(ActionEvent e)                                  //override the actionPerformed method from the ActionListener interface
                {
                    date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(calendarButton.getText()));    //set the date to the one represented by the button
                    //monthLabel.setText(sdfMonth.format(date.getTime()));                            //set the monthLabel to the new date
                    dateLabel.setText(sdf.format(date.getTime()));                                  //set the dateLabel to the new date
                    getTodaysAppointments();                                                        //get the new dates appointments and redraw the calendar
                }
            }
            calendarButton.addActionListener(new CalendarButtonListener());                 //add the actionListener to the button
            calendarSubPanelB.add(calendarButton);                                          //add the JButton to the calendarSubPanel
            counter++;                                                                      //incrememnt the counter
        }
        while(leftoverDays > 0)                                                             //while there are leftover days
        {
            int i = nextMonthCalendar.get(Calendar.DAY_OF_MONTH);                           //store the first day of the next month in i
            JButton calendarButton = new JButton(Integer.toString(i));                      //create a new JButton with the label representing the day of the month
            nextMonthCalendar.set(Calendar.DAY_OF_MONTH, nextMonthCalendar.get(Calendar.DAY_OF_MONTH) + 1);     //set the calendar to the next day
            calendarButton.setBackground(Color.GRAY);                                       //set the colour of the button to grey
            for(int j = 0; j < appointments.size(); j++)                                    //for every appointment
            {
                if(appointments.get(j).getDate().get(Calendar.MONTH) == nextMonth &&        //if the date of the appointment matches the date represented by the button
                    appointments.get(j).getDate().get(Calendar.DAY_OF_MONTH) == i)
                {
                    calendarButton.setBorder(new LineBorder(Color.ORANGE));                 //add an orange border to the button
                    break;                                                                  //break from the loop
                }
            }
            class CalendarActionListener implements ActionListener
            {
                public void actionPerformed(ActionEvent e)                                  //override the actionPerformed method from the ActionListener interface
                {
                    date.set(Calendar.MONTH, date.get(Calendar.MONTH)+1);                   //set the date to the date represented by the button
                    date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(calendarButton.getText()));
                    monthLabel.setText(sdfMonth.format(date.getTime()));                    //set the monthLabel to the new date
                    dateLabel.setText(sdf.format(date.getTime()));                          //set the dateLabel to the new date
                    getTodaysAppointments();                                                //get today's appointments and redraw the calendar
                }
            }
            calendarButton.addActionListener(new CalendarActionListener());                 //add the actionListener to the button
            calendarSubPanelB.add(calendarButton);                                          //add the JButton to the subPanel
            leftoverDays--;                                                                 //decrement leftoverDays
            counter++;                                                                      //increment counter
        }
        if(counter % 6 != 0)                                                                //if counter is not an integer multiple of 6
        {
            for(int j = 0; j < 7; j++)                                                      //add another weeks worth of buttons
            {
                int i = nextMonthCalendar.get(Calendar.DAY_OF_MONTH);                       //store the current day of the next month in i
                JButton calendarButton = new JButton(Integer.toString(i));                  //create a new JButton with label corresponding to the day of the month
                nextMonthCalendar.set(Calendar.DAY_OF_MONTH, nextMonthCalendar.get(Calendar.DAY_OF_MONTH) + 1);     //set the nextMonthCalendar to the next day
                calendarButton.setBackground(Color.GRAY);                                   //set the colour of the button to grey
                for(int k = 0; k < appointments.size(); k++)                                //for every appointment
                {
                    if(appointments.get(k).getDate().get(Calendar.MONTH) == nextMonth &&    //if the dates are the same
                        appointments.get(k).getDate().get(Calendar.DAY_OF_MONTH) == i)
                    {
                        calendarButton.setBorder(new LineBorder(Color.ORANGE));             //add an orange border to the button
                        break;                                                              //break from the loop
                    }
                }
                class CalendarActionListener implements ActionListener
                {
                    public void actionPerformed(ActionEvent e)                              //override actionPerformed method from the ActionListener interface
                    {
                        date.set(Calendar.MONTH, date.get(Calendar.MONTH)+1);               //set the date to the one represented by the button
                        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(calendarButton.getText()));
                        monthLabel.setText(sdfMonth.format(date.getTime()));                //set the monthLabel to the new date
                        dateLabel.setText(sdf.format(date.getTime()));                      //set the dateLabel to the new date
                        getTodaysAppointments();                                            //get the day's appointments and refresh the calendar
                    }
                }
                calendarButton.addActionListener(new CalendarActionListener());             //add the ActionListener to the JButton
                calendarSubPanelB.add(calendarButton);                                      //add the button to the subPanel
                leftoverDays--;                                                             //decrement leftoverDays
            }
        }
        
        calendarPanel.add(calendarSubPanelB, BorderLayout.CENTER);                          //add the calendar to the center of the calendarPanel
        rightPanel.add(calendarPanel);                                                      //add the calendarPanel to the rightPanel
    }
    
    /**
     * create the panel that holds the contacts and the description
     */
    private void createContactDescriptionPanel()
    {
        contactDescriptionPanel = new JPanel(new BorderLayout());       //initialize contactDescriptionPanel to a new JPanel with a BorderLayout
        createContactPanel();                                           //run the creatContactPanel method
        createDescriptionPanel();                                       //run the createDescriptionPanel method
        rightPanel.add(contactDescriptionPanel, BorderLayout.SOUTH);    //add the contactDescriptionPanel to the south part of the rightPanel 
    }
    
    /**
     * create the contact panel
     */
    private void createContactPanel()
    {
        contactBorder = new TitledBorder("Contact");                    //initialize contactBorder to a new TitledBorder with text "Contact"
        contactBorder.setTitleJustification(TitledBorder.LEFT);         //set the justification of the border to left
        contactPanel = new JPanel(new BorderLayout());                  //initialize contactPanel to a new JPanel with a BorderLayout
        contactPanel.setBorder(contactBorder);                          //set the border of the contatPanel to the contactBorder
        
        contactSubPanelA = new JPanel(new GridLayout(4, 2));            //initialize contactSubPanelA to a new JPanel with a 4x2 GridLayout
        lastNameLabel = new JLabel("Last Name");                        //initialize lastNameLabel to a new JLabel with text "Last Name"
        firstNameLabel = new JLabel("First Name");                      //initialize firstNameLabel to a new JLabel with text "FirstName"
        contactSubPanelA.add(lastNameLabel);                            //add the lastNameLabel to contactSubPanelA
        contactSubPanelA.add(firstNameLabel);                           //add the firstNameLabel to contactSubPanelA
        lastNameInput = new JTextField();                               //initalize lastNameInput to a new JTextField
        firstNameInput = new JTextField();                              //initialize firstNameInput to a new JTextField
        contactSubPanelA.add(lastNameInput);                            //add the lastNameInput to contactSubPanelA
        contactSubPanelA.add(firstNameInput);                           //add the firstNameInput to contactSubPanelA
        telephoneLabel = new JLabel("Telephone");                       //initialize telephoneLabel to a new JLabel with text "Telephone"
        emailLabel = new JLabel("email");                               //initialize emailLabel to a new JLabel with text "email"
        contactSubPanelA.add(telephoneLabel);                           //add the telephoneLabel to the contactSubPanelA
        contactSubPanelA.add(emailLabel);                               //add the emailLabel to the contactSubPanelA
        telephoneInput = new JTextField();                              //initialize telephoneInput to a new JTextField
        emailInput = new JTextField();                                  //initialize emailInput to a new JTextField
        contactSubPanelA.add(telephoneInput);                           //add the telephoneInput to contactSubPanelA
        contactSubPanelA.add(emailInput);                               //add the emailInput to contactSubPanelB
        
        contactSubPanelB = new JPanel(new BorderLayout());              //initialize contactSubPanelB to a new JPanel with a BorderLayout
        addressLabel = new JLabel("Address");                           //initialize addressLabel to a new JLabel with label "Address"
        contactSubPanelB.add(addressLabel, BorderLayout.NORTH);         //add the addressLabel to the north part of contactSubPanelB
        addressInput = new JTextField();                                //initialize the addressInput to a new JTextField
        contactSubPanelB.add(addressInput);                             //add the addressInput to contactSubPanelB
        
        contactSubPanelC = new JPanel();                                //initialize contactSubPanelC as a new JPanel
        createFindButton();                                             //run the createFindButton method
        createClearButton();                                            //run the createClearButton method
        
        contactPanel.add(contactSubPanelA, BorderLayout.NORTH);         //add contactSubPanelA to the north part of the contactPanel
        contactPanel.add(contactSubPanelB, BorderLayout.CENTER);        //add contactSubPanelB to the center part of the contactPanel
        contactPanel.add(contactSubPanelC, BorderLayout.SOUTH);         //add contactSubPanelC to the south part of the contactPanel
        
        contactDescriptionPanel.add(contactPanel, BorderLayout.NORTH);  //add the contactPanel to the north part of the contactDescriptionPanel
    }
    
    /**
     * create the find button
     */
    private void createFindButton()
    {
        findButton = new JButton("Find");                               //initialize findButton to a new JButton with label "Find"
        class FindButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)                  //override the actionPerformed method from the ActionListener interface
            {
                try                                                     //try
                {
                    if(!(lastNameInput.getText().equals("") && firstNameInput.getText().equals("")))    //if neither the lastName input field nor the firstName input field are empty
                    {
                        Person p = contacts.findPersonByName(lastNameInput.getText(), firstNameInput.getText());    //find a Person from the contacts object that matches
                        setFields(p);                                   //set the fields to those from the found Person object
                    }
                    else if(!emailInput.getText().equals(""))           //if the email input field is not blank
                    {
                        Person p = contacts.findPersonByEmail(emailInput.getText());        //search for a Person with a matching email
                        setFields(p);                                   //set the fields to those from the found Person object
                    }
                    else if(!telephoneInput.getText().equals(""))       //if the phone number input field is not blank
                    {
                        Person p = contacts.findPersonByTelephone(telephoneInput.getText());    //search for a Person with a matching phone number
                        setFields(p);                                   //set the fields to those from the found Person object
                    }
                    else                                                //if none of the above conditions are met
                    {
                        description.setText("ERROR: NO FIELDS WERE FILLED");    //set the description to an error
                    }
                }
                catch(NullPointerException err)                         //if one of the find methods return a null pointer
                {
                    description.setText("ERROR: CONTACT NOT FOUND");    //set the description to say that the contact was not found
                }
            }
            /**
             * sets the fields in the contact panel to those
             * from the given Person object
             * 
             * @param p the Person object containing the data for the fields
             */
            private void setFields(Person p)
            {
                firstNameInput.setText(p.getFirstName());
                lastNameInput.setText(p.getLastName());
                addressInput.setText(p.getAddress());
                telephoneInput.setText(p.getTelephone());
                emailInput.setText(p.getEmail());
            }
        }
        findButton.addActionListener(new FindButtonListener());         //add the ActionListener to the findButton
        contactSubPanelC.add(findButton);                               //add the findButton to contactSubPanelC
    }
    
    /**
     * create the clear button
     */
    private void createClearButton()
    {
        clearButton = new JButton("Clear");                             //initialize clearButton as a new JButton with label "Clear"
        class ClearButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)                  //override the actionPerformed method from the ActionListener interface
            {
                lastNameInput.setText("");                              //clear all of the fields
                firstNameInput.setText("");                             //including the description
                telephoneInput.setText("");                             //box
                emailInput.setText("");
                addressInput.setText("");
                description.setText("");
            }
        }
        clearButton.addActionListener(new ClearButtonListener());       //add the ActionListener to the clearButton
        contactSubPanelC.add(clearButton);                              //add clearButton to contactSubPanelC
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
        contactDescriptionPanel.add(descriptionPanel, BorderLayout.SOUTH);              //add the descriptionPanel to the south of the controlPanel
    }
    
}
