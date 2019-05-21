package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Server {
	public static void main(String args[]) {
		ServerSocket ser = null;
		Socket soc = null;
		try {
			//Créer un nouveau ServerSocket
			ser = new ServerSocket(8020);
			System.out.println("Server : En attente de contact d'un client");
			//Obtenir un Socket à partir du ServerSocket
			soc = ser.accept();
			//Obtenir un InputStream à partir du socket
			InputStream is = soc.getInputStream();
			//Créer un ObjectInputStream à partir du InputStream
			ObjectInput ios = new ObjectInputStream(is);
			//Lire les objets
			Class calc = (Class) ios.readObject();
			String requete = (String) ios.readObject();
			String[] substrings = requete.split("&");
			Object instance = calc.newInstance();
			
			Method[] methods = calc.getDeclaredMethods();
			System.out.println("Server : Methodes disponibles dans la classe "+ calc.getName() +": ");
			for(Method meth : methods) {
				System.out.println("Server : " + meth.getName());
			}
			System.out.println();
			
			Class<?>[] paramTypes = {String.class, String.class};
			Method method;
			
			method = calc.getMethod("add", String.class, String.class);
			
			String[] arguments = {substrings[2],substrings[3]};
			System.out.println("Server : Appel de la méthode '"+method.getName()+"' avec les paramètres "+ substrings[2] + " et " + substrings[3] );
			int result = (int)method.invoke(instance, arguments);
			//Simule un temps de calcul élevé
			System.out.println("Server : On simule un temps de calcul de 4 secondes...");
			Thread.sleep(4000);
			
			//Afficher les objets
			System.out.println("Server : resultat = " + result);
			
			OutputStream os = soc.getOutputStream();
			//Créer un ObjectInputStream à partir du InputStream
			ObjectOutput oos = new ObjectOutputStream(os);
			oos.writeInt(result);
			oos.flush();
			
			
			//Fermer les Stream
			ios.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
