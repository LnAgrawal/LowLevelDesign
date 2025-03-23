package model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    private PlayingPiece[][] playingPieces;
    public Board(int size){
        this.size = size;
        playingPieces = new PlayingPiece[size][size];
    }

    public PlayingPiece[][] getPlayingPieces() {
        return playingPieces;
    }

    public boolean addPiece(PlayingPiece piece, int row, int column){
        if(playingPieces[row][column] !=null){
            return false;
        }
        playingPieces[row][column] = piece;
        return true;
    }

    public List<Pair<Integer,Integer>> getFreeCells(){
        List<Pair<Integer,Integer>> list = new ArrayList<>();

        //Arrays.stream(playingPieces).forEach();
        for(int i =0; i < size;  i++){
            for(int j =0; j < size;  j++){
                if(playingPieces[i][j] == null){
                    list.add(new Pair<>(i,j));
                }
            }
        }
        return list;
    }

    public void printBoard(){
        for(int i =0; i < size;  i++){
            for(int j =0; j < size;  j++){
                if(playingPieces[i][j] == null){
                   System.out.print(" ");
                } else {
                    System.out.print(playingPieces[i][j].pieceType);
                }
                if(j < size-1) {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
    }
}
