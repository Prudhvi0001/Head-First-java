public class monstertestdrive {
  public static void main(String [] args) {
    monster [] ma = new monster[3];
    ma[0] = new vampire();
    ma[1] = new dragon();
    ma[2] = new monster();
    for(int x = 0; x < 3; x++) {
      ma[x].frighten(x);
    }
  }
}
class monster{
  boolean frighten(int degree){
    System.out.println("arghhh");
    return true;
  }
}
class vampire extends monster{
  boolean frighten(int degree){
    System.out.println("A bitr..?");
    return true;
  }
}
class dragon extends monster{
  boolean frighten(int degree){
    System.out.println("breath fire");
    return false;
  }
}
