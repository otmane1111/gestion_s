package otmane.ot;

import java.util.Scanner;

public class GestionStock {
    private static Produit[] produits = new Produit[100];
    private static int compteur = 0;

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher tous les produits");
        System.out.println("5. Rechercher un produit par nom");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
    }

    public static void ajouterProduit(Produit produit) {
        if (compteur < 100) {
            // Vérification unicité du code
            for (int i = 0; i < compteur; i++) {
                if (produits[i].getCode() == produit.getCode()) {
                    System.out.println("Erreur : Le code produit existe déjà.");
                    return;
                }
            }
            produits[compteur++] = produit;
            System.out.println("Produit ajouté avec succès.");
        } else {
            System.out.println("Erreur : La capacité maximale est atteinte.");
        }
    }

    public static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < compteur; i++) {
            if (produits[i].getCode() == code) {
                produits[i].setNom(nouveauNom);
                produits[i].setQuantite(nouvelleQuantite);
                produits[i].setPrix(nouveauPrix);
                System.out.println("Produit modifié avec succès.");
                return;
            }
        }
        System.out.println("Produit introuvable.");
    }

    public static void supprimerProduit(int code) {
        for (int i = 0; i < compteur; i++) {
            if (produits[i].getCode() == code) {
                produits[i] = produits[--compteur];
                produits[compteur] = null;
                System.out.println("Produit supprimé avec succès.");
                return;
            }
        }
        System.out.println("Produit introuvable.");
    }

    public static void afficherProduits() {
        if (compteur == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }
        for (int i = 0; i < compteur; i++) {
            System.out.println(produits[i]);
        }
    }

    public static void rechercherProduit(String nom) {
        for (int i = 0; i < compteur; i++) {
            if (produits[i].getNom().equalsIgnoreCase(nom)) {
                System.out.println(produits[i]);
                return;
            }
        }
        System.out.println("Produit introuvable.");
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;
        for (int i = 0; i < compteur; i++) {
            valeurTotale += produits[i].calculerValeurStock();
        }
        System.out.println("Valeur totale du stock : " + valeurTotale);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Code : ");
                    int code = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Quantité : ");
                    int quantite = scanner.nextInt();
                    System.out.print("Prix : ");
                    double prix = scanner.nextDouble();
                    ajouterProduit(new Produit(code, nom, quantite, prix));
                    break;
                case 2:
                    System.out.print("Code du produit à modifier : ");
                    code = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouvelle quantité : ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Nouveau prix : ");
                    double nouveauPrix = scanner.nextDouble();
                    modifierProduit(code, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;
                case 3:
                    System.out.print("Code du produit à supprimer : ");
                    code = scanner.nextInt();
                    supprimerProduit(code);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    System.out.print("Nom du produit à rechercher : ");
                    nom = scanner.nextLine();
                    rechercherProduit(nom);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (choix != 7);

        scanner.close();
    }
}
