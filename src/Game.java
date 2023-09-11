import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        Player currentPlayer = player2;
        Board playingBoard = new Board((short)1000, (short)1000);
        boolean captureOnly = false;

        while(true) {
            captureOnly = playingBoard.canAnythingCapture(currentPlayer);
            if(captureOnly){
                System.out.println("CO");
            }
            else{
                System.out.println("NCO");
            }

            Move currentMove = new Move(playingBoard, currentPlayer);

            do {
                currentMove.setStart(null);
                currentMove.setEnd(null);
                while (currentMove.getStart() == null || currentMove.getEnd() == null) {
                    currentMove.prepareMove();
                }
            }
            while(!currentMove.canExecute(captureOnly));
            currentMove.execute(captureOnly);
            if(playingBoard.hasWon(currentPlayer)){
                String message = "";
                if(currentPlayer.getIsWhite()){
                    message += "White";
                }
                else{
                    message += "Black";
                }
                message += " has won!";
                JOptionPane.showMessageDialog(playingBoard.getFrame(), message, "Game Over",JOptionPane.PLAIN_MESSAGE);
                break;
            }


            if (Math.abs(currentMove.getEnd().getY()-currentMove.getStart().getY()) == 1 || !playingBoard.canAnythingCapture(currentPlayer)) {
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }
                //System.out.println("Switched Players");
            }
        }
    }
}










































