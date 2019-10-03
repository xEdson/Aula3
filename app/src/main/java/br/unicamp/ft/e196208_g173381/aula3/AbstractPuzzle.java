package br.unicamp.ft.e196208_g173381.aula3;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public abstract class AbstractPuzzle {

     Board board;
    private ArrayList<ImageView> image;

    public AbstractPuzzle(Board board, ArrayList<ImageView> image) {
        this.board = board;
        this.image = image;
        startGame();

        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                addListener(image.get(i*board.getNumColumns()+j),i,j);
            }
        }
        readraW();
    }




    public void readraW() {

        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                image.get(i * board.getNumColumns() + j).setImageResource(board.getGameBlock(i, j));
            }
        }

    }

    public void startGame(){
        board.startGame();
    }
    public abstract void addListener(ImageView imageView, int line, int column);

    public abstract boolean endGame();
}
