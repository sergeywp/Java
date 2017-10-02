package english.translate.dictionary.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Test extends DictionaryReader {

	public static void main(String[] args) {
//		System.out.println("start");
		DictionaryReader dictionaryReader = new DictionaryReader();
		dictionaryReader.readFiles("G://en.txt", "G://ru.txt");
		
		List<String> enList = dictionaryReader.enList;
		List<String> ruList = dictionaryReader.ruList;
		List<List<String>> enListList = dictionaryReader.enListList;
		List<List<String>> ruListList = dictionaryReader.ruListList;
		
//		System.out.println(enListList.size());
//		System.out.println(ruListList.size());
		
		if(enList.size() == ruList.size()){
			
			for(int i=0; i<enListList.size(); i++){
				List <String> enTemp = enListList.get(i);
				List <String> ruTemp = ruListList.get(i);
				
//				System.out.println(enTemp.size());
//				System.out.println(ruTemp.size());

				int j=1;
				int randomNum = 0;
				String num = "";
				Random random = new Random();
				System.out.println("Level : "+i+"/"+enListList.size());
				System.out.println("To up the level enter \"0\"");
				System.out.println("-------------------------------------------");
				while(!num.equals("0")){
					
					j++;
					Scanner scan= new Scanner(System.in);
					num = scan.nextLine();
					
					if(j%2==0){
						int r = random.nextInt();
						if(enTemp.size()-1==0){
							num="0";
						}else{
							randomNum = Integer.signum(r)*r%(enTemp.size()-1);
							System.out.print(enTemp.get(randomNum ));
						}
					}else{
						System.out.println(ruTemp.get(randomNum));
						if(num.equals("+")){
							enTemp.remove(randomNum);
							ruTemp.remove(randomNum);
						}
					}						
				}

			}

			int i=1;
			int randomNum = 0;
			String num = "";
			Random random = new Random();
			while(!num.equals("0")){
				
				i++;
				Scanner scan= new Scanner(System.in);
				num = scan.nextLine();
				
				if(i%2==0){
					int r = random.nextInt();
					randomNum = Integer.signum(r)*r%(enList.size()-1);
					System.out.print(enList.get(randomNum ));
				}else{
					System.out.println(ruList.get(randomNum));
				}
					
			}

		}else{
			System.out.println("Invalid files : size of en file not equal tosize of ru file");
		}
		
		System.out.println("end");
		
	}

}
