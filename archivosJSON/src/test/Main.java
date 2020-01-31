package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Basado en: http://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5
 * 
 * GSON se puede descargar en:
 * https://mvnrepository.com/artifact/com.google.code.gson/gson
 */
public class Main {

	public static void main(String[] args) {

		Gson gson = new Gson();
		
		String path = "./data/data.json";

		
		System.out.println("Lectura de un JSON a un arreglo");
		System.out.println("--------------------------");
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(path));
			Message[] lista = gson.fromJson(reader, Message[].class);
			System.out.println(Arrays.toString(lista));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		System.out.println("Lectura de un JSON a una Lista");
		System.out.println("--------------------------");
		try {
			reader = new JsonReader(new FileReader(path));
			Type collectionType = new TypeToken<List<Message>>(){}.getType();
			List<Message> lista = gson.fromJson(reader, collectionType);
			System.out.println(Arrays.toString(lista.toArray()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Lectura de un pedazo de un JSON. Especificamante (body.data)");
		System.out.println("--------------------------");
		// Mirar 
		// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.6
		// https://static.javadoc.io/com.google.code.gson/gson/2.8.6/com.google.gson/com/google/gson/JsonElement.html
		String path2 = "./data/data_anidated.json";
		try {
			reader = new JsonReader(new FileReader(path2));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonElement e = elem.getAsJsonObject().get("body").getAsJsonObject().get("data");
			Message[] lista = gson.fromJson(e, Message[].class);
			System.out.println(Arrays.toString(lista));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Lectura de JSON (file) a Objeto. Metodo manual Streams");
		System.out.println("--------------------------");

		try {
			List<Message> lista = readJsonStream(new FileInputStream(path));
			System.out.println(Arrays.toString(lista.toArray()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Lectura de JSON (file) a Objeto. Metodo manual 2");
		System.out.println("--------------------------");

		try {
			List<Message> lista = new ArrayList<Message>();

			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonArray();
			for(JsonElement e: ja) {
				int id = e.getAsJsonObject().get("id").getAsInt();
				String text = e.getAsJsonObject().get("text").getAsString();

				String username = e.getAsJsonObject().get("user").getAsJsonObject().get("name").getAsString();
				int followersCount = e.getAsJsonObject().get("user").getAsJsonObject().get("followers_count").getAsInt();
				User user = new User(username, followersCount);
				
				List<Double> geo = new ArrayList<Double>();
				if(e.getAsJsonObject().has("geo") && !e.getAsJsonObject().get("geo").isJsonNull()) {
					for(JsonElement geoElem: e.getAsJsonObject().get("geo").getAsJsonArray()) {
						geo.add(geoElem.getAsDouble());

					}
				}
				Message m = new Message(id, text, user, geo);
				lista.add(m);
			}

			System.out.println(Arrays.toString(lista.toArray()));


		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Escritura y uso con un String");
		System.out.println("--------------------------");
		
		User juan = new User("Juan", 5);
		User maria = new User("Maria", 3);
		User[] lista = { juan, maria };

		System.out.println("Escritura a JSON");
		String ss = gson.toJson(lista);
		System.out.println(ss);

		System.out.println();
		System.out.println("Lectura de JSON (String) a Objeto");
		User[] lista2 = gson.fromJson(new StringReader(ss), User[].class);
		System.out.println(Arrays.toString(lista2));

		System.out.println();
		System.out.println("Lectura de JSON (file) a Objeto");

	}

	public static List<Message> readJsonStream(InputStream in) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try {
			return readMessagesArray(reader);
		} finally {
			reader.close();
		}
	}

	public static List<Message> readMessagesArray(JsonReader reader) throws IOException {
		List<Message> messages = new ArrayList<Message>();

		reader.beginArray();
		while (reader.hasNext()) {
			messages.add(readMessage(reader));
		}
		reader.endArray();
		return messages;
	}

	public static Message readMessage(JsonReader reader) throws IOException {
		long id = -1;
		String text = null;
		User user = null;
		List<Double> geo = null;

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("id")) {
				id = reader.nextLong();
			} else if (name.equals("text")) {
				text = reader.nextString();
			} else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
				geo = readDoublesArray(reader);
			} else if (name.equals("user")) {
				user = readUser(reader);
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return new Message(id, text, user, geo);
	}

	public static List<Double> readDoublesArray(JsonReader reader) throws IOException {
		List<Double> doubles = new ArrayList<Double>();

		reader.beginArray();
		while (reader.hasNext()) {
			doubles.add(reader.nextDouble());
		}
		reader.endArray();
		return doubles;
	}

	public static User readUser(JsonReader reader) throws IOException {
		String username = null;
		int followersCount = -1;

		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("name")) {
				username = reader.nextString();
			} else if (name.equals("followers_count")) {
				followersCount = reader.nextInt();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return new User(username, followersCount);
	}

}
