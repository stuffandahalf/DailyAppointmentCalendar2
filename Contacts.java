import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Contacts
{
    private LinkedList<Person> people;
    
    public Contacts()
    {
        people = new LinkedList<Person>();
    }
    
    public Person findPersonByEmail(String email)
    {
        for(Person i : people)
        {
            if(i.getEmail().equals(email))
            {
                return i;
            }
        }
        return null;
    }
    
    public Person findPersonByFirstName(String firstName)
    {
        for(Person i : people)
        {
            if(i.getFirstName().equals(firstName))
            {
                return i;
            }
        }
        return null;
    }
    
    public Person findPersonByLastName(String lastName)
    {
        for(Person i : people)
        {
            if(i.getLastName().equals(lastName))
            {
                return i;
            }
        }
        return null;
    }
    
    /*public Person findPersonByName(String firstName, String lastName)
    {
        for(Person contact : people)
        {
            if(contact.compareTo(
        }
        return null;
    }*/
    
    public Person findPersonByTelephone(String telephone)
    {
        for(Person i : people)
        {
            if(i.getTelephone().equals(telephone))
            {
                return i;
            }
        }
        return null;
    }
    
    public void readContactsFile() throws IOException, InputMismatchException
    {
        String contactFileName = "contacts.txt";
        Scanner contactFile = new Scanner(new File(contactFileName));
        int count = -1;
        while(contactFile.hasNext())
        {
            count++;
            contactFile.next();
        }
        if(count % 5 != 0)
        {
            throw new InputMismatchException("Invalid number of lines in file " + contactFileName);
        }
        contactFile = new Scanner(new File(contactFileName));
        
        int numOfContacts = contactFile.nextInt();
        for(int i = 0; i < numOfContacts; i++)
        {
            String lastName = contactFile.next();
            testInput(lastName, people);
            String firstName = contactFile.next();
            testInput(firstName, people);
            String address = contactFile.next();
            testInput(address, people);
            String telephone = contactFile.next();
            testInput(telephone, people);
            String email = contactFile.next();
            testInput(email, people);
            people.add(new Person(lastName, firstName, address, telephone, email));
        }
        contactFile.close();
        Collections.sort(people);
    }
    
    private void testInput(String input, LinkedList<Person> list) throws InputMismatchException
    {
        if(input.equals(""))
        {
            list = new LinkedList<Person>();
            throw new InputMismatchException("Parameter cannot be blank");
        }
    }
    
    public void writeContactsFile() throws IOException
    {
        PrintWriter contactFile = new PrintWriter(new File("contacts.txt"));
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

