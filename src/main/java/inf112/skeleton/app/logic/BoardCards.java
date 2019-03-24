package inf112.skeleton.app.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.GameControllerExperimental;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.screens.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardCards extends Board {

    private GameControllerExperimental gameControllerExperimental;

    //Card handling
    private ProgramCardDeck deck = new ProgramCardDeck();
    private ArrayList<IProgramCard> cardsToSelect;
    private ArrayList<IProgramCard> selectedCards;

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

    public BoardCards(RoboRally game) {
        super(game);
        gameControllerExperimental = new GameControllerExperimental(2, game);
        atlasCards = new TextureAtlas("assets/ProgramSheet/ProgramCardsTexturePack/cardsTexture.atlas");
        spriteBatchCards = new SpriteBatch();
        newTurn();
    }

    @Override
    public void render(float v) {
        super.render(v);
        int centerOfScreen = Gdx.graphics.getWidth()/2;

        //shows 9 cards player can select
        spriteBatchCards.begin();
        spriteBatchCards.draw(cardToSelect0,centerOfScreen - 360,0,94,130);
        spriteBatchCards.draw(cardToSelect1,centerOfScreen - 270,0,94,130);
        spriteBatchCards.draw(cardToSelect2,centerOfScreen - 180,0,94,130);
        spriteBatchCards.draw(cardToSelect3,centerOfScreen - 90,0,94,130);
        spriteBatchCards.draw(cardToSelect4,centerOfScreen,0,94,130);
        spriteBatchCards.draw(cardToSelect5,centerOfScreen + 90,0,94,130);
        spriteBatchCards.draw(cardToSelect6,centerOfScreen + 180,0,94,130);
        spriteBatchCards.draw(cardToSelect7,centerOfScreen + 270,0,94,130);
        spriteBatchCards.draw(cardToSelect8,centerOfScreen + 360,0,94,130);

        //shows numbers for order of selected cards
        spriteBatchCards.draw(number1, numberXPos.get(0), numberYPos.get(0),35,35);
        spriteBatchCards.draw(number2, numberXPos.get(1), numberYPos.get(1),35,35);
        spriteBatchCards.draw(number3, numberXPos.get(2), numberYPos.get(2),35,35);
        spriteBatchCards.draw(number4, numberXPos.get(3), numberYPos.get(3),35,35);
        spriteBatchCards.draw(number5, numberXPos.get(4), numberYPos.get(4),35,35);
        spriteBatchCards.end();

        //these if-statements handles which cards has been selected på user
        if (selectedCards.size() < 5) {
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                if (!selectedCards.contains(cardsToSelect.get(0))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen - 330);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(0));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                if (!selectedCards.contains(cardsToSelect.get(1))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen - 240);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(1));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                if (!selectedCards.contains(cardsToSelect.get(2))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen - 150);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(2));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
                if (!selectedCards.contains(cardsToSelect.get(3))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen - 60);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(3));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
                if (!selectedCards.contains(cardsToSelect.get(4))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen + 30);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(4));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
                if (!selectedCards.contains(cardsToSelect.get(5))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen + 120);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(5));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
                if (!selectedCards.contains(cardsToSelect.get(6))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen + 210);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(6));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
                if (!selectedCards.contains(cardsToSelect.get(7))) {
                    numberXPos.set(selectedCards.size(), centerOfScreen + 300);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(7));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
                if (!selectedCards.contains(cardsToSelect.get(8))){
                    numberXPos.set(selectedCards.size(), centerOfScreen + 390);
                    numberYPos.set(selectedCards.size(), 10);
                    selectedCards.add(cardsToSelect.get(8));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)){
                if (cardsToSelect.size() >= 5) {
                    newTurn();
                }
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            if (cardsToSelect.size() >= 5) {
                gameControllerExperimental.donePickingCards(selectedCards);
                newTurn();
            }
        }
        /*
        else if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)){
            ha
        }
        */

    }

    public void setNumberPos(int numberPos){
        System.out.println(numberPos);
    }

    //puts all numbers in right corner
    public void setStandardNumberPosition(){
        number1 = new Texture("assets/ProgramSheet/numbersInCircle/numberOne.png");
        number2 = new Texture("assets/ProgramSheet/numbersInCircle/numberTwo.png");
        number3 = new Texture("assets/ProgramSheet/numbersInCircle/numberThree.png");
        number4 = new Texture("assets/ProgramSheet/numbersInCircle/numberFour.png");
        number5 = new Texture("assets/ProgramSheet/numbersInCircle/numberFive.png");

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


    public void newTurn(){
        cardsToSelect = new ArrayList<>();
        selectedCards = new ArrayList<>();
        setStandardNumberPosition();

        for (int i = 0; i < 9; i++)
            cardsToSelect.add(deck.getTopCard());

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

    public ArrayList<IProgramCard> getSelectedCards(){
        if (selectedCards == null)
            throw new IllegalStateException("No cards has been selected");
        return selectedCards;
    }
}
