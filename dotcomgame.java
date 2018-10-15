import java.util.ArrayList;
import java.io.*;
class GameHelper {
   public String getUserInput(String prompt) {
      String inputLine = null;
      System.out.print(prompt + "  ");
      try {
        BufferedReader is = new BufferedReader(
 new InputStreamReader(System.in));
       inputLine = is.readLine();
       if (inputLine.length() == 0 )  return null;
     } catch (IOException e) {
       System.out.println("IOException:"+ e);
     }
     return inputLine;
  }
}
class dotcom{
  private ArrayList<String> locationcells;
  //int numofhits = 0;
  public void setlocationcells(ArrayList<String> locs){
    locationcells = locs;
  }
  public String checkyourself(String userinput){
    //int guess = Integer.parseInt(userguess);
    String result = "miss";
    int index = locationcells.indexOf(userinput);
    if (index>=0){
      locationcells.remove(index);
      if (locationcells.isEmpty()){
        result="kill";
      }else{
        result = "hit";
      }
    }
    return result;
  }
}
public class dotcomgame{
  public static void main(String[] args){
    int numofguesses = 0;
    GameHelper helper = new GameHelper();
    dotcom thedotcom = new dotcom();
    int randomnum = (int) (Math.random()*5);
    ArrayList<String> locations;
    locations.add(randomnum);
    locations.add (randomnum+1);
    locations.add(randomnum+2);
    thedotcom.setlocationcells(locations);
    boolean isalive = true;
    while(isalive == true){
      String guess = helper.getUserInput("enter a number");
      String result = thedotcom.checkyourself(guess);
      numofguesses++;
      if (result.equals("kill")){
        isalive = false;
        System.out.println("you took"+numofguesses+"guesses");
      }
    }
  }
}
