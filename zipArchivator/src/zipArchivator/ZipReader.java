package zipArchivator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipReader {

	/**
	 * @param args
	 */
	public static String PR_RELEASE ="";
	public static String PR_PATCHLEVEL ="";
	
	public static void main(String[] args) {
		
		String searchParam1 = "pr_release:";
        String searchParam2 = "pr_patchlevel:";
        String searchParam5 = "keyvendor:";
        String searchParam3 = "componentelement  name=";
        String searchParam4 = "SOURCEARCHIVES/";
        List<String>keyvendors = new ArrayList<String>();
		
        try{
        	ZipInputStream zin = new ZipInputStream(new FileInputStream("/home/sergey/workspace/zipArchivator/MyComponenJMS.sca"));
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){
                 
            	List<String> deployApplications = new ArrayList<String>();
            	List<String> sourceApplications = new ArrayList<String>();
            	
                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
             
                if(size>0){
                	if(name.contains("SAP_MANIFEST")){
                		
                		ZipFile zipFile = new ZipFile("/home/sergey/workspace/zipArchivator/MyComponenJMS.sca");
                		InputStream inputStream =zipFile.getInputStream(entry);
                		
                		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                		
                        StringBuilder out = new StringBuilder();
                        String line;
                        
                        while ((line = reader.readLine()) != null) {
                        	//поиск значения pr_release:
                        	if(line.contains(searchParam1)){
                        		out.append(line+"\n");
                        		boolean space=true;
                        		String[] parse=line.substring(line.toString().lastIndexOf(searchParam1)+searchParam1.length()).split(" ");
                        		for(int i=0; i<parse.length;i++){
                        			if(parse[i].length()>0){
                        				PR_RELEASE = parse[i];
                        				break;
                        			}                        				
                        		}                        		
                        	}
                        	
                        	// поиск значения pr_patchlevel
                        	if(line.contains(searchParam2)){
                        		out.append(line+"\n");
                        		boolean space=true;
                        		String[] parse=line.substring(line.toString().lastIndexOf(searchParam2)+searchParam2.length()).split(" ");
                        		for(int i=0; i<parse.length;i++){
                        			if(parse[i].length()>0){
                        				PR_PATCHLEVEL= parse[i];
                        				break;
                        			}                   				
                        		}                        		
                        	}
                        	
//                        	// поиск значения keyvendor
//                        	if(line.contains(searchParam5)){
//                        		out.append(line+"\n");
//                        		boolean space=true;
//                        		String[] parse=line.substring(line.toString().lastIndexOf(searchParam5)+searchParam5.length()).split(" ");
//                        		for(int i=0; i<parse.length;i++){
//                        			if(parse[i].length()>0){
//                        				keyvendors.add(parse[i]);
//                        				break;
//                        			}                   				
//                        		}                        		
//                        	}
                        	
                        	// поиск значения DEPLOYARCHIVES
                        	if(line.contains(searchParam3)){
                        		out.append(line+"\n");
                        		boolean space=true;
                        		String[] parse=line.substring(line.toString().lastIndexOf(searchParam3)+searchParam3.length()).split("\"");
                        		for(int i=0; i<parse.length;i++){
                        			if(parse[i].length()>0){
                        				deployApplications.add(parse[i]);
                        				break;
                        			}                   				
                        		}                        		
                        	}
                        	
                        	//поиск значений имен компонентов
                        	
                        }
                        reader.close();
                        
                	}
                	
//                	if(name.contains(searchParam4)){
//                		System.out.print(name.substring(name.lastIndexOf(searchParam4)+searchParam4.length())+"\n");
//                	}
//                	System.out.print("keyvendors : "+keyvendors.size());
                	
                }
            }

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
	}
}
