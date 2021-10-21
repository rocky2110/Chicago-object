package player;

import display.DisplayManager;

public class HumanPlayer extends BasePlayer {
    public HumanPlayer(String playerName) {
        super(playerName);
    }


    // BasePlayer とは異なり独自に実装するべき振る舞いを実装する


    // 0 ~ 5 の範囲で、延長したいターン数を決定する
    public void decideHowManyTurnsExtend() {

        //        extendTurn = 延長したいターン数
    }

    // 決定したターン数を返却する
    public int getHowManyTurnsExtend() {
        return extendTurn;
    }


    public void showHumanPlayerScore(DisplayManager disp, int twoDiceSummation) {
        disp.showHumanPlayerScore(twoDiceSummation);
    }
}
