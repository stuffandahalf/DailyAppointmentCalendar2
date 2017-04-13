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
     */
    public Contacts(String contactFileName) throws IOException, InputMismatchException
    {
        people = new LinkedList<Person>();
        this.contactFileName = contactFileName;
        emailComparator = new PersonEmailComparator();
        telephoneComparator = new PersonTelephoneComparator();
        readContactsFile();
        System.out.println(people);
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
        Person tmp = new Person("", "", "", "", email);
        for(Person i : people)
        {
            if(emailComparator.compare(i, tmp) == 0)
            {
                return i;
            }
        }
        return null;
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
        Person tmp = new Person(lastName, firstName, "", "", "");
        for(Person contact : people)
        {
            if(contact.compareTo(tmp) == 0)
	    {
                return contact;
            }
        }
        return null;
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
        Person tmp = new Person("", "", telephone, "", "");
        for(Person i : people)
        {
            if(telephoneComparator.compare(i, tmp) == 0)
            {
                return i;
            }
        }
        return null;
    }
    
    /**
     * reads the contacts from the file given in
     * the constructor of the class
     */
    public void readContactsFile() throws IOException, InputMismatchException
    {
        Scanner contactFile = new Scanner(new File(contactFileName));
        int count = -2;
        while(contactFile.hasNext())
        {
            contactFile.next();
            count++;
        }
        if(count % 5 != 0)
        {
            throw new InputMismatchException("Invalid number of lines in file " + contactFileName);
        }
        contactFile = new Scanner(new File(contactFileName));
        
        int numOfContacts = contactFile.nextInt();
        contactFile.nextLine();
        for(int i = 0; i < numOfContacts; i++)
        {
            String lastName = contactFile.nextLine();
            testInput(lastName, people);
            String firstName = contactFile.nextLine();
            testInput(firstName, people);
            String address = contactFile.nextLine();
            testInput(address, people);
            String telephone = contactFile.nextLine();
            testInput(telephone, people);
            String email = contactFile.nextLine();
            testInput(email, people);
            people.add(new Person(lastName, firstName, address, telephone, email));
        }
        contactFile.close();
        Collections.sort(people);
    }
    
    /**
     * tests the field to check if it is an empty string and
     * clears the list and throws an InputMismatchException if it is
     * 
     * @throws InputMismatchException throw an exception when the field is blank
     */
    private void testInput(String input, LinkedList<Person> list) throws InputMismatchException
    {
        if(input.equals(""))
        {
            list = new LinkedList<Person>();
            throw new InputMismatchException("Parameter cannot be blank");
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
        PrintWriter contactFile = new PrintWriter(new File(contactFileName));
        contactFile.println(people.size());
        for(Person p : people)
        {
            contactFile.println(p.getLastName());
            contactFile.println(p.getFirstName());
            contactFile.println(p.getAddress());
            contactFile.println(p.getTelephone());
            contactFile.println(p.getEmail());
        }
        contactFile.close();
    }
}

