package kodlamaio.hrms.core.utilities.helpers;

public class GenerateRandomVerificationCode {

	public static String getRandomVerificationCode() {
		  String character="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		            + "0123456789"
		            + "abcdefghijklmnopqrstuvxyz";
		    
		    StringBuilder stringBuilder = new StringBuilder(10);
		    
		    for(int i=0 ; i<10 ; i++) {
		    	int index = (int)(character.length()*Math.random());
		    	stringBuilder.append(character.charAt(index));
		    }
		    
		    return stringBuilder.toString();
		
		
	}

  
    
}
