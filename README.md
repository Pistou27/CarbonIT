
# Exercice pratique - La carte aux trésors

Le gouvernement péruvien vient d’autoriser les aventuriers en quête de trésors à explorer les 85 182
km² du département de la Madre de Dios. Vous devez réaliser un système permettant de suivre les
déplacements et les collectes de trésors effectuées par les aventuriers. Le gouvernement péruvien
étant très à cheval sur les bonnes pratiques de code, il est important de réaliser un code de qualité,
lisible, et maintenable




## Auteur

- [Alexandre PERRAULT](https://github.com/Pistou27/CarbonIT)


## Variables d'environnements

Pour faire fonctionner le programme il est nécessaire d'avoir une donnée d'entrée sous format texte avec comme nom fichierEntree.txt. Celui-ci doit ce trouver à la racine du projet (Exemple initialisé dans le projet)

`fichierEntree.txt`

Il en sortira un fichier de sortie sous format texte 

`fichierSortie`




## FAQ

#### Question 1

S'il y a plus qu'une donnée de dimension dans le fichierEntree.txt le programme prendra en compte la première valide 
ex: 
    C - AAA - 23
    C - 12 - 23
    C - 23 - 15

Ce sera C - 12 -23


#### Question 2

Si je mets des données valides dans le désordre ?
La méthode Main.validateInput remettra toujours les dimension en première ligne

#### Question 3

Si je mets une entité(Montagne/Trésor/Aventurier) sur une autre?
On part du principe que les données sont sur des cases distinct en entré.

#### Question 3

Si je mets une entité(Montagne/Trésor/Aventurier) sur une case en dehors des dimmension?
Les entités en dehors ne seront pas affiché ni comptabilisé (le gouvernement péruvien ne rémunere pas les gens qui ne savent pas lire une carte)



## Run Locally

Se mettre à la racine du projet.

javac src/main/java/org/example/Main.java
src/main/java/org/example/use_case/TreasureMapGeneration.java
src/main/java/org/example/entity/*.java

cd src/main/java

java org.example.Main
