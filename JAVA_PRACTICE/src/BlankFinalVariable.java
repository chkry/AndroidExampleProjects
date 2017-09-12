
public class BlankFinalVariable {

	  final int speedlimit;//blank final variable  
	    
	  BlankFinalVariable(int speed){  
//	  speedlimit=80;         //Blank Final variable can be changed only from constructor
	  speedlimit = speed;
	  System.out.println(speedlimit);  
	  }  
	  
	  public static void main(String args[]){  
	    new BlankFinalVariable(100);  
	 }  
	}  
