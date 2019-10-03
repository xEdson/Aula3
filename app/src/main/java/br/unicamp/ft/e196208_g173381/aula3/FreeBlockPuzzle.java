package br.unicamp.ft.e196208_g173381.aula3;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class FreeBlockPuzzle extends AbstractPuzzle {

    private int[] dummyCell;

    public FreeBlockPuzzle(Board board,
                           ArrayList<ImageView> imageViews) {
        super(board, imageViews);
    }

    @Override
    public void startGame() {
        super.startGame();
        int dummyImage = board.getCorrectBlock(0, 0);

        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                if (board.getGameBlock(i, j) == dummyImage) {
                    dummyCell = new int[]{i, j};
                }
            }
        }
    }

    @Override
    public void addListener(ImageView imageView, final int line, final int column) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dummyCell[0] == line || dummyCell[1] == column) {
                    System.out.println(line + " " + column);
                    board.swap(line, column, dummyCell[0], dummyCell[1]);
                }
                dummyCell[0]=line;
                dummyCell[1]=column;
                System.out.println("linha e coluna original"+line+" "+column+"Dymm"+dummyCell[0]+" "+ dummyCell[1]);
                readraW();

            }
        });
    }

    @Override
    public boolean endGame() {
        return false;
    }
}
