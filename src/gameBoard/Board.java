package gameBoard;

import display.DisplayManager;

import java.util.HashMap;

public class Board {

    // ゲームのルール説明時に表示するサンプルのゲームボードを作成して表示する
    public void showSampleGameBoard(DisplayManager disp) {
        disp.showSampleGameBoardPlayerNameLine();
        disp.showSampleGameBoardLine();
    }


    public void showGameBoard(DisplayManager disp, HashMap<Integer, Boolean> scoreMap) {
        // todo 表示処理
        disp.showGameBoardPlayerNameLine();
        disp.showGameBoardLine();
    }
}
