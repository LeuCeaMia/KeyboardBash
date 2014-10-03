
import com.golden.gamedev.Game;
import java.awt.*;
import java.awt.event.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class GameUI extends Game{

    boolean[] gameFlags, keyPressed;
    String[] menuLabels, optionLabels;
    String gameName;
    int index;
    int speed;
    int lvsel;
    final int MARVELOUS = 5, PERFECT = 4, GREAT = 3, GOOD = 1, MISS = 0;
    final int MAX_NOTES = 16;//has to be divisible by 4
    final int NOTE_SPAWN_Y = -10;
    final int dimension = 60;
    int notecount; 
    int totalJudgeCount, marvelousCount, perfectCount, greatCount, goodCount, missCount;
    double accuracy;
    Key RECEPTOR_LEFT, RECEPTOR_DOWN, RECEPTOR_UP, RECEPTOR_RIGHT; // up
    Key PRESSED_LEFT, PRESSED_DOWN, PRESSED_UP, PRESSED_RIGHT; // pressed
    Key NOTE_LEFT, NOTE_DOWN, NOTE_UP, NOTE_RIGHT; // notes
    
    int fi,nNotes;
    Key[] notes = new Key[MAX_NOTES];
    char[] noteTypes = new char[MAX_NOTES];
    double noteSpeed;
    double[] noteSpeeds = new double[MAX_NOTES];
    
    
    @Override
    public void initResources() {
        noteSpeed = 0.25;
        index = 0;
        speed = 1;
        lvsel = 0;
        nNotes = MAX_NOTES;
        gameFlags = new boolean[5];
        gameFlags[0] = true; // main menu scr
        gameFlags[1] = false; // game proper
        gameFlags[2] = false; // game over
        gameFlags[3] = false; // game options
        gameFlags[4] = false; // game help
        gameName = "Deutsche Dance Republic"; // game title
        menuLabels = new String[4];
        menuLabels[0] = "Play Game";
        menuLabels[1] = "Options";
        menuLabels[2] = "Game Help";
        menuLabels[3] = "Exit";
        keyPressed = new boolean[4];
        keyPressed[0] = false; // left
        keyPressed[1] = false; // down
        keyPressed[2] = false; // up
        keyPressed[3] = false; // right
        optionLabels = new String[2];
        optionLabels[0] = "Speed";
        optionLabels[1] = "Return to Main Menu";
        RECEPTOR_LEFT = new Key(getImage("ddrLEFT.png"), 200, 400);
        RECEPTOR_DOWN = new Key(getImage("ddrDOWN.png"), RECEPTOR_LEFT.getX() + 
                RECEPTOR_LEFT.getWidth(), RECEPTOR_LEFT.getY());
        RECEPTOR_UP = new Key(getImage("ddrUP.png"), RECEPTOR_DOWN.getX() + 
                RECEPTOR_DOWN.getWidth(), RECEPTOR_DOWN.getY());
        RECEPTOR_RIGHT = new Key(getImage("ddrRIGHT.png"), RECEPTOR_UP.getX() + 
                RECEPTOR_UP.getWidth(), RECEPTOR_UP.getY());
        PRESSED_LEFT = new Key(getImage("ddrLEFT_pressed.png"), 200, 400);
        PRESSED_DOWN = new Key(getImage("ddrDOWN_pressed.png"), PRESSED_LEFT.getX() + 
                PRESSED_LEFT.getWidth(), PRESSED_LEFT.getY());
        PRESSED_UP = new Key(getImage("ddrUP_pressed.png"), PRESSED_DOWN.getX() + 
                PRESSED_DOWN.getWidth(), PRESSED_DOWN.getY());
        PRESSED_RIGHT = new Key(getImage("ddrRIGHT_pressed.png"), PRESSED_UP.getX() + 
                PRESSED_UP.getWidth(), PRESSED_UP.getY());
        NOTE_LEFT = new Key(getImage("ddrLEFT_note.png"), 200, 0);
        NOTE_DOWN = new Key(getImage("ddrDOWN_note.png"), NOTE_LEFT.getX() + 
                NOTE_LEFT.getWidth(), 0);
        NOTE_UP = new Key(getImage("ddrUP_note.png"), NOTE_DOWN.getX() + 
                NOTE_DOWN.getWidth(), 0);
        NOTE_RIGHT = new Key(getImage("ddrRIGHT_note.png"), NOTE_UP.getX() + 
                NOTE_UP.getWidth(), 0);
        notecount = 0;
        iniNotes();
    }
    private void iniNotes(){
        int d;
        d = MAX_NOTES/4;
        for(fi = 0; fi<d;fi++){
            notes[fi] =  new Key(getImage("ddrUP.png"), RECEPTOR_DOWN.getX() + 
                RECEPTOR_DOWN.getWidth(), NOTE_SPAWN_Y);
            noteTypes[fi] = 'U';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d; fi<d*2;fi++){
            notes[fi] =  new Key(getImage("ddrDOWN.png"), RECEPTOR_LEFT.getX() + 
                RECEPTOR_LEFT.getWidth(), NOTE_SPAWN_Y);
            noteTypes[fi] = 'D';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d*2; fi<d*3;fi++){
            notes[fi] =  new Key(getImage("ddrLEFT.png"), 200, NOTE_SPAWN_Y);
            noteTypes[fi] = 'L';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d*3; fi<d*4;fi++){
            notes[fi] =  new Key(getImage("ddrRIGHT.png"), RECEPTOR_UP.getX() + 
                RECEPTOR_UP.getWidth(), NOTE_SPAWN_Y);
            noteTypes[fi] = 'R';
            noteSpeeds[fi] = (Math.random());
        }
    }
    private void changeNotes(){
        int d;
        d = nNotes/4;
        for(fi = 0; fi<d;fi++){
            notes[fi].setImage(getImage("ddrUP.png"));
            notes[fi].setX(RECEPTOR_DOWN.getX()+RECEPTOR_DOWN.getWidth());
            notes[fi].setY(NOTE_SPAWN_Y);
            noteTypes[fi] = 'U';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d; fi<d*2;fi++){
            notes[fi].setImage(getImage("ddrDOWN.png"));
            notes[fi].setX(RECEPTOR_LEFT.getX() + RECEPTOR_LEFT.getWidth());
            notes[fi].setY(NOTE_SPAWN_Y);
            noteTypes[fi] = 'D';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d*2; fi<d*3;fi++){
            notes[fi].setImage(getImage("ddrLEFT.png"));
            notes[fi].setX(200);
            notes[fi].setY(NOTE_SPAWN_Y);
            noteTypes[fi] = 'L';
            noteSpeeds[fi] = (Math.random());
        }
        for(fi = d*3; fi<d*4;fi++){
            notes[fi].setImage(getImage("ddrRIGHT.png"));
            notes[fi].setX(RECEPTOR_UP.getX() + RECEPTOR_UP.getWidth());
            notes[fi].setY(NOTE_SPAWN_Y);
            noteTypes[fi] = 'R';
            noteSpeeds[fi] = (Math.random());
        }
    }
    private void collisionCheck(char direction){
        //Check for Zone Balloon Collision
        double[] accuracy = new double[MAX_NOTES];
        for(fi=0;fi<nNotes;fi++)
        {   
            accuracy[fi] = (Math.abs(RECEPTOR_LEFT.getY() - notes[fi].getY()));
            if(Math.abs(RECEPTOR_LEFT.getY() - notes[fi].getY())< dimension && noteTypes[fi]==direction)
            {
                resetNote(fi);
                marvelousCount++;
                perfectCount++;
                greatCount++;
                goodCount++;
                missCount++;
            }    
        }
    }
    private void moveNotes(){
        for(fi=0;fi<nNotes;fi++){
            if(notes[fi].getY()<this.getHeight())
                notes[fi].setY(notes[fi].getY()+noteSpeeds[fi]);
            else
                resetNote(fi);
        }
    }
    private void resetNote(int i){
        notes[i].setY(0);
    }
    public void readInput(){
        // main menu
        if(gameFlags[0]){
            // look for user input
            if (keyPressed(KeyEvent.VK_DOWN) && index < menuLabels.length - 1){
                index++;
            }
            else if (keyPressed(KeyEvent.VK_UP) && index > 0){
                index--;
            }
            else if (keyPressed(KeyEvent.VK_ENTER)){
                switch(index){
                    case 0: gameFlags[0] = false; gameFlags[1] = true; break;
                    case 1: index = 0; gameFlags[0] = false; gameFlags[3] = true; break;
                    case 2: gameFlags[0] = false; gameFlags[4] = true; break;
                    case 3: finish(); break;
                }
            }
            else if (keyPressed(KeyEvent.VK_ESCAPE)){
                finish();
            }
        }
        else if (gameFlags[1]){
            // game proper
            if (keyDown(KeyEvent.VK_LEFT)){
                collisionCheck('L');
                keyPressed[0] = true;
            }
            else {
                keyPressed[0] = false;
            }
            if (keyDown(KeyEvent.VK_DOWN)){
                collisionCheck('D');
                keyPressed[1] = true;
            }
            else {
                keyPressed[1] = false;
            }
            if (keyDown(KeyEvent.VK_UP)){
                collisionCheck('U');
                keyPressed[2] = true;
            }
            else {
                keyPressed[2] = false;
            }
            if (keyDown(KeyEvent.VK_RIGHT)){
                collisionCheck('R');
                keyPressed[3] = true;
            }
            else {
                keyPressed[3] = false;
            }
            if (keyPressed(KeyEvent.VK_ESCAPE)){
                // discard changes
                gameFlags[0] = true;
                gameFlags[1] = false;
            }
        }
        else if (gameFlags[2]){
            // game over
            if (keyPressed(KeyEvent.VK_ENTER)){
                // play again
                gameFlags[1] = true;
                gameFlags[2] = false;
            }
            else if (keyPressed(KeyEvent.VK_ESCAPE)){
                // go back to main menu
                gameFlags[0] = true;
                gameFlags[2] = false;
            }
        }
        else if (gameFlags[3]){
            // game options
            if (keyPressed(KeyEvent.VK_DOWN) && index < optionLabels.length - 1){
                index++;
            }
            else if (keyPressed(KeyEvent.VK_UP) && index > 0){
                index--;
            }
            
            else if (keyPressed(KeyEvent.VK_ENTER)){
                switch(index){
                    case 1: // save changes
                            gameFlags[0] = true; gameFlags[3] = false; break;
                }
            }
            else if (keyPressed(KeyEvent.VK_ESCAPE)){
                // discard changes
                gameFlags[0] = true;
                gameFlags[3] = false;
            }
        }
        else if (gameFlags[4]){
            // help
            if (keyPressed(KeyEvent.VK_ESCAPE)){
                gameFlags[0] = true;
                gameFlags[4] = false;
            }
        }
    }
    
    @Override
    public void update(long l) {
        moveNotes();
        for(fi=0;fi<nNotes;fi++){
            notes[fi].update(l);
        }
        readInput();
    }

    @Override
    public void render(Graphics2D gd) {
        if(gameFlags[0]){
            // main menu
            gd.setColor(Color.black);
            gd.fillRect(0, 0, getWidth(), getHeight());
            gd.setColor(Color.white);
            gd.drawString(gameName, 290, 60);
            if(index == 0){
                gd.setColor(Color.blue);
                gd.fillRect(200, 105, 80, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(menuLabels[0], 200, 120);
            if(index == 1){
                gd.setColor(Color.blue);
                gd.fillRect(200, 125, 80, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(menuLabels[1], 200, 140);
            if(index == 2){
                gd.setColor(Color.blue);
                gd.fillRect(200, 145, 80, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(menuLabels[2], 200, 160);
            if(index == 3){
                gd.setColor(Color.blue);
                gd.fillRect(200, 165, 80, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(menuLabels[3], 200, 180);
        }
        else if(gameFlags[1]){
            // game proper
            gd.setColor(Color.black);
            gd.fillRect(0, 0, getWidth(), getHeight());
            gd.setColor(Color.white);
            if(keyPressed[0]){
                PRESSED_LEFT.render(gd);
            }
            else {
                RECEPTOR_LEFT.render(gd);
            }
            if(keyPressed[1]){
                PRESSED_DOWN.render(gd);
            }
            else {
                RECEPTOR_DOWN.render(gd);
            }
            if(keyPressed[2]){
                PRESSED_UP.render(gd);
            }
            else {
                RECEPTOR_UP.render(gd);
            }
            if(keyPressed[3]){
                PRESSED_RIGHT.render(gd);
            }
            else {
                RECEPTOR_RIGHT.render(gd);
            }
            for(fi=0;fi<nNotes;fi++){
                notes[fi].render(gd);
            }
            //gd.drawString("Accuracy: " + (totalJudgeCount / (notecount * MARVELOUS)), 0, 0);
            //gd.drawString("under construction, please check back later", 200, 225);
            //gd.drawString("Press escape key to go back to the main menu.", 200, 245);
        }
        else if(gameFlags[2]){
            // game over
            gd.setColor(Color.black);
            gd.fillRect(0, 0, getWidth(), getHeight());
            gd.setColor(Color.white);
            gd.drawString("under construction, please check back later", 200, 225);
            gd.drawString("Press enter key to play again.", 220, 245);
            gd.drawString("Press escape key to go back to the main menu.", 200, 265);
        }
        else if(gameFlags[3]){
            // options
            gd.setColor(Color.black);
            gd.fillRect(0, 0, getWidth(), getHeight());
            gd.setColor(Color.white);
            gd.drawString("Game Options", 290, 60);
            if(index == 0){
                gd.setColor(Color.blue);
                gd.fillRect(100, 85, 400, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(optionLabels[0], 100, 100);
            gd.drawString(Integer.toString(speed), 500, 100);
            if(index == 1){
                gd.setColor(Color.blue);
                gd.fillRect(100, 435, 400, 15);
                gd.setColor(Color.white);
            }
            gd.drawString(optionLabels[1], 100, 450);
        }
        else if(gameFlags[4]){
            // help
            gd.setColor(Color.black);
            gd.fillRect(0, 0, getWidth(), getHeight());
            gd.setColor(Color.white);
            gd.drawString("Hit the key accordingly with the directional buttons.", 200, 225);
            gd.drawString("Press escape key to go back to the main menu.", 200, 245);
        }
    }
    
}
