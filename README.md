TP1-3-Serialisation-Socket

Ce projet simule l'exécution d'un client et d'un serveur.
Le client possède une classe "Calc" contenant des méthodes de calcul.
Le but est d'envoyer cette classe au serveur via le réseau, puis de lui fournir le nom de la méthode à exécuter ainsi que les paramètres. Le serveur doit alors exécuter la méthode associée, et renvoyer le résultat au client.

Cela simule l'exécution de code de manière distante/distribuée.

------

Afin de tester le code, il faut :
1) Importer le code dans Eclipse.
2) Exécuter la fonction main de la classe "Server" (Clic droit -> Run As -> Java application)
3) Exécuter la fonction main de la classe "Client" (Clic droit -> Run As -> Java application)

L'ordre d'exécution est important, car le serveur doit être disponible avant que le client puisse faire une requête.

Explication du fonctionnement :
Au moment du lancement de la classe Client, celui-ci envoie la classe "Calc" et les paramètres (lignes 20 et 21) de la classe "Client" au serveur via un Socket.

Le serveur reçoit la classe, la stocke en mémoire, puis exécute la fonction demandée avec les paramètres fournis.
Afin de simuler un calcul lourd, j'ai ajouté la commande "Thread.sleep(4000)" afin de mettre en pause le serveur 4 secondes, avant de renvoyer le résultat.




