//Name: Gregory Norton
//Student ID: 500766165
//Class: CPS 209

//import java calendar utilities
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appointment implements Comparable<Appointment>                                     //class Appointment implements the Comparable interface
{
	private Calendar date;                                                                      //instance variables date
    private String description;                                                                 //and description
    private Person person;
    
    public Appointment(int year, int month, int day, int hour, int minute, String description)  //constructor needs 5 integers and one string
    {
        date = new GregorianCalendar(year, month-1, day, hour, minute);                         //initialize the date variable to point to a new GregorianCalendar object with the date provided
        this.description = description;                                                         //set the description to point to the same String as the one provided
    }
    
    public Appointment(int year, int month, int day, int hour, int minute, String description,              //second constructor takes same parameters as
                       String lastName, String firstName, String address, String telephone, String email)   //above and also parameters for a Person object
    {
        this(year, month, day, hour, minute, description);                                                  //calls the other constructor method
        person = new Person(lastName, firstName, address, telephone, email);                                //initializes the Person object
    }
    
    /**
     * gets the Person object
     * 
     * @return the object's Person object
     */
    public Person getPerson()
    {
        return person;                                                  //return the Person object
    }
    
    /**
     * sets the person variable to point
     * to a new Person object with the given parameters
     * 
     * @param lastName the lastname for the new Person
     * @param firstName the firstname for the new Person
     * @param address the address for the new Person
     * @param telephone the phone number for the new Person
     * @param email the email address for the new Person
     */
    public void setPerson(String lastName, String firstName, String address, String telephone, String email)
    {
        person = new Person(lastName, firstName, address, telephone, email);        //set the person instance variable to point to a new Person with given parameters
    }
    
    /**
     * method to return the
     * description of the
     * appointment
     * 
     * @return the description
     */
    public String getDescription()
    {
        return description;                                                         //return the description
    }
    
    /**
     * method to set the
     * description of the
     * appointment
     * 
     * @param newDescription the new description for the appointment
     */
    public void setDescription(String newDescription)
    {
        description = newDescription;                                               //set the description to the new description
    }
    
    /**
     * method to return
     * the date of the
     * appointment
     * 
     * @return the date of the appointment
     */
    public Calendar getDate()
    {
        return date;                                                                //return the date
    }
    
    /**
     * method to set the date
     * of the appointment
     * 
     * @param year the year to set the appointment to
     * @param month the month to set the appointment to
     * @param day the day to set the appointment to
     * @param hour the hour to set the appointment to
     * @param minute the minute to set the appointment to
     */
    public void setDate(int year, int month, int day, int hour, int minute)
    {
        date = new GregorianCalendar(year, month, day, hour, minute);               //set the date to a new GregorianCalendar with date set to the given variables
    }
    
    /**
     * method to return
     * the time and
     * description of the
     * appointment
     * 
     * @return a string containing the time and description of the appointment
     */
    public String print()
    {
        String hour = Integer.toString(date.get(Calendar.HOUR_OF_DAY));             //variable to store the hour
        String minute = Integer.toString(date.get(Calendar.MINUTE));                //variable to store the minute
        String message = "";                                                        //a blank string for the message
        if(hour.length() == 1)                                                      //if the hour is smaller than 10
        {
            message += "0";                                                         //append a zero to the message
        }
        message += hour + ":";                                                      //append the hour and a colon to the message
        if(minute.length() == 1)                                                    //if the minutes are below 10
        {
            message += "0";                                                         //append a zero to the message
        }
        message += minute + " " + description;                                      //append the minutes and the description to the message
        if(person == null)                                                          //if the Person pbject was not initialized
        {
            return message;                                                         //return the message
        }
        return message + " WITH:\n" + person;                                       //otherwise return the message with the details of the Person
    }
    
    /**
     * method to check if the
     * appointment occurs on
     * the same date
     * 
     * @param year the year to be compared
     * @param month the month to be compared
     * @param day the day to be compared
     * @param hour the hour to be compared
     * @param minute the minute to be compared
     * @return a boolean true if the dates are the same and false otherwise
     */
    public boolean occursOn(int year, int month, int day, int hour, int minute)
    {
        Calendar otherDate = new GregorianCalendar(year, month-1, day, hour, minute);     //make another GregorianCalendar object
        return date.compareTo(otherDate) == 0;                                            //return if the two dates are the same
    }
    
    /**
     * override the method
     * compareTo from interface
     * Comparable
     * 
     * @param other the object to be compare to
     * @return an integer to show the position relative to the other object
     */
    public int compareTo(Appointment other)
    {
        return date.compareTo(other.getDate());        //compare the dates of the two appointments and return the result
        
    }
}

