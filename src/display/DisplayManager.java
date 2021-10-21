package display;

import player.BasePlayer;
import util.PropertiesUtil;

public class DisplayManager implements TextPropertiesFilePaths {
    
    // ゲームタイトルを宣言
    public void showGameTitle() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.title"));
    }


    public void showGameRule() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.rule"));
    }

    public void askHowManyPlayers() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.ask.number.of.player"));
    }

    public void showGameStart() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.started"));
    }

    public void showPlayerNames(int playerIndex, String playerName) {
        System.out.println(playerIndex + "番目  ->  " + playerName);
    }

    public void alertPlayersNumber() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.alert.invalid.number.input"));
    }

    public void showPressEnter() {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.ask.press.enter"));
    }

    public void tellPlayerThrowDice(String playerName) {
        System.out.println(playerName + PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.tell.player.throw.dice"));
    }


    public void declareGameFinished() {
    }

    public void showSampleGameBoardPlayerNameLine() {
        System.out.print(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.board.split.line.for.display"));
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.board.first.line.for.display"));
        System.out.print(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.board.split.line.for.display"));
    }



    public void showSampleGameBoardLine() {
        for (int i = 0 ; i < 12 ; i++) {
            if (i < 10) {
                System.out.print("|  " + i + "  ");
            } else {
                System.out.print("|  " + i + " ");
            }
            System.out.print(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.board.second.and.subsequent.lines.for.display"));
            System.out.print(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.board.split.line.for.display"));
        }
    }

    public void showGameBoardPlayerNameLine() {
    }

    public void showGameBoardLine() {
    }

    public void showHumanPlayerScore(int twoDiceSummation) {
        System.out.print(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.dice.result.for.human.player") + twoDiceSummation + "でした　\n");
    }

    public void showComputerPlayerScore(String playerName) {
        System.out.print(playerName + PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.dice.result.for.computer.player"));
    }

    public void showGameRound(int turnCounter) {
        System.out.println(PropertiesUtil.getText(LOGIC_TEXT_FILE_PATH, "Chicago.msg.game.round") + turnCounter + "ターンです");
    }

    public void showSeparationPerPlayer() {
        System.out.println("=====================================================================");
        System.out.println();
    }
}
