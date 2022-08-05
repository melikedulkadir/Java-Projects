import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ork extends Zorde{
    private int orkHP = Constants.orkMaxHP;
    private int orkMaxHP = Constants.orkMaxHP;

    public Ork(String characterId){
        super(characterId);
    }

    public int getHP() {
        return orkHP;
    }

    public void setHP(int orkHP) {
        this.orkHP = orkHP;
    }

    public int getMaxHP() {
        return orkMaxHP;
    }

    @Override
    public boolean move(String characterId, int row, int column, int attackerAp, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes, boolean isFirst, FileWriter output) throws IOException {
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]!=null && board[i][j].getCharacterId().equals(characterId)){
                    if (i+row<board.length && i+row>=0 && j+column<board.length && j+column>=0) {
                        if (board[i + row][j + column] == null || board[i + row][j + column].toString().equals("Calliance") || board[i + row][j + column].toString().equals("Zorde")) {
                            checkNeighbours(i-1,j-1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i-1,j, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i-1,j+1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i,j-1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i,j+1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i+1,j-1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i+1,j, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i+1,j+1, board.length,attackerAp,board,calliances,false);
                            checkNeighbours(i,j, board.length,attackerAp,board,calliances,false);
                            break;
                        }
                    }
                }
            }
        }
       return super.move(characterId, row, column,attackerAp, board,calliances,zordes, isFirst,output);
    }
}
