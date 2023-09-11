public class Move {
    private Spot start;
    private Spot end;
    private Board playingBoard;
    private Player currentPlayer;
    public Move(Board playingBoard,Player currentPlayer){
        this.setBoard(playingBoard);
        this.setPlayer(currentPlayer);
    }

    public void execute(boolean captureOnly){
            if (this.canExecute(captureOnly)){
                this.getEnd().setPiece(this.getStart().getPiece());
                this.getStart().setPiece(null);
                if (Math.abs(this.getEnd().getY() - this.getStart().getY()) == 2) {
                    playingBoard.getSpot((byte) ((this.getStart().getX() + this.getEnd().getX()) / 2), (byte) ((this.getStart().getY() + this.getEnd().getY()) / 2)).setPiece(null);
                }
                if(this.getEnd().getY() == 0 && this.getPlayer().getIsWhite() || this.getEnd().getY() == 7 && !this.getPlayer().getIsWhite()){
                    this.getEnd().manPromotion();
                }
            }
        }
    public void prepareMove() {
        boolean clicked = false;
        while (!clicked) {
            for (byte x = 0; x < 8; x++) {
                for (byte y = 0; y < 8; y++) {
                    if (playingBoard.getSpot(x, y).getClicked()) {
                        Spot spot = playingBoard.getSpot(x,y);
                        try {
                            if (this.getStart() == null || spot.getPiece().getIsWhite() == this.currentPlayer.getIsWhite()) {
                                this.setStart(spot);
                                System.out.println("start selected");
                            }
                            else{
                                this.setEnd(spot);
                                System.out.println("end selected");

                            }
                        }
                        catch(NullPointerException e){
                            this.setEnd(spot);
                            System.out.println("end selected");
                        }
                        clicked = true;
                        spot.setClicked(false);
                    }
                }
            }
        }
    }

    public boolean canSetStart(boolean captureOnly){
        try {
            return this.getStart().canSetMoveSpot(this, true, captureOnly);
        }
        catch(NullPointerException e){
            return false;
        }
    }

    public boolean canSetEnd(boolean captureOnly){
        try {
            return this.getEnd().canSetMoveSpot(this, false, captureOnly);
        }
        catch(NullPointerException e){
            return false;
        }
    }
    public boolean canExecute(boolean captureOnly){
        return this.canSetStart(captureOnly) && this.canSetEnd(captureOnly);
    }

    public void setStart(Spot start){
        this.start = start;
    }
    public void setEnd(Spot end){
        this.end = end;
    }
    public void setBoard(Board playingBoard){this.playingBoard = playingBoard;}
    public void setPlayer(Player currentPlayer){this.currentPlayer=currentPlayer;}
    public Spot getStart() {
        return this.start;
    }
    public Spot getEnd(){
        return this.end;
    }

    public Player getPlayer(){return this.currentPlayer;}
    public Board getBoard(){return this.playingBoard;}

}
