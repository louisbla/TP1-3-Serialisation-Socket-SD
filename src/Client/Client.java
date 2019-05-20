package Client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String args[]) {
		try {
			//Création d'un nouveau Socket
			Socket soc = new Socket(InetAddress.getLocalHost(), 8020);
			//Obtenir un OutputStream à partir du socket
			OutputStream os = soc.getOutputStream();
			ObjectOutput oos = new ObjectOutputStream(os);
			//Ecrire les objets
			oos.writeObject(Calc.class);
			oos.writeObject("Calc&add&3&5");
			//Envoyer les objects, et fermer le flux
			
			InputStream is = soc.getInputStream();
			ObjectInputStream ios = new ObjectInputStream(is);
			
			oos.flush();
			int result = ios.readInt();
			System.out.println("Resultat = " + result);
			oos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erreur");
			System.exit(1);
		}
	}
}
