import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board {
    private Spot[][] board;
    private short height;
    private short width;
    private final JFrame frame = new JFrame();

    public Board(short width, short height) {
        this.board = new Spot[8][8];
        this.setHeight(height);
        this.setWidth(width);
        this.frame.setLayout(null);
        this.frame.setSize(this.getWidth() + 20, this.getHeight() + 40);
        this.frame.getContentPane().setBackground(Color.GRAY);
        this.frame.setTitle("Hrishi's Checkers Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (byte y = 0; y < 8; y++) {
            for (byte x = 0; x < 8; x++) {
                this.board[x][y] = new Spot(x, y);
                if ((x + y) % 2 == 0 && y <= 2) {
                    this.getSpot(x, y).setPiece(new Man(false));
                    //this.getSpot(x, y).setPiece(new King(false));
                } else if ((x + y) % 2 == 0 && y >= 5) {
                    this.getSpot(x, y).setPiece(new Man(true));
                    //this.getSpot(x, y).setPiece(new King(true));
                }
                this.getSpot(x, y).setPanelSize(this);
                this.getFrame().add(this.getSpot(x, y).getPanel());
            }
        }
        this.getFrame().setVisible(true);
    }


    public void setHeight(short height) {
        this.height = height;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return this.height;
    }

    public short getWidth() {
        return this.width;
    }

    public Spot getSpot(byte x, byte y) throws ArrayIndexOutOfBoundsException {
        return board[x][y];
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public boolean canAnythingCapture(Player currentPlayer) {
        for (Spot[] row : board) {
            for (Spot spot : row) {
                if (spot.getX() == 2 && spot.getY() == 6) {
                }
                if (!(spot.getPiece() == null) && spot.getPiece().getIsWhite() == currentPlayer.getIsWhite()) {
                    if (spot.getPiece().canCapture(spot, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean hasWon(Player currentPlayer){
        for(Spot[] row: board){
            for(Spot spot: row){
                if(spot.getPiece() != null && spot.getPiece().getIsWhite() != currentPlayer.getIsWhite()){
                    return false;
                }
            }
        }
        return true;
    }
}

