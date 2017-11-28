package com.risk.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {

	public static void save(Serializable data, String fileName) throws Exception {
		try (ObjectOutputStream objOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			objOutput.writeObject(data);
		}
	}

	public static Object load(String fileName) throws Exception {

		try (ObjectInputStream objInput = new ObjectInputStream(Files.newInputStream((Paths.get(fileName))))) {
			return objInput.readObject();
		}

	}
}
