package com.venkyb.instagramone;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;



//import com.sun.tools.classfile.InnerClasses_attribute.Info;



@SpringBootApplication
//@EnableEncryptableProperties
//@EnableSwagger2
//@EnableWebMvc
public class InstagramCloneOneApplication {

	private static final Logger logger = LogManager.getLogger(InstagramCloneOneApplication.class);
	public static void main(String[] args) {
		logger.info("The instagram clone app has started");
		SpringApplication.run(InstagramCloneOneApplication.class, args);
	}
	
	

}
