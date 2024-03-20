package com.dumbcatan.controller;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Resources;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class ResourceLogger {
	
//	static Logger LOGGER = Logger.getLogger(ResourceLogger.class.getName());
//    static FileHandler fh;
//    static SimpleFormatter sf = new SimpleFormatter();
//    
//    static {
//    	try {
//    		fh = new FileHandler("ResourceTransaction.log", true);
//            fh.setFormatter(sf);
//    	}catch(IOException e) {
//    		e.printStackTrace();
//    	} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//    }
	
//	@PostMapping("logging/resources")
//	public static void logInitialResources(@RequestBody Resources resources) {
//		
//		LOGGER.log(Level.INFO, "Test");
////		DataOutputStream datOS = new DataOutputStream(new FileOutputStream("logTest.txt", true));
//		System.out.println("Gained Resources: " + resources.getGainedResources());
//		LOGGER.log(Level.INFO, resources.getGainedResources());
////		datOS.writeUTF(resources.getGainedResources() + "\n");
//		System.out.println("Player Data Before: " + resources.getPlayerDataBefore());
//		LOGGER.log(Level.INFO, resources.getPlayerDataBefore());
////		datOS.writeUTF(resources.getPlayerDataBefore() + "\n");
//		System.out.println("Player Data After: " + resources.getPlayerDataAfter());	
//		LOGGER.log(Level.INFO, resources.getPlayerDataAfter());
////		datOS.writeUTF(resources.getPlayerDataAfter() + "\n\n");
//		
//	}
}
