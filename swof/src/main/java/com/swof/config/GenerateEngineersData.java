package com.swof.config;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.swof.models.Engineer;
/**
 * 
 * @author vipin
 * This Configration is take care to crate engineers list using json data from txt 
 */


@Configuration
public class GenerateEngineersData {

	List<Engineer> engineerList=new ArrayList<Engineer>();
	
	private static String ENGINEERS_FILE_NAME= "engineersJson.txt";
	FileInputStream fileInputStream = null;
	URL resource = null;
	File file = null;

	@PostConstruct
    public void init() throws Exception {
		JsonReader jsonReader=null;
		JsonObject jsonObject=null;
	
		//Gets the json file under src/main/resources/json folder
		Resource resource = new ClassPathResource("json/engineersJson.txt");
		File file = resource.getFile();
		fileInputStream = new FileInputStream(file);
		
		 jsonReader = Json.createReader(fileInputStream);
			
		//get JsonObject from JsonReader
		 jsonObject = jsonReader.readObject();
	    
	      
		  JsonArray jsonArray = jsonObject.getJsonArray("engineer");
					
					for(int i=0;i<jsonArray.size();i++){
						Engineer engineer=new Engineer();
						engineer.setId(jsonArray.getJsonObject(i).getString("id"));
						engineer.setName(jsonArray.getJsonObject(i).getString("name"));
						engineerList.add(engineer);
					}
				
				
		
		
	}
	
	public List<Engineer> getListFromJson(){
		return engineerList;
	}
	
	//Will Use when we configure Database instead of json
	public List<Engineer> getListFromDB(){
		return engineerList;
	}
}
