package com.gorilla.automation.property;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Property extends PropertiesConfiguration {

	FileReader reader;

	/**
	 * Default Constructor
	 * 
	 * @param String Base Property File
	 * 
	 */
	public Property(String ApplicationPropertyFile) {
		initialize(ApplicationPropertyFile);
	}

	public Property() {
		super();
	}

	public int getSize() {
		@SuppressWarnings("unchecked")
		Iterator<String> keys = getKeys();
		int i = 0;
		while (keys.hasNext()) {
			i++;
			keys.next();
		}
		return i;
	}

	private void initialize(String ApplicationPropertyFile) {
		try {
			// Load Master Property File
			File f = new File(ApplicationPropertyFile);
			File F = new File(ApplicationPropertyFile);
			loadfile(F);

			// Load All other property files
			if (null != getString("resources") && getString("resources").length() != 0) {
				loadResources(f.getParent(), getString("resources"));
			}

			// Load All System Property
			loadSystemProperty();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads All System Property
	 * 
	 */
	private void loadSystemProperty() {
		try {
			Iterator<Object> sysProperty = System.getProperties().keySet().iterator();

			while (sysProperty.hasNext()) {
				String propertyName = String.valueOf(sysProperty.next());
				String propertyValue = System.getProperties().getProperty(propertyName);

				if (null != getString(propertyName) && getString(propertyName).trim().length() != 0) {

					super.setProperty(propertyName.trim(), propertyValue.trim());
				} else {
					addProperty(propertyName, propertyValue);
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to load System property...." + e.getMessage());
		}
	}

	/**
	 * Loads All Property files from given resource folder.
	 * 
	 * @param String Resource Dir name
	 * 
	 */

	public void loadResources(String parentDir, String resourceDir) {
		try {
			int noOfPropertyFiles = 0;
			System.out.println("Loading Resources : " + resourceDir);

			String resource_dir = resourceDir;
			String[] sub = resource_dir.split(";");

			// Loop Through all files and add properties.

			System.out.println("No of Resources Directory : " + sub.length);

			for (int i = 0; i < sub.length; i++) {
				System.out.println("Loading Resources from " + parentDir + "/" + sub[i]);
				File folder = new File(parentDir + "/" + sub[i]);
				File[] listOfFiles = folder.listFiles();

				for (int j = 0; j < listOfFiles.length; j++) {
					System.out.println("Loading Resources File : " + listOfFiles[j]);
					if (listOfFiles[j].getName().contains(".properties")) {
						noOfPropertyFiles++;
						loadfile(listOfFiles[j]);
					}
				}
			}
			System.out.println("Load " + noOfPropertyFiles + " property files.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to read any property.
	 * 
	 * @param String Key To Read
	 * 
	 */

	@Override
	public String getString(String key) {
		String value = super.getString(key);
		if (null == value)
			return "";

		Pattern MY_PATTERN = Pattern.compile("\\$\\{.*?\\}");
		Matcher m = MY_PATTERN.matcher(value);

		while (m.find()) {
			String strToReplace = m.group(0);
			String ReplacedString = m.group(0).replace("$", "").replace("{", "").replace("}", "");

			if (null != super.getString(ReplacedString)) {
				value.replace(strToReplace, super.getString(ReplacedString));
			}
		}
		return value;
	}

	/**
	 * Loads All Properties from given file.
	 * 
	 * @param File
	 * 
	 */

	public void loadfile(File f) {
		try {
			reader = new FileReader(f);
			if (null != reader.toString())
				load(reader);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}