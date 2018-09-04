package test;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Basado en:
 * http://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5
 */
public class Main {

	
	public static void main(String[] args) {
	
		String s = "[]";
		InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));

		try {
			
			System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			
			FileInputStream fis = new FileInputStream("./data/data.json");
			System.out.println(readJsonStream(fis));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//------------------------
		Gson gson = new Gson();
		int [] l = {1,2, 3};
		String ss = gson.toJson(l);
		System.out.println(ss);
		
		
		
		BagOfPrimitives obj = new BagOfPrimitives();
		String json = gson.toJson(obj); 
		System.out.println(json);
		
		BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
			
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
