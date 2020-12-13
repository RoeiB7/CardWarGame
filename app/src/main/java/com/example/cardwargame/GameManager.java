package com.example.cardwargame;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameManager {

    private ImageView playButton;
    private ImageView leftCard;
    private ImageView rightCard;
    private ImageView winnerImage;
    private ImageView leftPic;
    private ImageView rightPic;
    private Button startGameButton;
    private Button leaderBoardButton;
    private ProgressBar progressBar;
    private CountDownTimer cdt;
    private MediaPlayer mp;
    private FrameLayout list;
    private FrameLayout map;


    private TextView leftScore;
    private TextView rightScore;
    private TextView title;
    private TextView score;
    private EditText winnerName;

    private int decksCounter = 0;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private int winnerScore = 0;
    private String winningPlayer = "";
    private static final String WINNER = "winner";
    private static final String SCORE = "score";
    private boolean isClosed = true;

    private ArrayList<Integer> cardsID;
    private List<Integer> firstHalfDeck;
    private List<Integer> secondHalfDeck;


    public GameManager(AppCompatActivity activity) {

    }


    /**
     * Initial all components in Entry activity
     */
    protected void initEntryViews(AppCompatActivity activity) {
        startGameButton = activity.findViewById(R.id.entry_BTN_startGame);
        leaderBoardButton = activity.findViewById(R.id.entry_BTN_leaderboard);
    }


    /**
     * Initial all components in Records activity
     */
    protected void initRecordsViews(AppCompatActivity activity) {
        list = activity.findViewById(R.id.records_LAY_list);
        map = activity.findViewById(R.id.records_LAY_map);
    }


    /**
     * Initial all components in Game activity
     */
    protected void initGameViews(AppCompatActivity activity) {

        playButton = activity.findViewById(R.id.main_IMG_play);
        leftCard = activity.findViewById(R.id.main_IMG_cardLeft);
        rightCard = activity.findViewById(R.id.main_IMG_cardRight);
        rightScore = activity.findViewById(R.id.main_LBL_scoreRight);
        leftScore = activity.findViewById(R.id.main_LBL_scoreLeft);
        progressBar = activity.findViewById(R.id.main_PRB_timer);

    }

    /**
     * Initial all components in Winner activity
     */
    protected void initWinnerViews(AppCompatActivity activity) {
        winnerImage = activity.findViewById(R.id.winner_IMG_winnerPlayer);
        title = activity.findViewById(R.id.winner_LBL_winnerMessage);
        score = activity.findViewById(R.id.winner_LBL_score);
        leftPic = activity.findViewById(R.id.winner_IMG_leftFirework);
        rightPic = activity.findViewById(R.id.winner_IMG_rightFirework);
        winnerName = activity.findViewById(R.id.winner_EDT_winnerName);
    }


    /**
     * Enter full screen mode by hiding navigation bar and status bar
     */
    protected void enterFullScreen(AppCompatActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * Moving into second activity (Winner Announcement) and passing winner identity and score
     */
    private void gameOver(AppCompatActivity activity) {
        Intent intent = new Intent(activity, WinnerAnnouncementActivity.class);
        intent.putExtra(WINNER, winningPlayer);
        intent.putExtra(SCORE, winnerScore);
        activity.startActivity(intent);
        activity.finish();
    }


    protected void startGame(AppCompatActivity activity) {
        Intent intent = new Intent(activity, GameActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    protected void leaderBoard(AppCompatActivity activity) {
        Intent intent = new Intent(activity, RecordsActivity.class);
        activity.startActivity(intent);
        activity.finish();
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
     * Shuffling cards and split them into two decks
     */
    protected void shuffleAndSplit() {

        Collections.shuffle(cardsID); //To check tie situation put this line in comment
        firstHalfDeck = cardsID.subList(0, 26);
        secondHalfDeck = cardsID.subList(26, 52);

    }

    /**
     * Initial main arraylist of cards with their id's
     */
    protected void initCardsArrayList(AppCompatActivity activity) {
        cardsID = new ArrayList<>();

        int CARDS_TYPE = 4;
        for (int i = 0; i < CARDS_TYPE; i++) {
            int CARDS_AMOUNT = 14;
            for (int j = 2; j <= CARDS_AMOUNT; j++) {

                switch (i) {

                    case 0:
                        int cardID = activity.getResources().getIdentifier("poker_card_a" + "" + j, "drawable", activity.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 1:
                        cardID = activity.getResources().getIdentifier("poker_card_b" + "" + j, "drawable", activity.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 2:
                        cardID = activity.getResources().getIdentifier("poker_card_c" + "" + j, "drawable", activity.getPackageName());
                        cardsID.add(cardID);
                        break;
                    case 3:
                        cardID = activity.getResources().getIdentifier("poker_card_d" + "" + j, "drawable", activity.getPackageName());
                        cardsID.add(cardID);
                        break;
                }
            }
        }
    }

    /**
     * Starting in each round by pressing on play button,
     * Displaying two random cards and updated score,
     * At the end of the game calling gameOver() function.
     */
    protected void nextRound(AppCompatActivity activity) {

        if (decksCounter <= 25) {
            leftCard.setImageResource(firstHalfDeck.get(decksCounter));
            rightCard.setImageResource(secondHalfDeck.get(decksCounter));

            String cardNameLeft = activity.getResources().getResourceEntryName(firstHalfDeck.get(decksCounter));
            String cardNameRight = activity.getResources().getResourceEntryName(secondHalfDeck.get(decksCounter));

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
            gameOver(activity);
        }
    }

    /**
     * Getting winner identity and score from first activity (Game activity) and displaying
     */
    protected void getDataFromGameActivity(AppCompatActivity activity) {
        Intent intent = activity.getIntent();
        String winnerName = intent.getStringExtra(WINNER);
        int winnerScore = intent.getIntExtra(SCORE, -1);
        if (winnerName.equalsIgnoreCase("PlayerLeft")) {
            winnerImage.setImageResource(R.drawable.ic_man);
            score.setText("Your Score: " + winnerScore);
        } else if (winnerName.equalsIgnoreCase("PlayerRight")) {
            winnerImage.setImageResource(R.drawable.ic_woman);
            score.setText("Your Score: " + winnerScore);
        } else {
            title.setText("It's A Tie!");
            title.setTextSize(60);
            score.setText("");
            winnerImage.setImageResource(R.drawable.ic_tie);
            leftPic.setImageResource(android.R.color.transparent);
            rightPic.setImageResource(android.R.color.transparent);

        }
    }

    protected int getScoreFromGameActivity(AppCompatActivity activity) {
        Intent intent = activity.getIntent();
        int winnerScore = intent.getIntExtra(SCORE, -1);
        return winnerScore;

    }

    protected void startDelay(AppCompatActivity activity) {

        cdt = new CountDownTimer(53000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (isClosed) {
                    nextRound(activity);
                    playSound(R.raw.flipcard, activity);
                    progressBar.incrementProgressBy(1);
                    isClosed = false;
                } else {
                    leftCard.setImageResource(R.drawable.ic_flippedcard);
                    rightCard.setImageResource(R.drawable.ic_flippedcard);
                    isClosed = true;
                }

            }

            @Override
            public void onFinish() {
                cdt.cancel();
            }
        }.start();
    }

    protected void playSound(int rawId, AppCompatActivity activity) {

        mp = MediaPlayer.create(activity, rawId);
        mp.setOnCompletionListener(mp -> {
            mp.reset();
            mp.release();
        });
        mp.start();
    }


    public ImageView getPlayButton() {
        return playButton;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getLeaderBoardButton() {
        return leaderBoardButton;
    }


    public EditText getWinnerName() {
        return winnerName;
    }
}
