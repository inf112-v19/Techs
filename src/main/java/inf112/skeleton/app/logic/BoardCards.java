package inf112.skeleton.app.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.GameController;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.ProgramCard;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.screens.Board;

import java.util.ArrayList;

public class BoardCards extends Board {

    private GameController gameController;

    //Card handling
    private ProgramCardDeck deck = new ProgramCardDeck();
    // Contains all cards that are possible to select
    private ArrayList<IProgramCard> cardsToSelect;
    // Contains all cards that are selected
    private ArrayList<IProgramCard> selectedCards;
    // Contains the keys you have to press in order to choose cards
    private ArrayList<Integer> keysForChoosingCards;

    private final int CARD_WIDTH = 94;
    private final int CARD_HEIGHT = 130;
    private final int NUMBER_WIDTH = 35;
    private final int NUMBER_HEIGHT = 35;
    
    private int handSize;
    private int centerOfScreen;
    private int numberOfCardsSelected = 0;
    private int numberOfLockedRegisters;
    private boolean movingPlayers = false;
    private boolean givenCardsToPlayer;

    private final int DAMAGE_WIDTH = 35;
    private final int DAMAGE_HEIGHT = 35;
    private final int HEALTH_WIDTH = 70;
    private final int HEALTH_HEIGHT = 70;

    // The X and Y-value of each damage token
    private final int DAMAGE_X = 1245;

    // The X and Y-value of each health
    private final int HEALTH_X = 1175;

    private TextureAtlas atlasCards;
    private SpriteBatch spriteBatchCards;
    private ArrayList<Sprite> cardsToSelectSprite;
    private ArrayList<Integer> cardsPositionOnScreen;
    private ArrayList<Integer> damageTokensOnScreen;
    private ArrayList<Integer> healthTokensOnScreen;

    private Texture number1;
    private Texture number2;
    private Texture number3;
    private Texture number4;
    private Texture number5;

    // Sprites regarding health and damage tokens
    private Texture activeHealth;
    private Texture deactiveHealth;
    private Texture activeDamage;
    private Texture deactiveDamage;

    //shows order of selected cards
    private ArrayList<Integer> numberXPos;
    private ArrayList<Integer> numberYPos;

    private boolean allPlayersDonePickingCards = false;
    private boolean finishedRound = false;

    public BoardCards(RoboRally game, int numPlayers) {
        super(game);
        gameController = new GameController(numPlayers, this);
        atlasCards = new TextureAtlas("assets/ProgramSheet/ProgramCardsTexturePack/cardsTexture.atlas");
        spriteBatchCards = new SpriteBatch();
        number1 = new Texture("assets/ProgramSheet/numbersInCircle/numberOne.png");
        number2 = new Texture("assets/ProgramSheet/numbersInCircle/numberTwo.png");
        number3 = new Texture("assets/ProgramSheet/numbersInCircle/numberThree.png");
        number4 = new Texture("assets/ProgramSheet/numbersInCircle/numberFour.png");
        number5 = new Texture("assets/ProgramSheet/numbersInCircle/numberFive.png");

        this.activeDamage = new Texture("assets/activeDamage.png");
        this.activeHealth = new Texture("assets/activeHealth.png");
        this.deactiveDamage = new Texture("assets/deactiveDamage.png");
        this.deactiveHealth = new Texture("assets/deactiveHealth.png");

        newTurn();
        
        // Creates and fills keysForChoosingCards with the correct numbers
        keysForChoosingCards = new ArrayList<Integer>();
        int keyOne = Input.Keys.NUM_1;
        for(int i = 0; i < 9; i++) {
            keysForChoosingCards.add(keyOne + i);
        }
    }

    @Override
    public void render(float v) {
        super.render(v);
        centerOfScreen = Gdx.graphics.getWidth() / 2;
        updateCardPositionOnScreen(centerOfScreen);
        getdamageTokenOnScreenLocation();
        getHealthTokensOnScreen();
        
        // If the player hasn't gotten cards yet, give cards
        if(!givenCardsToPlayer && !movingPlayers) {
            giveCardsToPlayer();
        }
        //shows 9 cards player can select
        spriteBatchCards.begin();

        for(int i = 0; i < handSize; i++) {
            spriteBatchCards.draw(cardsToSelectSprite.get(i), cardsPositionOnScreen.get(i), 0, CARD_WIDTH, CARD_HEIGHT);
        }
        
        //shows numbers for order of selected cards
        spriteBatchCards.draw(number1, numberXPos.get(0), numberYPos.get(0), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number2, numberXPos.get(1), numberYPos.get(1), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number3, numberXPos.get(2), numberYPos.get(2), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number4, numberXPos.get(3), numberYPos.get(3), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number5, numberXPos.get(4), numberYPos.get(4), NUMBER_WIDTH, NUMBER_HEIGHT);

        int damage = getDamageTokens(gameController.getCurrentPlayerByName());
        int health = getHealth(gameController.getCurrentPlayerByName());

        if (!this.movingPlayers) {
            drawTokensOnScreen(damage, health);
        }


        spriteBatchCards.end();

        if (!allPlayersDonePickingCards) {
            if (finishedRound) {
                processEndOfRound();
                finishedRound = false;
            }

            // If player presses P, enters powerdown
            if(Gdx.input.isKeyJustPressed(Keys.P)) {
            	doPowerdown(gameController.getCurrentPlayerByName()); 
            	while(selectedCards.size() < 5) {
            		selectedCards.add(new ProgramCard(CardType.MOVEMENT_0, 0, 0, 0));         		
            	}
            }
       
            if (selectedCards.size() < 5) {
                // if the players hasn't selected 5 cards yet, checks if player has selected any card
                for(int i = 0; i < handSize; i++) {
                    if ((Gdx.input.getX() > cardsPositionOnScreen.get(i) && 
                            Gdx.input.getX() < (cardsPositionOnScreen.get(i) + CARD_WIDTH) && 
                            Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() 
                            || Gdx.input.isKeyPressed(keysForChoosingCards.get(i))) {
                        selectCard(i, centerOfScreen, -1);
                    }
                }
                
            // if the players has selected 5 cards and presses Enter (or has started powerdown), ends this players turn
            } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || getPowerdownStatus(gameController.getCurrentPlayerByName())) {
                if (cardsToSelect.size() >= 5) {
                    gameController.setCardsThatWerePlayedInRegister(selectedCards);
                    gameController.donePickingCards(selectedCards, this);
                    newTurn();
                }
            }
        }
  
        else {
            this.movingPlayers = true;
            // If all players have selected cards, player can press SPACE to move a player
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                gameController.movePlayers(this);
                finishedRound = true;
            }
        }
    }
    
    private void selectCard(int cardToSelect, int centerOfScreen, int locationInRegister) {
        if (!selectedCards.contains(cardsToSelect.get(cardToSelect))) {
         
            if(locationInRegister != -1) {
                numberXPos.set(locationInRegister, centerOfScreen - 330 + (cardToSelect * 90));
                numberYPos.set(locationInRegister, 10);
                selectedCards.add(cardsToSelect.get(cardToSelect));
                numberOfLockedRegisters++;
            } else {
                numberXPos.set(numberOfCardsSelected, centerOfScreen - 330 + (cardToSelect * 90));
                numberYPos.set(numberOfCardsSelected, 10);
                selectedCards.add(selectedCards.size() - numberOfLockedRegisters, cardsToSelect.get(cardToSelect));
                numberOfCardsSelected++;
            }
        }
    } 

    /**
     * Gets the arraylist of selected cards
     * @return Returns the list of selected cards
     */
    public ArrayList<IProgramCard> getSelectedCards() {
        if (selectedCards == null)
            throw new IllegalStateException("No cards has been selected");
        return selectedCards;
    }

    /**
     * Start a new turn where a new set of dealt cards are created and added to TextureAtlas to be shown on screen
     */
    public void newTurn(){
        givenCardsToPlayer = false;
        handSize = 0;
        numberOfCardsSelected = 0;
        numberOfLockedRegisters = 0;
        
        cardsToSelect = new ArrayList<IProgramCard>();
        selectedCards = new ArrayList<IProgramCard>();
        cardsToSelectSprite = new ArrayList<Sprite>();
        
        setStandardNumberPosition();
        
        // Give cards to player if the move phase hasn't started
        if(!movingPlayers) {
            giveCardsToPlayer();
        }
    }
    
    private void giveCardsToPlayer() {
        int damageTokens = getDamageTokens(gameController.getCurrentPlayerByName());
        handSize = Integer.max(9 - damageTokens, 5);
        while(cardsToSelect.size() < handSize) {
            cardsToSelect.add(deck.getTopCard());
        }
        
        for(int i = 9 - damageTokens; i < 5; i++) {
            // add card to hand
            cardsToSelect.add(i, gameController.getCardsThatWerePlayedLastTurn().get(i));
            // Select that card
            selectCard(i, centerOfScreen, i);
        }
        
        for(int i = 0; i < handSize; i++) {
            cardsToSelectSprite.add(atlasCards.createSprite(cardsToSelect.get(i).toString(), -1));
        }
        givenCardsToPlayer = true;
    }

    /**
     * Used to mark that all players is finished selecting their program cards.
     * @param value True if all players are finished, otherwise false
     */
    public void setAllPlayersDonePickingCards(boolean value){
        allPlayersDonePickingCards = value;
        movingPlayers = value;
    }

    // TODO: What is this?
    public void setNumberPos(int numberPos){
        System.out.println(numberPos);
    }

    /**
     * Moves the number sprite showing the selected cards to its original position in the lower right corner.
     */
    public void setStandardNumberPosition() {
        this.numberXPos = new ArrayList<>();
        this.numberYPos = new ArrayList<>();

        int rightOfScreen = Gdx.graphics.getWidth() - 35;
        for (int i = 0; i < 5; i++) {
            numberXPos.add(rightOfScreen);
        }

        numberYPos.add(160);
        numberYPos.add(120);
        numberYPos.add(80);
        numberYPos.add(40);
        numberYPos.add(0);
    }
    
    /*
     * Recalculates the position of where the cards should be
     */
    private void updateCardPositionOnScreen(int centerOfScreen) {
        cardsPositionOnScreen = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            cardsPositionOnScreen.add(centerOfScreen - 360 + (i * 90));
        }
    }

    private void getdamageTokenOnScreenLocation() {
        damageTokensOnScreen = new ArrayList<>();
        int y = 200;
        for (int i = 0; i < 10; i++) {
            damageTokensOnScreen.add(y);
            y += 53;
        }
    }

    private void getHealthTokensOnScreen() {
        healthTokensOnScreen = new ArrayList<>();
        int y = 0;
        for (int i = 0; i < 3; i++) {
            healthTokensOnScreen.add(y);
            y += 70;
        }
    }

    private void drawTokensOnScreen(int damage, int health) {
        // Drawing damagetokens for player
        for (int i = 0; i < 10; i++) {
            if (damage > 0) {
                spriteBatchCards.draw(activeDamage, DAMAGE_X, damageTokensOnScreen.get(i), DAMAGE_WIDTH, DAMAGE_HEIGHT);
                damage--;
                continue;
            }
            spriteBatchCards.draw(deactiveDamage, DAMAGE_X, damageTokensOnScreen.get(i), DAMAGE_WIDTH, DAMAGE_HEIGHT);
        }

        // Drawing healthtokens for player
        for (int i = 0; i < 3; i++) {
            if (health > 0) {
                spriteBatchCards.draw(activeHealth, HEALTH_X, healthTokensOnScreen.get(i), HEALTH_WIDTH, HEALTH_HEIGHT);
                health--;
                continue;
            }
            spriteBatchCards.draw(deactiveHealth, HEALTH_X, healthTokensOnScreen.get(i), HEALTH_WIDTH, HEALTH_HEIGHT);
        }
    }
}
