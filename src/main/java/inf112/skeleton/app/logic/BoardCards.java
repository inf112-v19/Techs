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
    private ArrayList<IProgramCard> cardsToSelect;
    private ArrayList<IProgramCard> selectedCards;
    private ArrayList<Integer> numberOfSelectedCards;
    private boolean isPowerdown = false;

    private final int CARD_WIDTH = 94;
    private final int CARD_HEIGHT = 130;
    private final int NUMBER_WIDTH = 35;
    private final int NUMBER_HEIGHT = 35;

    private TextureAtlas atlasCards;
    private SpriteBatch spriteBatchCards;
    private Sprite cardToSelect0;
    private Sprite cardToSelect1;
    private Sprite cardToSelect2;
    private Sprite cardToSelect3;
    private Sprite cardToSelect4;
    private Sprite cardToSelect5;
    private Sprite cardToSelect6;
    private Sprite cardToSelect7;
    private Sprite cardToSelect8;

    private Texture number1;
    private Texture number2;
    private Texture number3;
    private Texture number4;
    private Texture number5;

    //shows order of selected cards
    private ArrayList<Integer> numberXPos;
    private ArrayList<Integer> numberYPos;

    private boolean allPlayersDonePickingCards = false;
    private boolean finishedTurn = false;

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
        newTurn();
    }

    @Override
    public void render(float v) {
        super.render(v);
        int centerOfScreen = Gdx.graphics.getWidth()/2;
        int cardPos0 = centerOfScreen - 360;
        int cardPos1 = centerOfScreen - 270;
        int cardPos2 = centerOfScreen - 180;
        int cardPos3 = centerOfScreen - 90;
        int cardPos4 = centerOfScreen;
        int cardPos5 = centerOfScreen + 90;
        int cardPos6 = centerOfScreen + 180;
        int cardPos7 = centerOfScreen + 270;
        int cardPos8 = centerOfScreen + 360;

        //shows 9 cards player can select
        spriteBatchCards.begin();
        spriteBatchCards.draw(cardToSelect0, cardPos0,0, CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect1, cardPos1,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect2, cardPos2,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect3, cardPos3,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect4, cardPos4,0, CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect5, cardPos5,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect6, cardPos6,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect7, cardPos7,0,CARD_WIDTH, CARD_HEIGHT);
        spriteBatchCards.draw(cardToSelect8, cardPos8,0,CARD_WIDTH, CARD_HEIGHT);

        //shows numbers for order of selected cards
        spriteBatchCards.draw(number1, numberXPos.get(0), numberYPos.get(0), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number2, numberXPos.get(1), numberYPos.get(1), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number3, numberXPos.get(2), numberYPos.get(2), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number4, numberXPos.get(3), numberYPos.get(3), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.draw(number5, numberXPos.get(4), numberYPos.get(4), NUMBER_WIDTH, NUMBER_HEIGHT);
        spriteBatchCards.end();



        if (!allPlayersDonePickingCards) {

            if(finishedTurn) {
                System.out.println("do end of turn");
                processEndOfTurns();
                finishedTurn = false;
            }
            
            if(Gdx.input.isKeyJustPressed(Keys.P)) {
            	doPowerdown(gameController.getCurrentPlayerByName()); 
            	while(selectedCards.size() < 5) {
            		selectedCards.add(new ProgramCard(CardType.MOVEMENT_0, 0, 0, 0));         		
            	}
            }

            //these if-statements handles which cards has been selected by user         
            if (selectedCards.size() < 5) {
                if ((Gdx.input.getX() > cardPos0 && Gdx.input.getX() < (cardPos0 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                    if (!selectedCards.contains(cardsToSelect.get(0))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 330);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(0));
                        numberOfSelectedCards.remove((Integer) 0);
                    }
                }

                if ((Gdx.input.getX() > cardPos1 && Gdx.input.getX() < (cardPos1 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                    if (!selectedCards.contains(cardsToSelect.get(1))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 240);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(1));
                        numberOfSelectedCards.remove((Integer) 1);
                    }
                }

                if ((Gdx.input.getX() > cardPos2 && Gdx.input.getX() < (cardPos2 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                    if (!selectedCards.contains(cardsToSelect.get(2))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 150);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(2));
                        numberOfSelectedCards.remove((Integer) 2);
                    }
                }

                if ((Gdx.input.getX() > cardPos3 && Gdx.input.getX() < (cardPos3 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
                    if (!selectedCards.contains(cardsToSelect.get(3))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen - 60);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(3));
                        numberOfSelectedCards.remove((Integer) 3);
                    }
                }
                

                if ((Gdx.input.getX() > cardPos4 && Gdx.input.getX() < (cardPos4 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
                    if (!selectedCards.contains(cardsToSelect.get(4))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 30);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(4));
                        numberOfSelectedCards.remove((Integer) 4);
                    }
                }

                if ((Gdx.input.getX() > cardPos5 && Gdx.input.getX() < (cardPos5 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
                    if (!selectedCards.contains(cardsToSelect.get(5))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 120);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(5));
                        numberOfSelectedCards.remove((Integer) 5);
                    }
                }

                if ((Gdx.input.getX() > cardPos6 && Gdx.input.getX() < (cardPos6 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
                    if (!selectedCards.contains(cardsToSelect.get(6))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 210);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(6));
                        numberOfSelectedCards.remove((Integer) 6);
                    }
                }

                if ((Gdx.input.getX() > cardPos7 && Gdx.input.getX() < (cardPos7 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
                    if (!selectedCards.contains(cardsToSelect.get(7))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 300);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(7));
                        numberOfSelectedCards.remove((Integer) 7);
                    }
                }

                if ((Gdx.input.getX() > cardPos8 && Gdx.input.getX() < (cardPos8 + CARD_WIDTH) && Gdx.input.getY() > Gdx.graphics.getHeight() - CARD_HEIGHT) && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
                    if (!selectedCards.contains(cardsToSelect.get(8))) {
                        numberXPos.set(selectedCards.size(), centerOfScreen + 390);
                        numberYPos.set(selectedCards.size(), 10);
                        selectedCards.add(cardsToSelect.get(8));
                        numberOfSelectedCards.remove((Integer) 8);
                    }
                }
                
            // This runs after the player has selected 5 cards and then presses ENTER
            } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || getPowerdownStatus(gameController.getCurrentPlayerByName()) == true) {
                if (cardsToSelect.size() >= 5) {
                    setCardsThatWerePlayedAndKeptInGameController();
                    gameController.donePickingCards(selectedCards, this);
                    newTurn();
                }
            }
        }
  
        else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                gameController.movePlayers(this);
                finishedTurn = true;
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
        cardsToSelect = new ArrayList<IProgramCard>();
        selectedCards = new ArrayList<IProgramCard>();
        numberOfSelectedCards = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++) {
            numberOfSelectedCards.add(i);
        }
        
        setStandardNumberPosition();

        // Gets the cards that weren't played last turn and adds them to this turns hand
        ArrayList<IProgramCard> keptCards = gameController.getCardsThatWereNotPlayed();
        System.out.println(keptCards.size());
        for(IProgramCard card : keptCards) {
            cardsToSelect.add(card);
            System.out.println("getting previous cards");
        }
        
        while(cardsToSelect.size() < 9) {
            cardsToSelect.add(deck.getTopCard());
        }

        cardToSelect0 = atlasCards.createSprite(cardsToSelect.get(0).toString(), -1);
        cardToSelect1 = atlasCards.createSprite(cardsToSelect.get(1).toString(), -1);
        cardToSelect2 = atlasCards.createSprite(cardsToSelect.get(2).toString(), -1);
        cardToSelect3 = atlasCards.createSprite(cardsToSelect.get(3).toString(), -1);
        cardToSelect4 = atlasCards.createSprite(cardsToSelect.get(4).toString(), -1);
        cardToSelect5 = atlasCards.createSprite(cardsToSelect.get(5).toString(), -1);
        cardToSelect6 = atlasCards.createSprite(cardsToSelect.get(6).toString(), -1);
        cardToSelect7 = atlasCards.createSprite(cardsToSelect.get(7).toString(), -1);
        cardToSelect8 = atlasCards.createSprite(cardsToSelect.get(8).toString(), -1);
    }

    /**
     * Used to mark that all players is finished selecting their program cards.
     * @param value True if all players are finished, otherwise false
     */
    public void setAllPlayersDonePickingCards(boolean value){
        allPlayersDonePickingCards = value;
    }

    // TODO: What is this?
    public void setNumberPos(int numberPos){
        System.out.println(numberPos);
    }

    /**
     * Moves the number sprites showing the selected cards to its original position in the lower right corner.
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
     * Gives GameController two lists, one with cards played this turn, the other with unplayed cards
     */
    private void setCardsThatWerePlayedAndKeptInGameController() {
        // For played cards:
        gameController.setCardsThatWerePlayedInRegister(selectedCards);
        
        // For unplayed cards:
        ArrayList<IProgramCard> unplayedCards = new ArrayList<IProgramCard>();
        for(Integer i : numberOfSelectedCards) {
            unplayedCards.add(cardsToSelect.get(i));
        }
        gameController.setCardsThatWereNotPlayed(unplayedCards);
    }
}
