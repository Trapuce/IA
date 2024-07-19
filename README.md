##############################################################
# Amadou Bah: 22013048                                      #
# Erwan Philippe Mensah: 21908945                           #
# Touré Papa Samba Khary: 21410550                          #
# Daouda Traoré: 22112112                                   #
##############################################################

# Block World Project

## Overview

Ce projet implémente diverses solutions pour le problème du Block World, un environnement classique en intelligence artificielle. Nous avons développé des tests de régularité, des planificateurs de recherche, des optimisations, des heuristiques, des tests de solveurs, des vues et une extraction de règles.

## Prérequis

Assurez-vous d'avoir les éléments suivants installés sur votre machine :
- JDK (Java Development Kit)
- Apache Ant

## Commandes Ant

Voici les commandes disponibles via Ant pour exécuter les différentes démonstrations et tâches de maintenance :

- **ant main** : Exécute la démo pour `constraint` (Main)
- **ant planner** : Exécute la démo pour `planner` (MainPlanner)
- **ant csp** : Exécute la démo pour `csp` (MainCSP)
- **ant datamining** : Exécute la démo pour `datamining` (MainDatamining)
- **ant compile** : Compile le projet
- **ant clean** : Supprime les répertoires créés (nettoyage)
- **ant dist** : Génère la documentation Java (Javadoc) et crée le fichier jar

## Fonctionnalités et Tests

### 1. Teste de Régularité

Dans la classe `BWRegularTest`, nous avons simulé trois états : deux réguliers et un irrégulier. Ces états sont testés pour vérifier leur régularité.

### 2. Teste des Planificateurs de Recherche

Dans la classe `PlanificateurTest`, un état est instancié sous forme de liste de piles contenant des listes de blocs. Les planificateurs de recherche sont exécutés pour atteindre deux objectifs (goal1 simple et goal2 complexe), démontrant la puissance des solveurs sur des problèmes de différentes tailles.

### 3. Optimisation

Une classe d'optimisation des problèmes de planification a été implémentée pour renvoyer les actions possibles suivant un état, évitant ainsi de tester des actions inutiles. Cela a permis de réduire considérablement le nombre de nœuds visités lors de l'exécution des solveurs BFS (de 63 178 à 668).

### 4. Heuristiques

Nous avons implémenté une deuxième heuristique (`HeuristicUnsuccesGoal`) pour calculer le nombre de blocs mal placés. Un solveur A* (`AstarPlanner3`) utilise cette heuristique pour les tests.

### 5. Tests des Solveurs

Nous avons choisi un problème de 10 blocs et 1 pile pour tester les solveurs. Les résultats montrent une diversité de performances selon les solveurs. Vous pouvez ajuster le nombre de blocs et de piles dans les premières lignes des tests.

### 6. Vues

Les classes de vue (`VuePlan`, `VueSolver`) sont des threads s'exécutant en parallèle pour éviter l'attente lors de la construction graphique des planificateurs. Des informations d'exécution des solveurs et planificateurs sont incluses dans leurs vues respectives.

### 7. Test d'Extraction

La classe exécutable `BWExtraction` permet de simuler différentes configurations, incluant une par défaut suggérée par Mr. Bruno Zanuttini (5 blocs, 5 piles). Vous pouvez extraire des règles de prémisse importante jusqu'à 100%. Le nombre de blocs et de piles peut être modifié dans les premières lignes de la classe.

## Structure du Projet

- `src/` : Contient les fichiers sources Java
- `build/` : Répertoire où les fichiers compilés seront placés
- `dist/` : Répertoire où les fichiers de distribution (Javadoc, Jar) seront placés
- `lib/` : Répertoire pour les dépendances et bibliothèques externes

## Instructions d'utilisation

### Compilation

Pour compiler le projet, utilisez la commande suivante :

```sh
ant compile
