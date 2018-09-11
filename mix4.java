public class mix4{
  int counter=0;
  public static void main(String [] args){
    int count=0;
    mix4 [] m4a=new mix4[20];
    int x=0;
    while (x<19){
      m4a[x]=new mix4();
      m4a[x].counter=m4a[x].counter+1;
      count=count+1;
      count=count+m4a[x].maybenew(x);
      x=x+1;
    }
    System.out.println(count+" "+m4a[1].counter);
  }
  public int maybenew(int index){
    if(index<1){
      mix4 m4=new mix4();
      m4.counter=m4.counter+1;
      return 1;
    }
    return 0;
  }
}
