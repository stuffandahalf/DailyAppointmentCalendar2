//Name: Gregory Norton
//Student ID: 500766165
//Class: CPS 209


import java.util.Comparator;

public class PersonEmailComparator implements Comparator<Person>
{
    /**
     * the implementation of the comparator interface
     * 
     * @param a the first Person object
     * @param b the second Person object
     * @return an integer representing the difference between the two objects
     */
    public int compare(Person a, Person b)
    {
        return(a.getEmail().toLowerCase().compareTo(b.getEmail().toLowerCase()));
    }
}

