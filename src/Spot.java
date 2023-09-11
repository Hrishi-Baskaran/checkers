import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Spot implements MouseListener {
    private byte xPosition;
    private byte yPosition;
    private Piece piece;
    private final JPanel panel = new JPanel();
    private short width;
    private short height;
    private boolean clicked;
    public Spot(byte xPosition, byte yPosition) {
        this.setX(xPosition);
        this.setY(yPosition);
        if((this.getX()+this.getY())%2 == 0) {
            this.getPanel().setBackground(Color.BLACK);
        }
        else{
            this.getPanel().setBackground(Color.WHITE);
        }
        this.getPanel().addMouseListener(this);
    }

    public void setX(byte xPosition) {
        this.xPosition = xPosition;
    }

    public void setY(byte yPosition) {
        this.yPosition = yPosition;
    }
    public void setPiece(Piece piece){
        this.getPanel().setVisible(false);
        this.piece = piece;
        if(this.piece != null) {
            this.getPanel().add(this.getPiece().getLabel());
        }
        else{
            this.getPanel().removeAll();
        }
        this.getPanel().setVisible(true);
    }
    public void setWidth(short width){this.width = width;}
    public void setHeight(short height){this.height = height;}
    public void setClicked(boolean c){
        this.clicked = c;
    }
    public byte getX() {
        return this.xPosition;
    }

    public byte getY() {
        return this.yPosition;
    }

    public Piece getPiece(){
        return this.piece;
    }
    public short getWidth(){return this.width;}
    public short getHeight(){return this.height;}
    public JPanel getPanel(){return this.panel;}
    public boolean getClicked(){return this.clicked;}

    public void setPanelSize(Board playingBoard){
        this.getPanel().setVisible(false);
        this.setWidth((short) (playingBoard.getWidth()/8));
        this.setHeight((short) (playingBoard.getHeight()/8));
        this.getPanel().setBounds(this.getX()*playingBoard.getWidth()/8, playingBoard.getHeight()-(this.getY()+1)*playingBoard.getHeight()/8,this.getWidth(),this.getHeight());
        if(this.getPiece()!= null){
            this.getPiece().setImageSize(this);
        }
        this.getPanel().setVisible(true);
    }
    public boolean canSetMoveSpot(Move move, boolean isStart, boolean captureOnly){
        boolean output;
        try {
            if(isStart) {
                if(captureOnly) {
                    if (move.getPlayer().getIsWhite()) {
                        output = this.getPiece().getIsWhite() && this.getPiece().canCapture(move.getStart(), move.getBoard());
                    } else {
                        output = !this.getPiece().getIsWhite() && this.getPiece().canCapture(move.getStart(), move.getBoard());
                    }
                }
                else{
                    if (move.getPlayer().getIsWhite()) {
                        output = this.getPiece().getIsWhite();
                    } else {
                        output = !this.getPiece().getIsWhite();
                    }
                }
            }
            else{
                if(captureOnly) {
                    output = move.getStart().getPiece().canCapture(move.getStart(), move.getEnd(), move.getBoard());
                }
                else{
                    output = move.getStart().getPiece().canTurn(move.getStart(), move.getEnd(), move.getBoard());
                }
            }
            return output;
        }
        catch(NullPointerException e){
            return false;
        }
    }

    public void setMoveSpot(Move move, boolean isStart){
        if(isStart){
            move.setStart(this);
        }
        else{
            move.setEnd(this);
        }
    }
    public boolean pieceOnSpot(){
        return this.getPiece() != null;
    }
    public void manPromotion(){
        boolean isWhite = this.getPiece().getIsWhite();
        this.setPiece(null);
        this.setPiece(new King(isWhite));
        this.getPiece().setImageSize(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.clicked = true;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
