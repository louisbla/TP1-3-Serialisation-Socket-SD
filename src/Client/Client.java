package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String args[]) throws IOException {
		System.out.println("********* Affichage console du client **********");
		Socket soc = null;
		OutputStream os = null;
		ObjectOutput oos = null;
		InputStream is = null;
		ObjectInputStream ios = null;
		
		try {
			//Cr�ation d'un nouveau Socket, et ouverture du flux sortant
			soc = new Socket(InetAddress.getLocalHost(), 8020);
			os = soc.getOutputStream();
			oos = new ObjectOutputStream(os);
			
			//Ecrire les objets � envoyer
			oos.writeObject(Calc.class);
			System.out.println("Client : La classe Calc a �t� envoy�e au serveur");
			oos.writeObject("Calc&add&3&5");
			System.out.println("Client : J'ai demand� au serveur d'utiliser la m�thode 'add' avec les param�tres 3 et 5");
			
			//Envoyer les objects
			oos.flush();
			
			//Ouvrir le flux entrant et attendre la r�ponse
			is = soc.getInputStream();
			ios = new ObjectInputStream(is);
			int result = ios.readInt();
			
			//Afficher le r�sultat et fermer le flux
			System.out.println("Client : Le serveur a renvoy� le r�sultat = " + result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erreur");
			System.exit(1);
		}finally {
			ios.close();
			oos.close();
		}
	}
}
