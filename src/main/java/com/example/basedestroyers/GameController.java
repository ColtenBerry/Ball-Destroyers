package com.example.basedestroyers;

import com.example.basedestroyers.AI.SimpleComputerMove;
import com.example.basedestroyers.Core.Base;
import com.example.basedestroyers.Core.Soldier;
import com.example.basedestroyers.Core.Timer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

    /*
    maybe have like a setNumSpawns integer and whenever one soldier is free from the base, the soldier spawn can be called
    again as long as the setNumSpawns is greater than 0 and garrison is > 0.
        - I implemented a clock and simply staggered the soldiers spawning. I did this till the garrison was 0.

    After that, I can implement garrison replenishment, as well as a maximum garrison number that should not be exceeded
    via garrison replenishment

    Following that, creating the AI will be the final step in making this a good, working game.
    I can figure out how to create maps, but that is obviously less important.
     */

/*
TODO:
    THURSDAY: First, I need to fix restarting a story level when the player loses. X
    THURSDAY: Next, I need to get the size of the screen and standardize everything according to that. Otherwise, I will simply
 have more work to do later. X?
    MAYBE: Finally, I need to set the AIs in the Level class and create different AIs for different levels?
    THURSDAY: I need to use layout simply for the random game mode. Otherwise, it is literally useless. Alternatively, I could
 could try and put random level as a level in the class level and simply do away with layout. The second option
 makes more sense I think X
    FRI/SAT: Finally, I need to make the AI better. It is fun right now, but I think that it could be vastly improved
    LEGACY: Finally (I know ive said finally a lot), I can continue adding story levels until I feel like the story is good enough
 Then I will put it on Gighub, show dad, and then I will begin work on a new game.
 I should store all of my dimensions and stuff within TotalGame. The difficulty is getting that to work correctly
 GOAL: get all this done by the time spring break ends.
 */

public class GameController {
    private final TotalGame game = TotalGame.getGame();
    @FXML
    private Pane gamePane;
    @FXML
    private HBox bottom;
    @FXML
    private HBox top;
    @FXML
    private Label playerWins;
    @FXML
    private Label redWins;
    @FXML
    private Label greenWins;
    @FXML
    private Label yellowWins;
    private final Movement clock = new Movement();
    @FXML
    private void startClock() {
        clock.start();
    }
    @FXML
    private void stopClock() {
        clock.stop();
    }
    private final Timer timer = new Timer();
    private class Movement extends AnimationTimer {
        private final long FRAMES_PER_SEC = 50L;
        private final long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                try {
                    updateViews();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                timer.update();
                getComputerMove();
                try {
                    if (gameOver()) {
                        stopClock();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                last = now;
            }
            //if i put the following in the if, the game is slower. I think its supposed to be in there tho
        }
    }
    private boolean playing;
    private SimpleComputerMove red_computer_move;
    private SimpleComputerMove green_computer_move;
    private SimpleComputerMove yellow_computer_move;
    private StrengthBar strengthBar;
    @FXML
    private void initialize() throws FileNotFoundException {
        //gamePane.setStyle("-fx-background-color: green;");
        //creates layout

        if (game.isGameStory()) {
            curr_level = Level.MASSIVE;
        }
        else {
            curr_level = Level.RANDOM;
        }
        loadLayout();
        updateLabels();
        //organize base lists
        startGame();
    }
    private Label levelNumberLabel = new Label();
    private void updateLabels() {
        if (!game.isGameStory()) {
            playerWins.setText("Player Wins: " + Faction.PLAYER.getRoundsWon());
            redWins.setText("Red Wins: " + Faction.RED.getRoundsWon());
            greenWins.setText("Green Wins: " + Faction.GREEN.getRoundsWon());
            yellowWins.setText("Yellow Wins: " + Faction.YELLOW.getRoundsWon());
        }
        else {
            bottom.getChildren().clear();
        }
    }
    @FXML
    private Button next_level;
    private void startGame() {
        //add in AI
        red_computer_move = new SimpleComputerMove(Faction.RED, timer);
        green_computer_move = new SimpleComputerMove(Faction.GREEN, timer);
        yellow_computer_move = new SimpleComputerMove(Faction.YELLOW, timer);
        //remove next level button from bottom hBox
        bottom.getChildren().remove(next_level);
        startClock();
        playing = true;
    }
    private void loadLayout() throws FileNotFoundException {
        //clear base lists for each faction
        Faction.PLAYER.clearAllBaseLists();
        //clear gamePane
        gamePane.getChildren().clear();
        //clears the top hbox - should just remove the strengthbar
        top.getChildren().clear();
        //creates the new base list
        game.getBases().clear();
        curr_level.setBaseList();
        //refills the faction lists
        for (Base b: game.getBases()) {
            Faction f = b.getFaction();
            f.getBaseList().add(b);
        }

        //add bases to pane
        for (Base b: game.getBases()) {
            gamePane.getChildren().add(b);
        }
        if (curr_level != null && curr_level.getObjectsList() != null) {
            curr_level.getObjectsList().clear();
            curr_level.setObjectsList();
            for (Node o: curr_level.getObjectsList()) {
                gamePane.getChildren().add(o);
            }
        }
        //re adds the strengthbar
        strengthBar = new StrengthBar(0, 0, 20, 200);
        top.getChildren().add(strengthBar);
    }
    private Faction winner;
    private boolean gameOver() throws FileNotFoundException {
        ArrayList<Faction> alive_factions = new ArrayList<>();
        for (Base b: game.getBases()) {
            if (!alive_factions.contains(b.getFaction()) && b.getFaction() != Faction.NEUTRAL) {
                alive_factions.add(b.getFaction());
                //I am altering the game so that the game works with more than two factions
            }
            if (alive_factions.size() >= 2) {
                return false;
            }
        }
        winner = game.getBases().get(0).getFaction();
        updateViews();
        if (!game.isGameStory()) {
            winner.incrementRoundsWon();
        }
        updateLabels();
        int bottom_size = bottom.getChildren().size();
        bottom.getChildren().add(bottom_size / 2, next_level);
        return true;
    }
    private Level curr_level;
    //loads the next level. initiates loadlayout.
    //TODO: fix if the player loses the level
    @FXML
    private void nextLevelButton() throws FileNotFoundException {
        if (game.isGameStory()) {
            if(winner != Faction.PLAYER) {
                game.getBases().clear();
                next_level.setText("Restart");
            }
            else if (curr_level.getNextLevel() != null) {
                next_level.setText("Next Level");
                curr_level = curr_level.getNextLevel();
            }
            else {
                game.setStory(false);
                curr_level = Level.RANDOM;
                //add in the winner labels if the game is no longer in story mode
                bottom.getChildren().add(playerWins);
                bottom.getChildren().add(redWins);
                bottom.getChildren().add(greenWins);
                bottom.getChildren().add(yellowWins);
                updateLabels();
            }
        }
        loadLayout();
        startGame();
    }
    private Base selected;
    private Base target;
    private boolean paused = false;
    private void pause() {
        if (!paused) {
            stopClock();
            paused = true;
        }
        else {
            clock.start();
            paused = false;
        }
    }
    private boolean baseEditor = false;
    private int count = 0;
    @FXML
    public void detectKey(KeyEvent e) {
        System.out.println("detect");
        if (e.getCode().equals(KeyCode.K)) {
            System.out.println("k");
            count += 1;
            if (count == 3) {
                baseEditor = true;
                game.getBases().clear();
                game.getSoldiers().clear();
                gamePane.getChildren().clear();
            }
        }
        else {
            count = 0;
        }
        if (e.getCode().equals(KeyCode.ESCAPE)) {
            pause();
            System.out.println("pause");
        }
    }

    @FXML
    private void click(MouseEvent e) throws FileNotFoundException {
//        System.out.println("(" + e.getX() + ", " + e.getY() + ")");
        double x = e.getX();
        double y = e.getY();
        if (baseEditor) {
            System.out.println(x);
            System.out.println(y);
            Base b = new Base(new Point2D(x, y), Faction.PLAYER, 10);
            game.getBases().add(b);
            gamePane.getChildren().add(b);
        }
        //select (first click)
        if (selected == null) {
            selected = selectBase(x, y, true);
            if (selected != null) {
                selected.flipSelect();
            }
//            System.out.println("Selected: " + selected);
        }
        //target (second click)
        else if (target == null) {
            target = selectBase(x, y, false);
            if (target == null) {
                selected.flipSelect();
                selected = null;
            }
//            System.out.println("Target: " + target);
        }
        if (target == selected) {
            target = null;
        }
        else if (target != null && selected != null) {
//            System.out.println("Selected: " + selected);
//            System.out.println("Target: " + target);
            selected.setTarget(target);
//            finishSpawningSoldier(move.spawnColumn());
            selected.flipSelect();
            selected = null;
            target = null;
        }
    }
    private Base selectBase(double x, double y, boolean k) {
        Point2D mouse = new Point2D(x, y);
        if (k) {
            for (Base b : Faction.PLAYER.getBaseList()) {
                if (b.getPoint().distance(mouse) < b.getSize())
                    return b;
            }
        } else {
            for (Base b : game.getBases()) {
                if (b.getPoint().distance(mouse) < b.getSize())
                    return b;
            }
        }
        return null;
    }
    private void shiftBaseList(Base targetBase, Faction homefaction) {
        Faction targetBaseFaction = targetBase.getFaction();
        targetBaseFaction.getBaseList().remove(targetBase);
        targetBase.setFaction(homefaction);
        homefaction.getBaseList().add(targetBase);
//        red_computer_move.updateLists(targetBase, targetBaseFaction);
//        yellow_computer_move.updateLists(targetBase, targetBaseFaction);
//        green_computer_move.updateLists(targetBase, targetBaseFaction);
    }
//    ArrayList<Integer> numbers = new ArrayList<>();
//    Iterator<Integer> numIt = numbers.iterator();
//    public void blah() {
//        numbers.add(20);
//        numbers.add(3);
//        numbers.add(7);
//        numbers.add(12);
//        while (numIt.hasNext()) {
//            Integer i = numIt.next();
//            if (i < 10) {
//                it.remove();
//            }
//        }
//        System.out.println(numbers);
//    }
    private void finishSpawningSoldier(Soldier s) {
            game.getSoldiers().add(s);
            gamePane.getChildren().add(s);
            s.getAllegiance().getSoldierList().add(s);
    }
    private void updateViews() throws FileNotFoundException {
        strengthBar.update();
        //spawns soldiers
        for (Base b: game.getBases()) {
            b.update();
            if (b.getFaction() != Faction.NEUTRAL && !b.isSpawning()) {
                b.replenish(timer);
            }
            else if (b.isSpawning() && b.checkGarrison()) {
                Soldier s = b.spawnSoldiers(timer);
                if (s != null) {
                    finishSpawningSoldier(s);
                }
            }
        }
        //moves soldiers
        for (Soldier s: game.getSoldiers()) {
                s.move();
        }
        //deletes soldiers when they meet bases
        for (Soldier s: game.getSoldiers()) {
            if (s != null) {
                if (s.getCompleted()) {
                    //if base was conquered
                    if (s.getConquered()) {
                        shiftBaseList(s.getTarget(), s.getAllegiance());
                    }
                    markedForDeletion.add(s);
                    s.getAllegiance().getSoldierList().remove(s);
                    //I am unsure why this is needed, if not then phantom soldiers seem to spawn and reinforce garrisons
                    s.setPoint(new Point2D(-100, -100));
                }
            }
        }
        // deletes soldiers when they collide
        for (Soldier s: game.getSoldiers()) {
            for (Soldier ss: game.getSoldiers()) {
                if (s != null && ss != null && !s.getCollision() && !ss.getCollision()) {
                    Point2D s_point = s.getPoint();
                    Point2D ss_point = ss.getPoint();
                    Faction s_faction = s.getAllegiance();
                    Faction ss_faction = ss.getAllegiance();
                    if (s_faction != ss_faction && s_point.distance(ss_point) < s.getSize()) {
                        s.setCollision();
                        ss.setCollision();
                        markedForDeletion.add(s);
                        markedForDeletion.add(ss);
                        s.getAllegiance().getSoldierList().remove(s);
                        ss.getAllegiance().getSoldierList().remove(ss);
                    }
                }

            }
        }
//        for (Soldier s: friendlySoldiers) {
//            for (Soldier enemy: enemySoldiers) {
//                if (s.getPoint().distance(enemy.getPoint()) < s.getRadius() + enemy.getRadius()) {
//                    s.setCollision();
//                    enemy.setCollision();
//                    markedForDeletion.add(s);
//                    markedForDeletion.add(enemy);
//                }
//            }
//        }
        game.getSoldiers().removeIf(Soldier::getCollision);
        game.getSoldiers().removeIf(Soldier::getCompleted);
//        moves.removeIf(Move::isSpawning);
//        System.out.println(moves);
        customDelete();
    }
    //this is needed because the gamepane children must be deleted when they are out of use
    //the custom delete and marked for deletion is the only way I could get the delete to actually work
    private ArrayList<Soldier> markedForDeletion = new ArrayList<>();
    private void customDelete() {
        gamePane.getChildren().removeAll(markedForDeletion);
        markedForDeletion.removeIf(Soldier::getCompleted);
    }
    double compInitialTime = 0.0;
    //It may be better to make getComputerMove in the Faction Enum itself...
    private void getComputerMove() {
        red_computer_move.makeMove();
        green_computer_move.makeMove();
        yellow_computer_move.makeMove();
            /*
            so makemove isn't bad, but it just results in a delay in the best move being made.
            Instead, it should roll for a move, then roll to see if the move should be made?
            roll to attack based on distance, strength, power levels, etc?
             */
    }
}


/*
At this point, the game is basically working as intended. Final List of stuff to do:
    - Cleanup code
    - Improve computer (perhaps randomize moves)
    - Randomize starts (perhaps I could create certain presets to start in)
        - would presets better if made in scenebuilder?. No, i cant spawn bases in scenebuilder
    - Make it so it's an actual game (start/win screen)
    - Ensure that the player can only control the friendly bases.
    - Strength bar
 */