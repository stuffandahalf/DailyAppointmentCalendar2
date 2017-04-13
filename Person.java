public class Person implements Comparable<Person>
{
    private String lastName;
    private String firstName;
    private String telephone;
    private String address;
    private String email;
    
    /**
     * The constructor for class Person
     * 
     * @param lastName the lastname of the Person
     * @param firstName the firstname of the Person
     * @param telephone the phone number of the Person
     * @param address the address of the Person
     * @param email the email address of the Person
     */
    public Person(String lastName, String firstName, String telephone, String address, String email)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
    }
    
    /**
     * converts the class to a string
     * 
     * @return the string represnting the object
     */
    public String toString()
    {
        return(firstName + " " + lastName + "\n" + telephone + "\n" + address + "\n" + email);
    }
    
    /**
     * the implementation of the interface Comparable
     * 
     * @param other another Person object
     * @return an integer representing the difference between the two objects
     */
    public int compareTo(Person other)
    {
        //System.out.println(lastName.toLowerCase());
        if(lastName.toLowerCase().equals(other.lastName.toLowerCase()))
        {
            return(firstName.toLowerCase().compareTo(other.firstName.toLowerCase()));
        }
        else
        {
            return(lastName.toLowerCase().compareTo(other.lastName.toLowerCase()));
        }
    }
    
    /**
     * gets the lastName field of the Person
     * 
     * @return the lastName String
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * sets the lastName field to the given String
     * 
     * @param newLastName the new lastname for the Person object
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    /**
     * gets the firstName field of the Person
     * 
     * @return the firstName String
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * sets the firstName field of the Person
     * 
     * @param newFirstName the new firstname for the Person object
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }
    
    /**
     * gets the telephone field of the Person
     * 
     * @return the telephone String
     */
    public String getTelephone()
    {
        return telephone;
    }
    
    /**
     * sets the telephone field to the given String
     * 
     * @param newTelephone the new phone number String
     */
    public void setTelephone(String newTelephone)
    {
        telephone = newTelephone;
    }
    
    /**
     * gets the address of the Person
     * 
     * @return the address String
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * sets the address field to the given String
     * 
     * @param newAddress the new address for the Person object
     */
    public void setAddress(String newAddress)
    {
        address = newAddress;
    }
    
    /**
     * gets the email field of the Person
     * 
     * @return the email String
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * sets the email field to the given String
     * 
     * @param newEmail the new email for the Person Object
     */
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
}

