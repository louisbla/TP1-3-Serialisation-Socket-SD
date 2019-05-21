package Server;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) {
		System.out.println("********* Affichage console du serveur **********");
		ServerSocket ser = null;
		Socket soc = null;
		try {
			//Créer un nouveau Socket
			ser = new ServerSocket(8020);
			System.out.println("Server : En attente de contact d'un client");
			
			//Attendre la connexion d'un client, et ouvrir  un flux entrant
			soc = ser.accept();
			InputStream is = soc.getInputStream();
			ObjectInput ios = new ObjectInputStream(is);
			Class calc = (Class) ios.readObject();		//Reception de la classe
			String requete = (String) ios.readObject();	//Reception de la requete et paramètres
			String[] substrings = requete.split("&");	//Séparation des paramètres
			
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
			
			//Appel de la méthode demandée
			String[] arguments = {substrings[2],substrings[3]};
			System.out.println("Server : Appel de la méthode '"+method.getName()+"' avec les paramètres "+ substrings[2] + " et " + substrings[3] );
			int result = (int)method.invoke(instance, arguments);
			
			//Simule un temps de calcul élevé
			System.out.println("Server : On simule un temps de calcul de 4 secondes...");
			Thread.sleep(4000);
			
			//Afficher le résultat à renvoyer
			System.out.println("Server : resultat = " + result);
			
			//Ouverture d'un flux sortant
			OutputStream os = soc.getOutputStream();
			ObjectOutput oos = new ObjectOutputStream(os);
			//Ecriture et envoie du résultat
			oos.writeInt(result);
			oos.flush();
			
			//Fermer les Stream
			ios.close();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
