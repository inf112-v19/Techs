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

    private final int CARD_WIDTH = 94;
    private final int CARD_HEIGHT = 130;
    private final int NUMBER_WIDTH = 35;
    private final int NUMBER_HEIGHT = 35;
    
    private int handSize;
    private boolean movingPlayers = false;
    private boolean givenCardsToPlayer;

    private final int DAMAGE_WIDTH = 35;
    private final int DAMAGE_HEIGHT = 35;
    private final int HEALTH_WIDTH = 70;
    private final int HEALTH_HEIGHT = 70;

    // The X and Y-value of each damage token
    private final int DAMAGE_X = 1245;
    private final int DAMAGE_TEN = 678;
    private final int DAMAGE_NINE = 624;
    private final int DAMAGE_EIGHT = 571;
    private final int DAMAGE_SEVEN = 518;
    private final int DAMAGE_SIX = 465;
    private final int DAMAGE_FIVE = 412;
    private final int DAMAGE_FOUR = 359;
    private final int DAMAGE_THREE = 306;
    private final int DAMAGE_TWO = 253;
    private final int DAMAGE_ONE = 200;

    // The X and Y-value of each health
    private final int HEALTH_X = 1175;
    private final int HEALTH_ONE = 0;
    private final int HEALTH_TWO = 70;
    private final int HEALTH_THREE = 140;


    private TextureAtlas atlasCards;
    private SpriteBatch spriteBatchCards;
    private ArrayList<Sprite> cardsToSelectSprite;
    private ArrayList<Integer> cardsPositionOnScreen;
    /*
    private Sprite cardToSelect0;
    private Sprite cardToSelect1;
    private Sprite cardToSelect2;
    private Sprite cardToSelect3;
    private Sprite cardToSelect4;
    private Sprite cardToSelect5;
    private Sprite cardToSelect6;
    private Sprite cardToSelect7;
    private Sprite cardToSelect8;
    */
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

    public BoardCards(RoboRally game, int numPlayers) {
        super(game);
        gameController = new GameController(2, this);
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
    }

    @Override
    public void render(float v) {
        super.render(v);
        int centerOfScreen = Gdx.graphics.getWidth() / 2;
        updateCardPositionOnScreen(centerOfScreen);
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


        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_TEN, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_NINE, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_EIGHT, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_SEVEN, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_SIX, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_FIVE, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_FOUR, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_THREE, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_TWO, DAMAGE_WIDTH, DAMAGE_HEIGHT);
        spriteBatchCards.draw(deactiveDamage, DAMAGE_X, DAMAGE_ONE, DAMAGE_WIDTH, DAMAGE_HEIGHT);

        spriteBatchCards.draw(activeHealth, HEALTH_X, HEALTH_ONE, HEALTH_WIDTH, HEALTH_HEIGHT);
        spriteBatchCards.draw(activeHealth, HEALTH_X, HEALTH_TWO, HEALTH_WIDTH, HEALTH_HEIGHT);
        spriteBatchCards.draw(activeHealth, HEALTH_X, HEALTH_THREE, HEALTH_WIDTH, HEALTH_HEIGHT);

        spriteBatchCards.end();



        if (!allPlayersDonePickingCards) {
            if(Gdx.input.isKeyJustPressed(Keys.P)) {
            	doPowerdown(gameController.getCurrentPlayerByName()); 
            	while(selectedCards.size() < 5) {
            		selectedCards.add(new ProgramCard(CardType.MOVEMENT_0, 0, 0, 0));         		
            	}
            }

            //these if-statements handles which cards has been selected by user         
            if (selectedCards.size() < 5) {
                if ((Gdx.input.getX() > cardsPositionOnScreen.get(0) && Gdx.input.getX() < (cardsPositionOnScreen.get(0) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                    if (!selectedCards.contains(cardsToSelect.get(0))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 330);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(0));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(1) && Gdx.input.getX() < (cardsPositionOnScreen.get(1) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                    if (!selectedCards.contains(cardsToSelect.get(1))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 240);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(1));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(2) && Gdx.input.getX() < (cardsPositionOnScreen.get(2) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                    if (!selectedCards.contains(cardsToSelect.get(2))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 150);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(2));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(3) && Gdx.input.getX() < (cardsPositionOnScreen.get(3) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
                    if (!selectedCards.contains(cardsToSelect.get(3))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 60);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(3));
                    }
                }
                

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(4) && Gdx.input.getX() < (cardsPositionOnScreen.get(4) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
                    if (!selectedCards.contains(cardsToSelect.get(4))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 30);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(4));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(5) && Gdx.input.getX() < (cardsPositionOnScreen.get(5) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
                    if (!selectedCards.contains(cardsToSelect.get(5))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 120);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(5));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(6) && Gdx.input.getX() < (cardsPositionOnScreen.get(6) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
                    if (!selectedCards.contains(cardsToSelect.get(6))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 210);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(6));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(7) && Gdx.input.getX() < (cardsPositionOnScreen.get(7) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
                    if (!selectedCards.contains(cardsToSelect.get(7))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 300);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(7));
                    }
                }

                if ((Gdx.input.getX() > cardsPositionOnScreen.get(8) && Gdx.input.getX() < (cardsPositionOnScreen.get(8) + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
                    if (!selectedCards.contains(cardsToSelect.get(8))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 390);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(8));
                    }
                }
                
            // This runs after the player has selected 5 cards and then presses ENTER
            } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || getPowerdownStatus(gameController.getCurrentPlayerByName()) == true) {
                if (cardsToSelect.size() >= 5) {
                    gameController.setCardsThatWerePlayedInRegister(selectedCards);
                    gameController.donePickingCards(selectedCards, this);
                    newTurn();
                }
            }
        }
  
        else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                gameController.movePlayers(this);
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
        
        cardsToSelect = new ArrayList<IProgramCard>();
        selectedCards = new ArrayList<IProgramCard>();
        cardsToSelectSprite = new ArrayList<Sprite>();
        
        setStandardNumberPosition();
        if(!movingPlayers) {
            System.out.println("giving cards to players");
            giveCardsToPlayer();
        }
    }
    
    private void giveCardsToPlayer() {
        handSize = 9 - getDamageTokens(gameController.getCurrentPlayerByName());
        System.out.println(handSize);
        while(cardsToSelect.size() < handSize) {
            cardsToSelect.add(deck.getTopCard());
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
        cardsPositionOnScreen = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++) {
            cardsPositionOnScreen.add(centerOfScreen - 360 + (i * 90));
        }
    }
}
