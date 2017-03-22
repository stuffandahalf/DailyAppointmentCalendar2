import java.util.Comparator;

public class PersonEmailComparator implements Comparator<Person>
{
    public int compare(Person a, Person b)
    {
        return(a.getEmail().compareTo(b.getEmail()));
    }
}

