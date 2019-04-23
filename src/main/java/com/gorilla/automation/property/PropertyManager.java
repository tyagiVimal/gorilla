package com.gorilla.automation.property;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	/**
	 * Thread Safe Properties Object
	 * 
	 * 
	 */

	private static ThreadLocal<Property> Properties = new ThreadLocal<Property>() {
		protected Property initialValue() {
			Property p = new Property(System.getProperty("base.property.file", "./src/test/resources/base.properties"));
			return p;
		};
	};

	/**
	 * Resource Bundler Object
	 */

	public static Property getResourceBundle() {
		return Properties.get();
	}

	public static void PropertyWriter(String fileName, String propertyName, String propertyValue) {
		// File configFile = new File("resources/runtime.properties");
		File configFile = new File(fileName);
		try {
			Properties props = new Properties();
			props.setProperty(propertyName, propertyValue);
			FileWriter writer = new FileWriter(configFile);
			props.store(writer, propertyName);
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static String PropertyReader(String fileName, String propertyName) {
		File configFile = new File(fileName);
		String propertyValue = "";
		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			propertyValue = props.getProperty(propertyName);
			System.out.println("Reading Property :: " + propertyName + " From File :: " + fileName + " >>> Value :: "
					+ propertyValue);
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return propertyValue;
	}

	public static void main(String args[]) {
		System.out.println(PropertyManager.getResourceBundle().getString("BASE_URL"));
	}
}