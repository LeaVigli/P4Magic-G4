package model;

/**
 *
 * @author p1517348
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DisappearColumnEffectTest {
    
    static Game aGame;
    
    public DisappearColumnEffectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testDisappearColumnEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - la colonne dans laquelle le pion est joué est vide
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        for(int i=2; i>=0;i--) {
            assertEquals(-1, aGame.getBoard().getTileIJ(height-1-i, 0).getStatus());
        }
        
    }

    /**
     * Test de DisappearColumnEffect sur grille vide 
     * Vérification de l'état de la
     * tuile après application de l'effet 
     * Résultats attendus : la case doit être
     * vide, le tour de jeu doit être passé
     */
    @Test
    public void testDisappearColumnEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - le début de la colonne est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        for(int i=2; i>=0;i--) {
            assertEquals(-1, aGame.getBoard().getTileIJ(height-1-i, 0).getStatus());
        }
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

    /**
     * Test de DisappearColumnEffect sur grille vide 
     * Vérification du nombre de jetons
     * après jeu 
     * Résultat attendu : le nombre doit être égal à 0
     */
    @Test
    public void testDisappearColumnEffectEmptyGameWithTilesNumber() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérification que le nombre de jetons au total est égal à 0  
        assertEquals(0, aGame.getBoard().getTotalTilesCount());

    }

    /**
     * Test de DisappearColumnEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case doit
     * être vide, l'effet doit être sur la case 
     * et le tour doit être passé
     */
    @Test
    public void testDisappearColumnEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérifications :
        // - le début de la colonne jouée est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        for(int i=2; i>=0;i--) {
            assertEquals(-1, aGame.getBoard().getTileIJ(height-1-i, 0).getStatus());
        }
        assertTrue("Doit être d'effet disappear column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
}
