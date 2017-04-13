import java.util.Comparator;

public class PersonTelephoneComparator implements Comparator<Person>
{
    /**
     * the implementation of the Comparator interface
     * 
     * @param a the first Person object
     * @param b the second Person object
     * @return an integer representing the difference between the two objects
     */
    public int compare(Person a, Person b)
    {
        return(a.getTelephone().compareTo(b.getTelephone()));
    }
}

