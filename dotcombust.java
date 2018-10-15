import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class dotcombust{

  private Gamehelper helper = new Gamehelper();
  private ArrayList<dotcom> dotcomslist = new ArrayList<dotcom>();
  private int numofguesses = 0;

  private void setupgame(){
    dotcom one = new dotcom();
    one.setname("Pets.com");
    dotcom two = new dotcom();
    two.setname("eToys.com");
    dotcom three = new dotcom();
    three.setname("Go2.com");
    dotcomslist.add(one);
    dotcomslist.add(two);
    dotcomslist.add(three);

    System.out.println("Your goal is to sink all three dot coms.");
    System.out.println("Pets.com, eToys.com, Go2.com");
    System.out.println("Try to sink them all in the fewest number of guesses");

    for (dotcom dotcomtoset : dotcomslist){
      ArrayList<String> newlocation = helper.placedotcom(3);
      dotcomtoset.setlocationcells(newlocation);
    }
  }

  private void startplaying(){
    while(!dotcomslist.isEmpty()){
      String userguess = helper.getuserinput("Enter a guess:");
      checkuserguess(userguess);
    }
    finishgame();
  }

  private void checkuserguess(String userguess){
    numofguesses++;
    String result = "miss";
    for (dotcom dotcomtotest : dotcomslist){
      result = dotcomtotest.checkyourself(userguess);
      if (result.equals("hit")){
        break;
      }
      if (result.equals("kill")){
        dotcomslist.remove(dotcomtotest);
        break;
      }
    }
    System.out.println(result);
  }

  private void finishgame(){
    System.out.println("All dot coms are dead! Your stock is now worthless.");
    if (numofguesses<=18){
      System.out.println("It only took you "+numofguesses+" guesses.");
      System.out.println("You got out before your option sank.");
    }else{
      System.out.println("Took you long enough. "+numofguesses+" guessee");
      System.out.println("Fish are dancing with your guesses");
    }
  }

  public static void main(String[] args){
    dotcombust game = new dotcombust();
    game.setupgame();
    game.startplaying();
  }
}
class dotcom{
  private ArrayList<String> locationcells;
  private String name;
  public void setlocationcells(ArrayList<String> loc){
    locationcells = loc;
  }
  public void setname(String n){
    name = n;
  }
  public String checkyourself(String userinput){
    //int count = 0;
    String result = "miss";
    int index = locationcells.indexOf(userinput);
    if(index>=0){
      locationcells.remove(index);
      if(locationcells.isEmpty()){
        result="kill";
        System.out.println("Ouch! you sunk "+name+" : (");
      }else{
        result = "hit";
      }
    }
    return result;
  }
}
class Gamehelper {
  private static final String alphabet = "abcdefg";
  private int gridLength = 7;
  private int gridSize = 49;
  private int [] grid = new int[gridSize];
  private int comCount = 0;
  public String getuserinput(String prompt) {
     String  inputLine = null;
     System.out.print(prompt + "  ");
     try {
       BufferedReader is = new BufferedReader(
       new InputStreamReader(System.in));
       inputLine = is.readLine();
       if (inputLine.length() == 0 )  return null;
     } catch (IOException e) {
       System.out.println("IOException: " + e);
     }
     return inputLine.toLowerCase();
  }
 public ArrayList<String> placedotcom(int comSize) {
    ArrayList<String> alphaCells = new ArrayList<String>();
    String [] alphacoords = new String [comSize];      // holds ‘f6’ type coords
    String temp = null;                                // temporary String for concat
    int [] coords = new int[comSize];                  // current candidate coords
    int attempts = 0;                                  // current attempts counter
    boolean success = false;                           // fl ag = found a good location ?
    int location = 0;                                  // current starting location
    comCount++;                                        // nth dot com to place
    int incr = 1;                                      // set horizontal increment
    if ((comCount % 2) == 1) {                         // if odd dot com (place vertically)
      incr = gridLength;                               // set vertical increment
    }
    while ( !success & attempts++ < 200 ) {            // main search loop  (32)
      location = (int) (Math.random() * gridSize);     // get random starting point
      int x = 0;                                       // nth position in dotcom to place
       success = true;                                 // assume success
       while (success && x < comSize) {                // look for adjacent unused spots
         if (grid[location] == 0) {                    // if not already used
            coords[x++] = location;                    // save location
            location += incr;                          // try ‘next’ adjacent
            if (location >= gridSize){                 // out of bounds - ‘bottom’
              success = false;                         // failure
            }
            if (x>0 && (location % gridLength == 0)) { // out of bounds - right edge
              success = false;                         // failure
            }
          }
          else {                                      // found already used location
              success = false;                          // failure
          }
      }
    }                                                   // end while
    int x = 0;                                          // turn location into alpha coords
    int row = 0;
    int column = 0;

    while (x < comSize) {
      grid[coords[x]] = 1;                              // mark master grid pts. as ‘used’
      row = (int) (coords[x] / gridLength);             // get row value
      column = coords[x] % gridLength;                  // get numeric column value
      temp = String.valueOf(alphabet.charAt(column));   // convert to alpha
      alphaCells.add(temp.concat(Integer.toString(row)));
      x++;

    }

    return alphaCells;
  }
}
