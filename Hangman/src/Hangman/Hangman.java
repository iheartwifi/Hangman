package Hangman;

import java.util.Scanner;



public class Hangman {
	
		//mots de 6 lettres ou moins
	    private static final String [] MOTS_1 =  
	        { "MAISON", "ECRAN", "SOLEIL", "VISAGE", "ZOMBIE", "LIVRE", "BRIQUE", 
	            "PLUIE", "SINGE", "LAIT", "WAGON", "MAGIE", "BOITE", "PIEGE", "MONTRE"};
	
	    //mots contenant entre 7 et 12 lettres
	    private static final String [] MOTS_2 =  
	        { "VAPORISATEUR","FOURGONNETTE","MOUSTIQUE","MICROSCOPE","MONSTRE",
	            "DIPLOMATE","FOURNAISE","CATASTROPHE","MILLENAIRE","BALLERINE", 
	            "OBSERVATOIRE","EUPHORIE", "ATMOSPHERE", "ALGORITHME", "METABOLISME"};
	
	    //mots de 13 lettres et plus
	    private static final String [] MOTS_3 =  
	        { "ALPHABETISATION", "MARIONNETTISTE", "IMMATRICULATION", "QUINCAILLERIE",
	            "DOCUMENTATION", "DEMONSTRATRICE", "PROGRAMMATION", "TOTALITARISME",
	            "HUMIDIFICATEUR", "ALOURDISSEMENT","IMMUNOTRANSFUSION", "IMPRESSIONNISME", 
	            "MANUTENTIONNAIRE", "PIEZOELECTRICITE", "ARCHIDUCHESSE"};
	
		/**
	     * Cette methode retourne un mot (nom commun) tire au hasard et dont le nombre de lettres
	     * varie selon le niveau de difficulte passe en parametre.
	     *      niveauDifficulte = 1 : mot de 6 lettres ou moins.
	     *      niveauDifficulte = 2 : mot entre 7 et 12 lettres.
	     *      niveauDifficulte = 3 ; mot de plus de 12 lettres.
	     *
	     * @param niveauDifficulte, le niveau de difficulte du mot retourne.
	     * @return un mot tire au hasard selon niveauDifficulte si celui-ci est entre 1 et 3 inclus.  
	     * Sinon, retourne null.
	     */  
	    public static String tirerUnMot (int niveauDifficulte) {
	
	        if (niveauDifficulte == 1)
	            return MOTS_1[tirerUnNombre(0, MOTS_1.length - 1)];
	
	        else if (niveauDifficulte == 2)
	            return MOTS_2[tirerUnNombre(0, MOTS_2.length - 1)];
	
	        else if (niveauDifficulte == 3)
	            return MOTS_3[tirerUnNombre(0, MOTS_3.length - 1)];
	
	        else
	            return null;
	    }
	    
		
		/**
	     * Cette methode retourne un nombre entier tire au hasard entre min et max
	     * inclusivement.
	     * @param min, la borne minimale du nombre a tirer
	     * @param max, la borne maximale du nombre a tirer
	     * @return un entier tire au hasard entre min et max inclusivement.
	     */
	    public static int tirerUnNombre(int min, int max) {
	        return (int) (Math.random() * (max - min) + min + 0.5);
	    }
	
	    
		static Scanner kb = new Scanner(System.in);
		
	    public final static int MAX_ERREUR=6; //constante globale maximum erreur permis

	    public static void presenterLogiciel() {
	        System.out.println("Bienvenue au jeu pendu");
	        System.out.println("Appuyer sur <ENTREE> pour continuer...");
	        pause(); //appel la méthode pause()
	        menu(); //appel la méthode menu()
	    }

	    public static void pause() {
	    	kb.nextLine();  
	    }

	    public static void menu() {
	        System.out.println("----------\nMENU\n----------");
	        System.out.println("\n1. Jouer \n2. Quitter \nEntrer votre choix: \n\n"  );
	    }

	    /**
	     * Valide un option entre 1 et 2  puis retourne l'option saisi et valide.
	     * @return un option valide
	     */
	    public static int validerOption() {
	        int opt= ' ';
	        System.out.print("Choisissez l'option 1 ou l'option 2: ");
	        opt=kb.nextInt();
	        if (opt != 1 && opt != 2) {
	            while (opt != 1 && opt != 2) {
	                System.out.println("Option est invalide, veuillez choisir 1 ou 2");
	                System.out.print("Choisissez l'option 1 ou l'option 2: ");
	                opt=kb.nextInt();
	            }
	        }else if (opt==2) {
	            finProgramme(); 
	        } 
	        return opt;
	    }

	    /**
	     * Valide un niveau entre 1 et 3  puis retourne un niveau saisi et valide.
	     * @return un niveau valide
	     */
	    public static int validerNiveau() {
	        int niv= 0;
	        System.out.print("Choisissez le niveau de difficulté entre 1 à 3: ");
	        niv=kb.nextInt();
	        while (niv !=1 && niv !=2 && niv !=3) {
	            System.out.println("Niveau invalide, veuillez re-entrer le niveau de difficulté");
	            System.out.print("Choisissez le niveau de difficulté entre 1 à 3: ");
	            niv=kb.nextInt();
	        }
	        return niv;
	    }

	    /**
	     * Valide le nom entre 3 charactere et 10 charactere et doit 
	     * contenir que des lettres entre a et z puis retourne un niveau saisi et valide.
	     * @return un niveau valide
	     */
	    public static String validerNom() {
	        String nom=" ";
	        System.out.print("Entrez votre nom: ");
	        nom=kb.nextLine();
	        int i=0;  
	        while ((nom.length() < 3 || nom.length() > 10) && ( nom.charAt(i) >= 'a' && nom.charAt(i) <= 'z')) {
	            System.out.println("Nom invalide, veuillez re-inscrire de nouveau");
	            System.out.println("Veuillez inscrire votre nom, 3 lettres min et 10 max");
	            nom=kb.nextLine();
	        }            

	        return nom;
	    }

	    /**
	     * Appelle la méthode validerNom() pour mettre le nom en majuscule
	     * @return le nom en majuscule
	     */
	    public static String nomMajuscule(){
	        String nomMajuscule = validerNom();
	        String nomMaj=nomMajuscule.toUpperCase();
	        return nomMaj;
	    }
	    
	    /**
	     * Valide la lettre entre 3 charactere et 10 charactere et doit 
	     * contenir puis retourne un niveau saisi et valide.
	     * @return une lettre valide
	     */	
	    public static char lettreSaisir(){
	        char lettreSaisi=' ';
	        lettreSaisi=(char) kb.nextInt();
	        if (!(lettreSaisi >= 'a' && lettreSaisi <= 'z') || !(lettreSaisi >= 'A' && lettreSaisi <= 'Z' )) {
	            System.out.println("Lettre invalide, veuillez re-entrer une autre lettre");
	            lettreSaisi=(char) kb.nextInt();
	        }
	        return lettreSaisi;
	    }

	    /**
	     * Verifie le nombre de tiret contenu dans le mot cache.
	     * @return un resultat valide
	     */	
	    public static String initialiseMotTemporaire(String mot){
	        String resultat = " ";
	        for (int i=0; i<mot.length(); i++){  
	            resultat = resultat + "_ ";
	        }
	        return resultat;
	    }

	    /**
	     * Affiche l'echafaud
	     */	
	    public static void affichePendu() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    " +
	            "\n|" +
	            "\n|" + 
	            "\n|" +
	            "\n===");
	    } 

	    /**
	     * Affiche premier erreur
	     */
	    public static void erreur1() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|" +
	            "\n|" + 
	            "\n|" +
	            "\n===");
	    }   

	    /**
	     * Affiche deuxieme erreur
	     */
	    public static void erreur2() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|    |" +
	            "\n|" + 
	            "\n|" +
	            "\n===");
	    }

	    /**
	     * Affiche troisieme erreur
	     */
	    public static void erreur3() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|   /|" +
	            "\n|" + 
	            "\n|" +
	            "\n===");
	    }   

	    /**
	     * Affiche quatrieme erreur
	     */
	    public static void erreur4() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|   /|\\" +
	            "\n|" + 
	            "\n|" +
	            "\n===");
	    }

	    /**
	     * Affiche cinquieme erreur
	     */
	    public static void erreur5() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|   /|\\" +
	            "\n|   /" + 
	            "\n|" +
	            "\n===");
	    }

	    /**
	     * Affiche sixieme erreur
	     */
	    public static void erreur6() {
	        System.out.println( "______" +
	            "\n|    |" +
	            "\n|    0" +
	            "\n|   /|\\" +
	            "\n|   / \\" + 
	            "\n|" +
	            "\n===");
	    }

	    /**
	     * Indique le nombre d'erreur et 
	     * affiche le bonhomme pendu selon le nombre d'erreur
	     * @return la somme d'erreur valide
	     */
	    public static int compteurErreur(int nbErreur){
	        int compteurErreur=0;
	        switch(nbErreur) {
	            case 1: erreur1();
	            break;
	            case 2: erreur2();
	            break;
	            case 3: erreur3();
	            break;
	            case 4: erreur4();
	            break;
	            case 5: erreur5();
	            break;
	            case 6: erreur6();
	            break;
	        }
	        return compteurErreur;
	    }
	    
	    /**
	     * Indique le nombre d'erreur et 
	     * affiche le bonhomme pendu selon le nombre d'erreur
	     * @return la somme d'erreur valide
	     */
	    public static String valideReponse(){
	        String rep = " ";
	        System.out.print("Voulez-vous jouer encore? Oui(o)/Non(n): ");
	        rep=kb.nextLine();
	        do {
	            if(rep.toLowerCase().equals("o")) {
	                affichePendu();
	            }else if (rep.toLowerCase().equals("n")){
	                finProgramme();
	            }
	        }while( rep.toLowerCase().equals("o") && rep.toLowerCase().equals("n"));
	        return rep;	
	    }

	    public static void statistiques(){
	        System.out.println("STATISTIQUES POUR" + nomMajuscule());
	        System.out.println("------------------------------------");
	        System.out.println("Nombres de parties jouees: ");
	        System.out.println("Nombres de parties gagnees: ");
	        System.out.println("------------------------------------");
	        System.out.println("Score final:  ");      
	    }    

	    /**
	    public static int partiesJouees(){

	    }

	    public static int partiesGagnees()
	    }

	    public static double scoreFinal(){

	    }
	     */
	    public static void finProgramme() {
	        System.out.println("Merci, Au Revoir");
	    }

	    public static void main (String [] args) {
	        presenterLogiciel();
	        int option = validerOption();
	        char lettre = ' ';
	        int erreur=0;

	        if(option=='1'){
	            int nivDiff = validerNiveau();
	            String mot = tirerUnMot(nivDiff);
	            String motTemp = initialiseMotTemporaire(mot);
	            affichePendu();
	            System.out.println(nomMajuscule()+" Le mot cache: " + motTemp);  
	            while (erreur<MAX_ERREUR) {
	                //String motTemp = initialiseMotTemporaire(mot);
	                lettre=(char)kb.nextInt();                
	                lettre=Character.toUpperCase(lettre);
	                if (lettre==mot.charAt(0)) {
	                    for (int i=0; i<mot.length(); i++){
	                        motTemp = motTemp.substring(0, 1) + mot.charAt(i) + motTemp.substring((i+1));
	                        System.out.println(motTemp);
	                    }
	                } else if (lettre!=mot.charAt(0)){
	                    System.out.println("Desole... Il n'y a pas de lettre(s) " + lettre + " dans ce mot.");
	                    erreur++;
	                    compteurErreur(erreur);
	                }else if(lettre==motTemp.charAt(0)){
	                    System.out.println("La lettre " + lettre + " est deja decouverte... Recommencez!");
	                }	
	                if(erreur==MAX_ERREUR){
	                    System.out.println("Vous etes mort!!! \nLe mot cache etait : " + mot);
	                    System.out.println(valideReponse());	
	                }	
	            }
	        }else if (option=='2') {
	            finProgramme();
	            statistiques();
	        } 

	    }
}
