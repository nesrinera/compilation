# compilation  Rahali Nesrin et Bouchenafa Rania Groupe5
Object de projet :
L’objectif de ce microprojet est de réaliser un compilateur d’un fichier source écrit dans le langage donné, en suivant les trois étapes d’analyse :
1.	Analyse lexical
2.	Analyse sémantique 
3.	Analyse syntaxique

Le langage de programmation :
On a utilisé le langage java 
Et pour l’environnement de travail on a choisi l’IDE Eclipse.
Fichier source utilisé:
 
1ere étape: chargement du fichier source. COMPILA
Pour commencer nous avons créé un jframecontenant un  boutons charger qui permet de charger un fichier de type compila 

2éme étape : analyse lexicale
 

Dans cette fonction nous avons déclaré :
Un premier tableau qui contient les mos clés du programme.
Un deuxième tableau qui contient la signification de ces mots.
Apres on a ajouté une boucle pour relier chaque mot clé avec sa signification.
3éme étape : analyse syntaxique :
 
Dans cette partie du code on va vérifier la syntaxe du code entrée s’elle est correcte.
Pour les mots clés on vérifier la syntaxe de chaque un
Et pour les instructions on va parcourir chaque instruction et on vérifier si elle est bien écrite.
4éme étape : analyse Sémantique :
 
Dans cette partie du code on va faire la définition sémantique du code entrée.
Pour les mots clés on vérifier la syntaxe de chaque un
Et pour les instructions on va parcourir chaque instruction et on va afficher si c’est une déclaration d’un entier ou un réel par exemple.
L’affichage de cette partie est comme le tableau du fiche tp
La partie main :
On a fait interface avec 4 boutons et un text area
Pour le bouton charger on va appeler la fonction charger ().
Pour le bouton analyse lexical on va appeler la fonction lexical avec un paramètre « mot ».
Pour le bouton analyse sémantique on va appeler la fonction sémantique avec un paramètre « mot ».
Pour le bouton analyse syntaxique on va appeler la fonction syntax avec un paramètre « mot ».


