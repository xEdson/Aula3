package br.unicamp.ft.e196208_g173381.aula3;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    public int getNumLines() {
        return numLines;
    }

    public void setNumLines(int numLines) {
        this.numLines = numLines;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public ArrayList<Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Integer> blocks) {
        this.blocks = blocks;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Integer> getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(ArrayList<Integer> gameIndex) {
        this.gameIndex = gameIndex;
    }

    private int numLines;
    private int numColumns;
    private ArrayList<Integer> blocks;
    private int width;
    private int height;
    private ArrayList<Integer> gameIndex;

    public Board(int numLines, int numColumns, ArrayList<Integer> blocks, int width, int height) {
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.blocks = blocks;
        this.width = width;
        this.height = height;
        gameIndex = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            gameIndex.add(i);
        }
        this.startGame();
    }


    public void startGame() {
        Collections.shuffle(this.gameIndex);
    }

    public int getCorrectBlock(int line, int column) {

        return blocks.get(line * numColumns + column);

    }

    public int getGameBlock(int line, int column) {


        int i = gameIndex.get(line * numColumns + column);
        return blocks.get(i);

    }

    public void swap(int line1, int column1, int line2, int column2) {
        int intermediario;

        intermediario = gameIndex.get(line1 * numColumns + column1);
        gameIndex.set(line1 * numColumns + column1, gameIndex.get(line2 * numColumns + column2));
        gameIndex.set(line2 * numColumns + column2, intermediario);

    }

}
