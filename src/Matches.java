import java.util.Objects;

public class Matches {
    public Person a;
    public Person b;

    /**
     * Constructor for Matches
     * @param a - person a
     * @param b - person b
     * @throws IllegalArgumentException if a and b are the same person
     */
    public Matches(Person a, Person b) throws IllegalArgumentException{
        if(a.hashCode() == b.hashCode()) {
            throw new IllegalArgumentException("Person cannot be matched with themselves");
        }
        this.a = a;
        this.b = b;
    }

    /**
     * toString override
     * @return a string of Person a and Person b formatted as "a -> b"
     */
    @Override
    public String toString() {
        return a + " -> " + b;
    }

    /**
     * Hashcode override so this object can be used in a HashSet
     * @return hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
