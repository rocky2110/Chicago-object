package player;

import display.DisplayManager;
import util.PropertiesUtil;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static display.TextPropertiesFilePaths.LOGIC_TEXT_FILE_PATH;

public class BasePlayer {

    // コンストラクタ
    public BasePlayer(String playerName) {
        this.playerName = playerName;
    }

    // プレイヤーの名前
    private String playerName;
    // プレイヤーの得点
    private int playerScore = 0;
    // プレイヤーのターン数を増やす権利
    private boolean rightToExtendTurns = true;
    // プレイヤーが増やせるターン数 0 ~ 5
    private int extendOneTurn = 1;
    private int extendTwoTurn = 2;
    private int extendThreeTurn = 3;
    private int extendFourTurn = 4;
    private int extendFiveTurn = 5;
    // サイコロの出目の和
    private int summationOfTwoDice;
    protected int extendTurn;
    private boolean zorome = false;

    // 乱数
    Random rand = new Random();

    // プレイヤーの得点
    private HashMap<Integer, Boolean> playerScoreMap = new HashMap<>();

    public static int askHowManyPeopleJoin(DisplayManager disp, Scanner scan) {
        int playerNumber = 0;
        do {
            try {
                playerNumber = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                disp.alertPlayersNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (playerNumber <= 1) {
                disp.alertPlayersNumber();
            }
        } while (playerNumber <= 1);

        return playerNumber;
    }

    // 得点表を初期化する
    void init() {
        for (int i = 0; i < 12; i++) {
            playerScoreMap.put(i + 1, false);
        }
    }

    // Player に共通する動作を列挙して実装

    // サイコロを 2 つ振る
    public void throwTwoDice() {
        zorome = false;
        int diceEyeMax = Integer.parseInt(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.number.dice.eye.max"));
        int firstDiceNumber = rand.nextInt(diceEyeMax) + 1;
        int secondDiceNumber = rand.nextInt(diceEyeMax) + 1;
        summationOfTwoDice = firstDiceNumber + secondDiceNumber;
        if (firstDiceNumber == secondDiceNumber) {
            zorome = true;
        }
    }

    // サイコロの出目の和を返す done
    public int getSummationOfTwoDice() {
        return summationOfTwoDice;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean fetchIsZorome() {
        return zorome;
    }

    public void addPlayerScore(int twoDiceSummation) {
        playerScore += twoDiceSummation;
    }

    // 得点表をつける
    public void setPlayerScoreMap(int twoDiceSummation) {
        if (playerScoreMap.containsKey(twoDiceSummation)) {
            if (playerScoreMap.get(twoDiceSummation)) {
                playerScoreMap.put(twoDiceSummation, false);
            } else {
                playerScoreMap.put(twoDiceSummation, true);
            }
        }
    }

    // 得点表を返却する
    public HashMap<Integer, Boolean> getScoreMap() {
        return playerScoreMap;
    }
}
