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
class simpledotcom{
  int[] locationcells;
  int numofhits = 0;
  public void setlocationcells(int[] locs){
    locationcells = locs;
  }
  public String checkyourself(String userguess){
    int guess = Integer.parseInt(userguess);
    String result = "miss";
    for(int cell : locationcells){
      if(guess==cell){
        result="hit";
        //locationcells.remove(guess);
        numofhits++;
        break;
      }
    }
    if (numofhits==locationcells.length){
      result="kill";
    }
    System.out.println(result);
    return result;
  }
}
public class simpledotcomgame{
  public static void main(String[] args){
    int numofguesses = 0;
    GameHelper helper = new GameHelper();
    simpledotcom thedotcom = new simpledotcom();
    int randomnum = (int) (Math.random()*5);
    int [] locations = {randomnum, randomnum+1, randomnum+2};
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
