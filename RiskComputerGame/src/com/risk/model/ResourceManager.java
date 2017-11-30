package com.risk.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * The Class ResourceManager contains methods for saving data at particular state and resume again later.
 * reading the saved files to load any previously saved game.
 */
public class ResourceManager {

	/**
	 * This method save the required data into a file to save the game at certain state.
	 *
	 * @param data the data
	 * @param fileName the filename
	 * @throws Exception the exception
	 */
	public static void save(Serializable data, String fileName) throws Exception {
		try (ObjectOutputStream objOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			objOutput.writeObject(data);
		}
	}

	/**
	 * This method reads the saved data files to load the game saved previously.
	 *
	 * @param fileName the filename
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object load(String fileName) throws Exception {

		try (ObjectInputStream objInput = new ObjectInputStream(Files.newInputStream((Paths.get(fileName))))) {
			return objInput.readObject();
		}

	}
}
