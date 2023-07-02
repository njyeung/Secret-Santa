import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;

public class SecretSanta {
  private static Random randGen = new Random();
  private static Set<Person> peopleSet = new HashSet<Person>();
  private static Set<Matches> matches = new HashSet<Matches>();
  
  public static void main(String args[]) {

    // TEMPORARY SOLUTION, WILL BE REPLACED WITH DATABASE
    // Load set of people from file
    try {
      peopleSet = load("People.txt");
    } catch (FileNotFoundException e) {
      System.out.println("Issue while loading people from file");
      System.exit(1);
    } catch(IllegalArgumentException e) {
      System.out.println("Caught an IllegalArgumentException from load");
      System.exit(1);
    }
    
    // Create matches
    try{
      matches = createMatches(peopleSet);
    } catch(IllegalArgumentException e) {
      System.out.println(peopleSet.size() + " people loaded");
      System.out.println("The amount of people is invalid for creating secret santas");
      System.exit(1);
    } catch(IllegalStateException e) {
      System.out.println("Caught an IllegalStateException from createMatches");
      System.exit(1);
    }

    // Writes matches to a text file
    try {
      writeMatches(matches, "Results.txt");
    } catch (IOException e) {
      System.out.println("Caught an IOException from writeMatches");
      System.exit(1);
    }

    // Run python script to send emails from text file
    try {
      Runtime.getRuntime().exec("python3 SendEmails.py");
    } catch (IOException e) {
      System.out.println("Caught an IOException from running SendEmails.py");
      System.exit(1);
    }

    // Write "sent" to people.txt
    try {
      writeSent("People.txt");
    } catch (IOException e) {
      System.out.println("Caught an IOException from writeSent");
      System.exit(1);
    }
  }


  /**
   * Returns an Set loaded with people objects read from text file -> People.txt
   * Removes duplicate entries
   * 
   * @param pathName - path name of intial text file containing information of people
   * @return a set of people
   * @throws FileNotFoundException
   */
  public static Set<Person> load(String pathName) throws FileNotFoundException, IllegalArgumentException {
    Set<Person> returnSet = new HashSet<Person>();
    File file = new File(pathName);
    Scanner scan = new Scanner(file);
    String[] currInfo = new String[2];

    while (scan.hasNext()) {
      currInfo = scan.nextLine().split(",");

      // If the line doesn't contain a name and email, it is skipped
      if(currInfo[0].equals("") && currInfo[1].equals("")) {
        continue;
      }
      // If the line only contains either the name or the email, it is invalid
      if(currInfo[0].equals("") || currInfo[1].equals("")) {
        throw new IllegalArgumentException("File is not formatted correctly");
      }

      returnSet.add(new Person(currInfo[0], currInfo[1]));
    }
    scan.close();
    
    return returnSet;
  }


  /**
   * Generates matches for secret santa
   * 
   * @param set - set of people to generate the secret santa from
   * @throws IllegalArgumentException
   */
  public static Set<Matches> createMatches(Set<Person> set) throws IllegalArgumentException {
    
    // Check if sets are valid for creating secret santas
    if (set.size() == 0) {
      throw new IllegalArgumentException("Arraylist of people are empty");
    }
    if(set.size() == 1) {
      throw new IllegalArgumentException("There is only one person in the list!");
    }

    // Convert set into arraylist so random access is possible
    LinkedList<Person> a = new LinkedList<Person>(set);
    LinkedList<Person> b = new LinkedList<Person>(set);
    Set<Matches> returnMatches = new HashSet<Matches>();

    // Create matches
    while(!a.isEmpty()) {
      // Draws a random person from arraylist b
      int randIndex = randGen.nextInt(b.size());

      // Makes sure the person is not matched with themselves
      if(a.peek().hashCode() == b.get(randIndex).hashCode()) {
        // Edge case: if there is only one person left in the list and the person is matched with themselves
        if(a.size() == 1 && b.size() == 1) {
          // Only option is to just try again
          return createMatches(set);
        }
        continue;
      }

      // Adds the match to the set
      returnMatches.add(new Matches(a.remove(), b.remove(randIndex)));
    }

    return returnMatches;
  }


  /**
   * Writes a set of matches to a text file
   * 
   * @param matches - set of matches to write out
   * @param pathName - name of the file to write to
   * @throws IOException - if an issue with FileWriter occurs
   */
  public static void writeMatches(Set<Matches> matches, String pathName) throws IOException {
    FileWriter writer = new FileWriter(pathName);
    matches.forEach((item) -> {
      try {writer.write(item + "\n");} 
      catch (IOException e) {e.printStackTrace();}
    });
    writer.close();
  }

  /**
   * Verifies that all emails have been sent
   * @param pathName - name of the file write to
   */
  public static void writeSent(String pathName) throws IOException {
    FileWriter writer = new FileWriter(pathName);
    writer.write("sent");
    writer.close();
  }
}
