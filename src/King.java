import javax.swing.*;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
        if(isWhite) {
            this.getLabel().setIcon(new ImageIcon(ICON_FOLDER + "white_king.png"));
        }
        else{
            this.getLabel().setIcon(new ImageIcon(ICON_FOLDER + "black_king.png"));
        }
        this.getLabel().setVisible(true);
    }

    @Override
    public boolean canMove(Spot start, Spot end, Board playingBoard) {
        try {
            if (Math.abs(end.getY() - start.getY()) == 1 && Math.abs(end.getX() - start.getX()) == 1) {
                return end.getPiece() == null;
            }
            return false;
            }
        catch (Exception e) {
            return false;
        }
    }

    public boolean canCapture(Spot start, Spot end, Board playingBoard) {
        try {
            if (Math.abs(end.getY() - start.getY()) == 2 && Math.abs(end.getX() - start.getX()) == 2) {
                if (playingBoard.getSpot((byte) ((start.getX() + end.getX()) / 2), (byte) ((start.getY() + end.getY()) / 2)).getPiece().getIsWhite() != this.getIsWhite()) {
                    return end.getPiece() == null;
                }

            }
            return false;
        }
        catch(ArrayIndexOutOfBoundsException | NullPointerException e){
            return false;
        }
    }
    public boolean canCapture(Spot start, Board playingBoard){
        boolean result1;
        boolean result2;
        boolean result3;
        boolean result4;
        try{
            result1 = this.canCapture(start, playingBoard.getSpot((byte) (start.getX() + 2), (byte) (start.getY() + 2)), playingBoard);
        }
        catch( ArrayIndexOutOfBoundsException e){
            result1 = false;
        }

        try{
            result2 =this.canCapture(start, playingBoard.getSpot((byte) (start.getX() - 2), (byte) (start.getY() + 2)), playingBoard);
        }
        catch(ArrayIndexOutOfBoundsException e){
            result2 = false;
        }
        try{
            result3 = this.canCapture(start, playingBoard.getSpot((byte) (start.getX() - 2), (byte) (start.getY() - 2)), playingBoard);
        }
        catch(ArrayIndexOutOfBoundsException e){
            result3 = false;
        }
        try{
            result4 =  this.canCapture(start, playingBoard.getSpot((byte) (start.getX() + 2), (byte) (start.getY() - 2)), playingBoard);
        }
        catch(ArrayIndexOutOfBoundsException e){
            result4 = false;
        }
        return result1 || result2|| result3|| result4;
    }
}