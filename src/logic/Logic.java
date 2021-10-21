package logic;

import display.DisplayManager;
import gameBoard.Board;
import player.BasePlayer;
import player.ComputerPlayer;
import player.HumanPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 進行役が Logic クラス
 */
public class Logic extends BaseLogic {
    // ターン数
    private int turnCounter = 10;
    // ゲームの継続フラグj (仕様書未登録)
    private boolean isGameContinued = true;

    // プレイヤーの人数
    private ArrayList<BasePlayer> players = new ArrayList<>();

    // クラスのインスタンス
    private DisplayManager disp = new DisplayManager();
    private Board board = new Board();
    private Scanner scanner = new Scanner(System.in);


    @Override
    void start() {
        // 準備する処理
        // ゲームタイトルの表示
        disp.showGameTitle();

        // ゲームボードの表示
        board.showSampleGameBoard(disp);

        // ゲームルールの説明
        disp.showGameRule();

        // ゲームプレイヤーの人数を尋ねる
        // ゲームプレイヤーの人数を把握する(confluence の記載に抜けてんぞ)
        disp.askHowManyPlayers();
        int numOfPlayer = BasePlayer.askHowManyPeopleJoin(disp, scanner);

        // ゲームプレイヤーに名前を与える
        for (int i = 0; i < numOfPlayer; i++) {
            BasePlayer player;
            if (i == 0) {
                player = new HumanPlayer("あなた");
            } else {
                String cpuPlayerName = "コンピュータ" + i;
                player = new ComputerPlayer(cpuPlayerName);
            }
            players.add(player);
        }


        // ゲーム開始を宣言する
        disp.showGameStart();

        // プレイヤーの名前を表示する
        showPlayerNames(players);

    }


    // ゲームに参加するプレイヤーの名前を表示する
    private void showPlayerNames(ArrayList<BasePlayer> players) {
        int playerIndex = 1;
        for (BasePlayer player : players) {
            disp.showPlayerNames(playerIndex, player.getPlayerName());
            playerIndex++;
        }
    }

    // プレイヤーにサイコロを振る様に伝える
    private void tellPlayerThrowDice(String playerName) {
        disp.tellPlayerThrowDice(playerName);
    }

    // プレイヤーのサイコロの出目の和を取得する
    private int fetchPlayersDiceSummation(BasePlayer player) {
        return player.getSummationOfTwoDice();
    }

    // プレイヤーのサイコロの出目がゾロ目かどうか取得する
    private boolean isPlayerDiceZorome(BasePlayer player) {
        return player.fetchIsZorome();
    }

    // サイコロの出目をボードに表示させる(重複しているのであとで消す)
    private void showBoard(HashMap<Integer, Boolean> scoreMap) {
        board.showGameBoard(disp, scoreMap);
    }

    // プレイヤーの得点を表示させる
    private void showPlayerScore() {
    }

    // 繰り返す
    @Override
    protected void processLoop() {
        do {
            disp.showGameRound(turnCounter);

            if (turnCounter == 0) {

                fetchPlayerNameAndPlayerScore();
                rearrangePlayerNameBasedOnPlayerScore();
                showPlayerRanking();
                finishGame();

            } else {

                for (BasePlayer player : players) {

                    // プレイヤーにサイコロを振るよう伝える
                    tellPlayerThrowDice(player.getPlayerName());

                    // エンターのみ押させる
//                    pressEnter();
                    pressEnterBeforePlayerTurn();
                    // プレイヤーがサイコロをふる(ここで for を使わないと限界)
                    player.throwTwoDice();
                    // 進行役がダイスの目の輪を把握する
                    int twoDiceSummation = fetchPlayersDiceSummation(player);
                    boolean isZorome = isPlayerDiceZorome(player);
                    if (isZorome) {
                        // ゾロ目ならプレイヤーの得点は 2 倍になる
                        int twoTimesScore = twoDiceSummation * 2;
                        player.addPlayerScore(twoTimesScore);
                    } else {
                        // それ以外は普通にそのまま得点を与える
                        player.addPlayerScore(twoDiceSummation);
                    }


                    // 進行役が得点を表示する
                    if (player instanceof ComputerPlayer) {
                        ((ComputerPlayer) player).showComputerPlayerScore(player, disp);
                    } else if (player instanceof HumanPlayer) {
                        ((HumanPlayer) player).showHumanPlayerScore(disp, twoDiceSummation);
                    }


                    // プレイヤーに得点を暗記させる
                    player.setPlayerScoreMap(twoDiceSummation);

                    disp.showSeparationPerPlayer();

                }
                // 進行役が出目の和をボード上に表示させる。そのために各プレイヤーが記憶している得点表を取得する。
                showBoard();
            }

            // ターンカウンターを引き算する
            subtractionNumberOfTurn();
        } while (turnCounter >= 0);
    }

    private void subtractionNumberOfTurn() {
        turnCounter--;
    }

    private void pressEnter() {
        scanner.nextLine();
    }

    private void pressEnterBeforePlayerTurn() {
        disp.showPressEnter();
        scanner.nextLine();
    }

    // ターン数が0になった時、プレイヤー全員の名前と得点を取得する。
    private void fetchPlayerNameAndPlayerScore() {
    }

    // プレイヤーの得点順にプレイヤーの名前を並び替える
    private void rearrangePlayerNameBasedOnPlayerScore() {
    }

    // プレイヤーの得点順に順位をつけ、プレイヤーの名前と得点を表示する
    private void showPlayerRanking() {
    }

    // ゲーム終了の宣言
    private void finishGame() {
        isGameContinued = false;
    }

    // ゲームの終了を宣言する
    @Override
    protected void finish() {
        // 終了の宣言
        disp.declareGameFinished();
    }

    @Override
    protected boolean isEnd() {
        // isGameContinued を返却するので、これを finish の中でフラグを変更する。
        return isGameContinued;
    }
}
