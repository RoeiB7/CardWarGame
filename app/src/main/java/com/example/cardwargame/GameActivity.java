package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {


    private ArrayList<Integer> cardsID;
    private List<Integer> firstHalfDeck;
    private List<Integer> secondHalfDeck;
    private ImageView playButton;
    private ImageView leftCard;
    private ImageView rightCard;
    private TextView leftScore;
    private TextView rightScore;
    private int decksCounter = 0;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int winnerScore = 0;
    private String winningPlayer = "";
    public static final String WINNER = "winner";
    public static final String SCORE = "score";


    /**
     * Clean and short onCreate for activating all functions
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();
        enterFullScreen();
        initCardsArrayList();
        shuffleAndSplit();
        playButton.setOnClickListener(v -> nextRound());

    }

    /**
     * Initial all components in the View
     */
    private void initViews() {

        playButton = findViewById(R.id.main_IMG_play);
        leftCard = findViewById(R.id.main_IMG_cardLeft);
        rightCard = findViewById(R.id.main_IMG_cardRight);
        rightScore = findViewById(R.id.main_LBL_scoreRight);
        leftScore = findViewById(R.id.main_LBL_scoreLeft);

    }

    /**
     * Enter full screen mode by hiding navigation bar and status bar
     */
    private void enterFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * Shuffling cards and split them into two decks
     */
    public void shuffleAndSplit() {

        Collections.shuffle(cardsID); //To check tie situation put this line in comment
        firstHalfDeck = cardsID.subList(0, 26);
        secondHalfDeck = cardsID.subList(26, 52);

    }

    /**
     * Moving into second activity (Winner Announcement) and passing winner identity and score
     */
    private void gameOver() {
        Intent intent = new Intent(this, WinnerAnnouncementActivity.class);
        intent.putExtra(WINNER, winningPlayer);
        intent.putExtra(SCORE, winnerScore);
        startActivity(intent);
        finish();
    }


    /**
     * Getting card value by his name
     *
     * @param cardName
     * @return card value
     */
    private int nameToValue(String cardName) {
        int cardValue;
        if (cardName.length() == 13) {
            cardValue = Integer.parseInt(cardName.substring(cardName.length() - 1));
        } else {
            cardValue = Integer.parseInt(cardName.substring(cardName.length() - 2));
        }

        return cardValue;
    }

    /**
     * Starting in each round by pressing on play button,
     * Displaying two random cards and updated score,
     * At the end of the game calling gameOver() function.
     */
    private void nextRound() {

        if (decksCounter <= 25) {
            leftCard.setImageResource(firstHalfDeck.get(decksCounter));
            rightCard.setImageResource(secondHalfDeck.get(decksCounter));

            String cardNameLeft = this.getResources().getResourceEntryName(firstHalfDeck.get(decksCounter));
            String cardNameRight = this.getResources().getResourceEntryName(secondHalfDeck.get(decksCounter));

            int cardValueLeft = nameToValue(cardNameLeft);
            int cardValueRight = nameToValue(cardNameRight);

            if (cardValueLeft > cardValueRight) {
                scoreLeft++;
                leftScore.setText(scoreLeft + "");
            } else if (cardValueRight > cardValueLeft) {
                scoreRight++;
                rightScore.setText(scoreRight + "");
            }
            decksCounter++;
        } else {
            if (scoreLeft > scoreRight) {
                winningPlayer = "PlayerLeft";
                winnerScore = scoreLeft;
            } else if (scoreRight > scoreLeft) {
                winningPlayer = "PlayerRight";
                winnerScore = scoreRight;
            } else {
                winningPlayer = "tie";
            }
            gameOver();
        }
    }

    /**
     * Initial main arraylist of cards with their id's
     */
    private void initCardsArrayList() {
        cardsID = new ArrayList<>();

        int CARDS_TYPE = 4;
        for (int i = 0; i < CARDS_TYPE; i++) {
            int CARDS_AMOUNT = 14;
            for (int j = 2; j <= CARDS_AMOUNT; j++) {

                switch (i) {

                    case 0:
                        int cardID = this.getResources().getIdentifier("poker_card_a" + "" + j, "drawable", this.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 1:
                        cardID = this.getResources().getIdentifier("poker_card_b" + "" + j, "drawable", this.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 2:
                        cardID = this.getResources().getIdentifier("poker_card_c" + "" + j, "drawable", this.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 3:
                        cardID = this.getResources().getIdentifier("poker_card_d" + "" + j, "drawable", this.getPackageName());
                        cardsID.add(cardID);
                        break;
                }
            }
        }
    }
}