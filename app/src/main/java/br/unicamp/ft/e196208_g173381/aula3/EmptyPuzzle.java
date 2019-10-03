package br.unicamp.ft.e196208_g173381.aula3;

import android.widget.ImageView;

import java.util.ArrayList;

public class EmptyPuzzle extends AbstractPuzzle {

    public EmptyPuzzle(Board board,
                       ArrayList<ImageView> imageViews) {
        super(board, imageViews);
    }

    @Override
    public void startGame() {

    }

    public void addListener(ImageView imageView, int line, int column) {

    }

    public boolean endGame() {
        return false;
    }
}