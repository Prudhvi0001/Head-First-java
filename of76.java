interface Nose {
  public int iMethod();
}
abstract class Picasso implements Nose{
  int a = 10;
  public int iMethod(){
    return 7;
  }
}
class Clowns extends Picasso { }
class Acts  extends Picasso {
  public int iMethod(){
    return 5;
  }
}
public class of76 extends Clowns {
  public static void main(String [] args) {
    Picasso [] i = new Picasso[3];
    i[0] = new Acts();
    i[1] = new Clowns();
    i[2] = new of76();
    for(int x = 0; x < 3; x++) {
      System.out.println(i[x].iMethod() + i[x].a + " " + i[x].getClass( ) );
    }
  }
}
