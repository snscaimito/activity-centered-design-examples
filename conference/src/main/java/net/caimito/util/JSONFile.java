package net.caimito.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import net.caimito.conference.eventorganization.FileLocations;

import org.apache.tapestry5.json.JSONObject;

public class JSONFile {

	public static void write(String filename, JSONObject object) {
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(object.toString()) ;
			writer.close() ;
		} catch (IOException e) {
			throw new RuntimeException(e) ;
		}
	}
	
	public static JSONObject read(String filename) {
		return new JSONObject(readFile(filename));
	}
	
	private static String readFile(String pathname) {
		try {
			File file = new File(pathname);
			StringBuilder fileContents = new StringBuilder((int) file.length());
			Scanner scanner = new Scanner(file);
			String lineSeparator = System.getProperty("line.separator");

			try {
				while (scanner.hasNextLine()) {
					fileContents.append(scanner.nextLine() + lineSeparator);
				}
				return fileContents.toString();
			} finally {
				scanner.close();
			}
		} catch (IOException e) {
			return "{}" ;
		}
	}

	public static void delete(String filename) {
		File jsonTicketsDefined = new File(filename) ;
		if (jsonTicketsDefined.exists())
			jsonTicketsDefined.delete() ;
	}
	
}
