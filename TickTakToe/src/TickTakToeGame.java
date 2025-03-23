import javafx.util.Pair;
import model.*;

import java.util.*;

public class TickTakToeGame {
    private Board board;
    Deque<Player> players;
    int size;

    public TickTakToeGame(int size){
        this.size = size;
        initGame();
    }

    private void initGame(){
        board = new Board(size);
        players = new LinkedList<>();

        Player player1 = new Player("Player 1", new PlayingPieceX());
        Player player2 = new Player("Player 2", new PlayingPieceO());
        players.add(player1);
        players.add(player2);
    }

    public String play(){
        boolean  noWinner = true;

        while(noWinner){
            board.printBoard();

            List<Pair<Integer,Integer>> freeCells = board.getFreeCells();
            if(freeCells.isEmpty()){
                System.out.println("No space to fill, exiting");
                noWinner=false;
                continue;
            }

            Player currentPlayer = players.pollFirst();

            System.out.println(currentPlayer.getName() + " turn. Please enter row, column to place the piece: ");
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            String[] rowColumn = value.split(",");
            if(rowColumn.length != 2){
              System.out.println("Wrong value entered. Please enter again");
              players.addFirst(currentPlayer);
              continue;
            }
            int row = Integer.parseInt(rowColumn[0]);
            int column = Integer.parseInt(rowColumn[1]);
            boolean isPiecePlaced =  board.addPiece(currentPlayer.getPlayingPiece(), row, column);
             if(!isPiecePlaced) {
                 System.out.println("Incorrect position chosen. Please enter again");
                 players.addFirst(currentPlayer);
                 continue;
             }
            players.addLast(currentPlayer);

             boolean winner = isWinner(row, column,currentPlayer.getPlayingPiece().getPieceType());
             if(winner){
                 return currentPlayer.getName() + " wins";
             }


        }

        return "Tie";



    }

    private boolean isWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch =  true;
        boolean antiDiagonalMatch =  true;

        //row
        for(int i=0; i < size; i ++){
            if(board.getPlayingPieces()[row][i] == null || board.getPlayingPieces()[row][i].getPieceType() != pieceType)
                rowMatch = false;
        }
        for(int i=0; i < size; i ++){
            if(board.getPlayingPieces()[i][column] == null || board.getPlayingPieces()[i][column].getPieceType() != pieceType)
                columnMatch = false;
        }

        for(int i=0, j=0; i < size; i ++, j++){
            if(board.getPlayingPieces()[i][j] == null || board.getPlayingPieces()[i][j].getPieceType() != pieceType)
                diagonalMatch = false;
        }

        for(int i=0, j=size-1; i < size; i ++, j--){
            if(board.getPlayingPieces()[i][j] == null || board.getPlayingPieces()[i][j].getPieceType() != pieceType)
                antiDiagonalMatch = false;
        }


        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
