package english.translate.dictionary.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DictionaryReader {
	public Map<String,String> map = new HashMap<String,String>();
	public List<String> enList = new ArrayList<String>();
	public List<String> ruList = new ArrayList<String>();
	public List<List<String>> enListList = new ArrayList<List<String>>();
	public List<List<String>> ruListList = new ArrayList<List<String>>();
	
	private static List<String> getLinesFromFile(String file){
		List <String> list = new ArrayList<String>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				StringBuilder sb = new StringBuilder();
				String line = "";
				while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
					if(line != null){
						if(!line .equals("")){
							list.add(line);
						}
					}
			    }
				
			} finally {
				br.close();
			}
		}catch(Exception e){
			System.out.println("error : "+e);
		}
		return list;
		
	}
	
	public void readFiles(String englishFileName, String russianFileName){
		enList = getLinesFromFile(englishFileName);
		ruList = getLinesFromFile(russianFileName);
		if(enList.size()==ruList.size()){
			for(int i=0; i<enList.size(); i++){
				map.put(enList.get(i), ruList.get(i));
			}
		}else{
			System.out.println("Invalid files : size of en file not equal tosize of ru file");
		}		
		fillLists(50);
	}
	
	public void fillLists(int count){
		Random random = new Random();
		
		if(enList.size()==ruList.size()){
			List <String> enTemp = new ArrayList<String>();
			List <String> ruTemp = new ArrayList<String>();
			for(int i=1; i<=enList.size(); i++){
				int r = random.nextInt();
				if(i%count==0){
					enListList.add(enTemp);
					ruListList.add(ruTemp);
					enTemp = new ArrayList<String>();
					ruTemp = new ArrayList<String>();
				}
				enTemp.add(enList.get((Integer.signum(r)*r)%enList.size()));
				ruTemp.add(map.get(enList.get((Integer.signum(r)*r)%enList.size())));
//				enList.remove(r%enList.size());
				
			}
		}
	}
}
