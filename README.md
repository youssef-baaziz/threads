# TP : Programmation Multi-Threads en Java

Ce projet met en œuvre la programmation multi-threads en Java à travers deux exercices :  
1. Un programme mettant en évidence le comportement des threads en affichant un nombre unique plusieurs fois en parallèle.  
2. Un programme utilisant un pool de threads pour calculer la somme des éléments d'un tableau de manière concurrente.

## Prérequis
- Java 8 ou une version ultérieure
- Un environnement de développement comme IntelliJ IDEA, Eclipse ou VS Code

---

## Exercice 1 : Classe Talkative

### Description
L'exercice illustre le fonctionnement du multi-threading en Java en créant une classe `Talkative` qui implémente `Runnable`. Chaque instance de `Talkative` affiche 100 fois une valeur unique.

### Implémentation
- Création de la classe `Talkative` avec un attribut entier unique
- Implémentation de l'interface `Runnable`
- Définition de la méthode `run()` pour afficher 100 fois l'entier
- Création et démarrage de 10 threads dans `main`

### Exemple d'utilisation
```java
public class TalkativeMain {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Talkative(i));
            thread.start();
        }
    }
}
```

### Observation
L'exécution des threads est asynchrone et l'affichage peut apparaître dans un ordre aléatoire.

---

## Exercice 2 : Sommeur avec Pool de Threads

### Description
Cet exercice divise un tableau en plusieurs segments et calcule la somme de chaque segment en parallèle à l'aide d'un pool de threads.

### Implémentation
- Création d'une classe `Sommeur` qui implémente `Runnable`
- Calcul de la somme d'une plage de valeurs d'un tableau
- Utilisation d'un pool de threads pour paralléliser le calcul
- Agrégation des résultats

### Exemple d'utilisation
```java
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] tableau = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int nbThreads = 2;
        ExecutorService executor = Executors.newFixedThreadPool(nbThreads);
        
        Future<Integer> future1 = executor.submit(new Sommeur(tableau, 0, 5));
        Future<Integer> future2 = executor.submit(new Sommeur(tableau, 5, 10));
        
        int sommeTotale = future1.get() + future2.get();
        
        executor.shutdown();
        System.out.println("Somme totale: " + sommeTotale);
    }
}
```

### Observation
L'utilisation d'un pool de threads permet une exécution plus rapide et efficace sur des tableaux de grande taille.

---

## Captures d'écran
Ajoutez ici des captures d'écran montrant l'exécution des programmes dans la console.
- Talkative
<img width="315" alt="Screenshot 2025-04-01 at 17 58 46" src="https://github.com/user-attachments/assets/426f0ebb-22e4-48c6-9c27-34d57e4e639a" />

- Sommeur
<img width="315" alt="Screenshot 2025-04-01 at 17 59 07" src="https://github.com/user-attachments/assets/c472af06-88c0-411b-af37-209bbcbdfded" />

---

## Auteur
Ce TP a été réalisé dans le cadre du cours de Programmation Orientée Objet Java 2023, encadré par **Mr. Abdelmajid BOUSSELHAM**.
