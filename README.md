
# Lab #1 - 2048

## Description du Projet
Ce projet consiste à créer une version modifiée du jeu 2048 en utilisant JavaFX. L’objectif est de suivre une architecture MVW (Model-View-Whatever) et d’adapter les règles pour qu’elles soient différentes du jeu original. 

## Règles du Jeu
Voici les principales différences par rapport au 2048 classique :

1. **Valeurs dans les cases** :  
   Dans ce jeu, les cases contiennent des puissances de 2 (comme 2, 4, 8, … jusqu'à 2048) ou bien des valeurs dans un intervalle spécifique (à définir selon les besoins du projet).

2. **Effet d’un clic** :  
   Un clic sur une case déclenche un cumul des valeurs des cases voisines dans la case cliquée. Quelques variantes possibles :
   - Cumul des valeurs des 4 cases contiguës de même valeur.
   - Possibilité d’étendre le cumul aux cases sur la même ligne ou colonne.

   Après le cumul, les cases impliquées prennent une nouvelle valeur aléatoire pour garder un peu de surprise !

3. **Objectif et conditions de victoire/défaite** :
   - **Victoire** : Le joueur gagne lorsqu'une certaine valeur cible (comme 2048) est atteinte ou dépassée dans une case.
   - **Défaite** : La partie est perdue si aucune action ne permet de faire de nouveaux cumuls.
