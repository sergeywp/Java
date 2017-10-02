import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cezar {

	/**
	 * Method for putting into list RUS+ENG Alphabeth
	 * 
	 * @param list - input list for adding
	 * @return list
	 */
		public static List<String> MakeDict(List<String> list){
        for(char i='a';i<='z';i++){
        	list.add(String.valueOf(i));
        }
        for(char i='A';i<='Z';i++){
        	list.add(String.valueOf(i));
        }
        for(char i='а';i<='я';i++){
        	list.add(String.valueOf(i));
        }
        for(char i='А';i<='Я';i++){
        	list.add(String.valueOf(i));
        }
        for(char i=' ';i<='@';i++){
        	list.add(String.valueOf(i));
        }    
        return list;
    }
    /**
     * 
     * @param InputStr - Input cycle String
     * @param n        - Int -for what characters moving
     * @param left     - "true" for left cycle moving, "false" for right
     * @return
     * 
     */
    private static String CycleSdvig(String InputStr,int n, boolean left)
    {
    	if((n==0)||(n%InputStr.length()==0))
    		return InputStr;
    	else{    		
    		if(n>InputStr.length()){
    			n=n%InputStr.length();
    		}
    			
    		String OutputStr="";
    		//влево
    		if(left){
    			OutputStr=InputStr.substring(n, InputStr.length())+InputStr.substring(0,n);    			
    		}
    		//вправо
    		else{
            	OutputStr=InputStr.substring(InputStr.length()-n, InputStr.length())+InputStr.substring(0,InputStr.length()-n);
    		}
        	return OutputStr; 
    	}    	
    }
    
    private static Random random = new Random();

    private int generateRandom(int n) {
    	return Math.abs(random.nextInt()) % n;
    }    
    
    /**
     * Changing of Dictonary type from String list, to String[]
     * @param list  - input list
     * @return      -output massive []
     */
    public static String getStringDict(List<String> list)
    {
    	String str="";
    	for(int j=0; j<list.size(); j++)
    	{
    		str=str+list.get(j);
    	}
    	return str;
    }
    
    /**
     * random mixing of elements of the List
     * @param Stack   - input <String>List
     * @return        - output <String>List
     */
    public static List<String> ChangeDict(List<String> Stack){ 
    	List<String> ShuffledStack = new ArrayList<String>();
    	int size = Stack.size();

    	while(ShuffledStack.size() != size)
    	        {
    	            int r = (int)(Math.random()*(size));
    	                  if(!ShuffledStack.contains( Stack.get( r   )))
    	                      ShuffledStack.add((String) Stack.get( r   ));
    	        }
    	return ShuffledStack;
    }
    
    /**
     * Cezar Encryption of input String
     * @param InputString   - input string for encryption
     * @param Dict          - use of dictionary foe encrypt (use the same for encryption and decryption)
     * @param n             - step of moving (use the same for encryption and decryption)
     * @return
     */
    public static String Encrypt(String InputString, String Dict, int n)
    {
    	String OutputString="";    
    	String CycleDict=CycleSdvig(Dict, n, true);
    	
    	//encrypt
    	for(int i=0; i<InputString.length();i++)
    	{
    		for(int j =0; j<Dict.length();j++)
    		{
    			if(String.valueOf(Dict.charAt(j)).equals(String.valueOf(InputString.charAt(i))))
    			{
    				OutputString = OutputString + String.valueOf(CycleDict.charAt(j));
    			}
    		}
    		
    	}
    	 
    	return OutputString;
    }
    /**
     * Cezar Decryption of input  Encrypted String
     * @param InputString   - input string for encryption
     * @param Dict          - use of dictionary for encrypt (use the same for encryption and decryption)
     * @param n             - step of moving (use the same for encryption and decryption)
     * @return
     */
    public static String Decrypt(String InputString, String Dict, int n)
    {
    	String OutputString="";    
    	String CycleDict=CycleSdvig(Dict, n, false);

		for(int i=0; i<InputString.length();i++)
    	{
    		for(int j =0; j <Dict.length();j++)
    		{
    			if(String.valueOf(Dict.charAt(j)).equals(String.valueOf(InputString.charAt(i))))
    			{
    				OutputString = OutputString + String.valueOf(CycleDict.charAt(j));
    			}
    		}
    		
    	} 
    	return OutputString;
    }
    
    /**
     * Example of how to use Cezar Encryption Algorythms 
     * @param args
     */
	public static void main(String[] args) {
		String str1 = "The American paddlefish is one of the largest freshwater fish in North America. Closely related to the sturgeon, it is one of only two extant taxa in the paddlefish family (the other being the Chinese paddlefish). Fossil records of paddlefish date back over 300 million years, nearly 50 million years before dinosaurs appeared. The American paddlefish is referred to as a relict species (because it retains some morphological characteristics of its early ancestors) even though it is highly derived with evolutionary adaptations specifically for filter feeding. The rostrum and cranium of American paddlefish are covered with tens of thousands of sensory receptors for locating swarms of zooplankton, their primary f";
		String Dict2 = getStringDict(
				//путаем словарь
				Cezar.ChangeDict(
						//создаем словарь типа list
						MakeDict(new ArrayList<String>())));
		
		String str2 =  Cezar.Encrypt(str1,Dict2, 1);		
		System.out.print(str1+"\n");
    	System.out.print(str2+"\n");    	
    	
    	str1 = Cezar.Decrypt(str2,Dict2, 1);
    	System.out.print(str1+"\n");
    	System.out.print(str2+"\n");
	}	
}
