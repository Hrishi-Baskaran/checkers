import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Man extends Piece{
    public Man(boolean isWhite){
        super(isWhite);
        Image img;
        if(isWhite) {
            this.getLabel().setIcon(new ImageIcon(ICON_FOLDER + "white_man.png"));
        }
        else{
            this.getLabel().setIcon(new ImageIcon(ICON_FOLDER + "black_man.png"));
        }
        this.getLabel().setVisible(true);
    }

    @Override
    public boolean canMove(Spot start, Spot end, Board playingBoard) {
    try{
        byte whiteSign = 1;
        if(start.getPiece().getIsWhite()){
            whiteSign = -1;
        }
        if (end.getY() - start.getY() == whiteSign && Math.abs(end.getX() - start.getX()) == 1) {
            return end.getPiece() == null;
        }
        return false;
    }
    catch (Exception e) {
        return false;
    }
    }
    public boolean canCapture(Spot start, Spot end, Board playingBoard){
        try{
            byte whiteSign = 1;
            if(start.getPiece().getIsWhite()){
                whiteSign = -1;
            }
            if (end.getY() - start.getY() == 2*whiteSign && Math.abs(end.getX() - start.getX()) == 2) {
                if (playingBoard.getSpot((byte) ((start.getX() + end.getX()) / 2),(byte) (start.getY() + whiteSign)).getPiece().getIsWhite() != this.getIsWhite()) {
                    return end.getPiece() == null;
                }
            }
            return false;
        }
        catch(ArrayIndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }

    public boolean canCapture(Spot start, Board playingBoard) {
        byte whiteSign = 1;
        if (start.getPiece().getIsWhite()) {
            whiteSign = -1;
        }
        boolean result1;
        boolean result2;
        try{
            result1 = this.canCapture(start, playingBoard.getSpot((byte) (start.getX() + 2), (byte) (start.getY() + 2 * whiteSign)), playingBoard);
        }
        catch(ArrayIndexOutOfBoundsException e){
            result1 = false;
        }
        try{
            result2 = this.canCapture(start, playingBoard.getSpot((byte) (start.getX() - 2), (byte) (start.getY() + 2 * whiteSign)), playingBoard);
        }
        catch(ArrayIndexOutOfBoundsException e){
            result2 = false;
        }
        return result1 || result2;
    }

}
