import java.util.Comparator;

public class PersonTelephoneComparator implements Comparator<Person>
{
    public int compare(Person a, Person b)
    {
        return(a.getTelephone().compareTo(b.getTelephone()));
    }
}

