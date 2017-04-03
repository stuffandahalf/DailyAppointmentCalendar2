public class Person implements Comparable<Person>
{
	private String lastName;
    private String firstName;
    private String telephone;
    private String address;
    private String email;
    
    public Person(String lastName, String firstName, String telephone, String address, String email)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
    }
    
    
    
    public String toString()
    {
        return(firstName + " " + lastName + "\n" + telephone + "\n" + address + "\n" + email);
    }
    
    public int compareTo(Person other)
    {
        if(lastName.equals(other.lastName))
        {
            return(firstName.compareTo(other.firstName));
        }
        else
        {
            return(lastName.compareTo(other.lastName));
        }
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }
    
    public String getTelephone()
    {
        return telephone;
    }
    
    public void setTelephone(String newTelephone)
    {
        telephone = newTelephone;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String newAddress)
    {
        address = newAddress;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
}

