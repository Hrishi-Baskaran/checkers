import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public abstract class Piece{
    private boolean isWhite;
    private JLabel label = new JLabel();
    protected final String ICON_FOLDER = "icons/";


    public Piece(boolean isWhite){
        this.setIsWhite(isWhite);
    }

    public boolean getIsWhite(){
        return this.isWhite;
    }

    public JLabel getLabel(){return this.label;}

    public void setIsWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    public abstract boolean canMove(Spot start, Spot end, Board playingBoard);
    public abstract boolean canCapture(Spot start, Spot end, Board playingBoard);
    public abstract boolean canCapture(Spot start, Board playingBoard);

    public boolean canTurn(Spot start, Spot end, Board playingBoard){
        return canMove(start, end, playingBoard) || canCapture(start, end, playingBoard);
    }
    public void setImageSize(Spot spot){
        Image img;
        this.getLabel().setVisible(false);
        img = ((ImageIcon) this.getLabel().getIcon()).getImage();
        img = img.getScaledInstance(spot.getWidth(), spot.getHeight(),Image.SCALE_SMOOTH);
        this.getLabel().setIcon(new ImageIcon(img));
        this.getLabel().setVisible(true);
    }

}
