//Name: Gregory Norton
//Student ID: 500766165
//Class: CPS 209


import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Contacts
{
    //instance variables
    private LinkedList<Person> people;
    private String contactFileName;
    private PersonEmailComparator emailComparator;
    private PersonTelephoneComparator telephoneComparator;
    
    /**
     * Constructor for Contacts
     * 
     * @param contactFileName the file from which to read the contacts
     * @throws IOException may be thrown when reading the contacts file
     * @throws InputMismatchException may be thrown when reading the contacts file
     */
    public Contacts(String contactFileName) throws IOException, InputMismatchException
    {
        people = new LinkedList<Person>();                              //initialize people as a new LinkedList of Person objects
        this.contactFileName = contactFileName;                         //set the contactFileNameVariable to the given parameter
        emailComparator = new PersonEmailComparator();                  //create a new PersonEmailComparator object
        telephoneComparator = new PersonTelephoneComparator();          //create a new PersonTelephoneComparator object
        readContactsFile();                                             //read the contacts file to load contacts into the LinkedList
    }
    
    /**
     * finds a Person object with the same email
     * string as provided
     * 
     * @param email the email to compare
     * @return a pointer to the found Person object or null
     */
    public Person findPersonByEmail(String email)
    {
        Person tmp = new Person("", "", "", "", email);                 //create a new temporary Person with only an email
        for(Person i : people)                                          //go through the LinkedList
        {
            if(emailComparator.compare(i, tmp) == 0)                    //if the email of tmp and the current Person object match
            {
                return i;                                               //return the found Person
            }
        }
        return null;                                                    //if nothing is found, return null
    }
    
    /**
     * finds a Person object with the given first
     * and last name strings
     * 
     * @param lastName the last name belonging to the Person object
     * @param firstName the first name belonging to the Person object
     * @return a pointer to the found Person object or null
     */
    public Person findPersonByName(String lastName, String firstName)
    {
        Person tmp = new Person(lastName, firstName, "", "", "");       //create a new temporary Person object with only lastname and firstname parameters
        for(Person contact : people)                                    //for every Person in the LinkedList
        {
            if(contact.compareTo(tmp) == 0)                             //if the compareTo method returns 0
	    {
                return contact;                                         //return the found contact
            }
        }
        return null;                                                    //if nothing is found, return null
    }
    
    /**
     * finds a Person object with a matching
     * telephone string to the one provided
     * 
     * @param telephone the phone number of the Person to be found
     * @return a pointer to the found Person or null
     */
    public Person findPersonByTelephone(String telephone)
    {
        Person tmp = new Person("", "", "", telephone, "");             //create a temporary Person with only telephone parameter
        for(Person i : people)                                          //for every Person in the LinkedList
        {
            if(telephoneComparator.compare(i, tmp) == 0)                //if the telephone numbers are the same
            {
                return i;                                               //return the found Person
            }
        }
        return null;                                                    //otherwise return null
    }
    
    /**
     * reads the contacts from the file given in
     * the constructor of the class
     * 
     * @throws IOException Scanner may throw an IOException
     * @throws InputMismatchException if the contact file does not have enough lines
     */
    public void readContactsFile() throws IOException, InputMismatchException, NumberFormatException
    {
        Scanner contactFile = new Scanner(new File(contactFileName));                               //create a new Scanner from the 
        int count = -2;                                                                             //start the counter at a negative offset to ignore the first line
        while(contactFile.hasNext())                                                                //while there is another line in the file
        {
            contactFile.next();                                                                     //continue going through it
            count++;                                                                                //incrememnt the counter
        }
        if(count % 5 != 0)                                                                          //if the counter is not an integer multiple of 5
        {
            throw new InputMismatchException("Invalid number of lines in file " + contactFileName); //throw an InputMismatchException
        }
        contactFile = new Scanner(new File(contactFileName));                                       //reset the Scanner
        int numOfContacts = Integer.parseInt(contactFile.nextLine());
        for(int i = 0; i < numOfContacts; i++)                                                      //for every contact stored in the file
        {
            String lastName = contactFile.nextLine();                                               //read in the lastname
            testInput(lastName, people);                                                            //test the String
            String firstName = contactFile.nextLine();                                              //read in the firstname
            testInput(firstName, people);                                                           //test the String
            String address = contactFile.nextLine();                                                //read in the address
            testInput(address, people);                                                             //test the String
            String telephone = contactFile.nextLine();                                              //read in the telephone number
            testInput(telephone, people);                                                           //test the String
            String email = contactFile.nextLine();                                                  //read in the email address
            testInput(email, people);                                                               //test the String
            people.add(new Person(lastName, firstName, address, telephone, email));                 //add a new Person made from the parameters read in to the LinkedList
        }
        contactFile.close();                                                                        //close the Scanner
        Collections.sort(people);                                                                   //sort the LinkedList
        
        System.out.println("The following contacts were loaded");                                   //print a header to standard out 
        for(Person p : people)                                                                      //for every person in the LinkedList
        {
            System.out.println(p);                                                                  //print the Person's details
            System.out.println();                                                                   //print a newline
        }
    }
    
    /**
     * tests the field to check if it is an empty string and
     * clears the list and throws an InputMismatchException if it is
     * 
     * @throws InputMismatchException throw an exception when the field is blank
     */
    private void testInput(String input, LinkedList<Person> list) throws InputMismatchException
    {
        if(input.equals(""))                                                //if the given string is blank
        {
            list = new LinkedList<Person>();                                //clear the LinkedList
            throw new InputMismatchException("Parameter cannot be blank");  //throw a new InputMismatchException
        }
    }
    
    /**
     * writes the fields of each person object in the list to
     * the contactsFile specified in the constructor
     * 
     * @throws IOException PrintWriter and File throw IOExceptions
     */
    public void writeContactsFile() throws IOException
    {
        PrintWriter contactFile = new PrintWriter(new File(contactFileName));   //create a new PrintWriter from the contactFile
        contactFile.println(people.size());                                     //write the number of Person objects are in the LinkedList
        for(Person p : people)                                                  //for every Person object
        {
            contactFile.println(p.getLastName());                               //write the objects lastname
            contactFile.println(p.getFirstName());                              //firstname
            contactFile.println(p.getAddress());                                //address
            contactFile.println(p.getTelephone());                              //telephone
            contactFile.println(p.getEmail());                                  //and email
        }
        contactFile.close();                                                    //close the PrintWriter
    }
}

