import java.util.Objects;

public class Person {
  private String name;
  private String email;
  
  /**
   * Constructor for People
   * @param name - name of the person
   * @param email - email of the person
   * @throws IllegalArgumentException if email is invalid
   * @return People object
   */
  public Person(String name, String email) throws IllegalArgumentException{
    this.name = name;
    // Regex to make sure the email is valid
    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
      throw new IllegalArgumentException("Invalid email address");
    }
    this.email = email;
  }

  /**
   * Getter for name
   * @return name of the person
   */
  public String getName() {
    return name;
  }
  /**
   * Getter for email
   * @return email of the person
   */
  public String getEmail() {
    return email;
  }
  /**
   * toString override
   * @return name and email of the person formatted as "name (email)"
   */
  @Override
  public String toString() {
    return name + " (" + email + ")";
  }

  /**
   * Hashcode override so this object can be used in a HashSet
   * @return hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, email);
  }
}
