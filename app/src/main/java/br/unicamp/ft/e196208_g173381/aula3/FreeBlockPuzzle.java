package br.unicamp.ft.e196208_g173381.aula3;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
            public void onClick(View view) {

                if (line == dummyCell[0] || column == dummyCell[1]) {

                    if (line == dummyCell[0]) {
                        for (int i = dummyCell[1]; i > column; i--) {
                            board.swap(line, i, line, i - 1);
                        }
                        for (int i = dummyCell[1]; i < column; i++) {
                            board.swap(line, i, line, i + 1);
                        }
                    }
                    if (column == dummyCell[1]) {
                        for (int i = dummyCell[0]; i > line; i--) {
                            board.swap(i, column, i - 1, column);
                        }
                        for (int i = dummyCell[0]; i < line; i++) {
                            board.swap(i, column, i + 1, column);
                        }
                    }

                    dummyCell[0] = line;
                    dummyCell[1] = column;
                    readraW();
                    if(endGame()){
                        Toast toast = Toast.makeText(view.getContext(), "PARABÈNS VOCÊ ACABOU O JOGO, CLIQUE NO BOTAO PARA REINICIAR", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }


            }
        });
    }

    @Override
    public boolean endGame() {
        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                boolean fim = board.getCorrectBlock(i, j) == board.getGameBlock(i, j);
                if (fim == false)
                    return false;
            }
        }
        return true;
    }
}
