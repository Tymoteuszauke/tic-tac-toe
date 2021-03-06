package com.tymek.board;

import com.tymek.exceptions.AlreadyTakenPositionException;
import com.tymek.exceptions.BoardException;
import com.tymek.exceptions.DrawBesideBoardException;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Mateusz on 29.06.2017.
 */
public class Board {

    private List<BoardField> board;
    private int boardSize;
    private int boardWidth;
    private int boardHeight;

    public Board(int boardHeight, int boardWidth) {
        board = new ArrayList<>(boardHeight * boardWidth);
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        this.boardSize = boardHeight * boardWidth;
        fillBoard();
    }

    private void fillBoard() {
        IntStream.range(0, boardSize)
                .boxed()
                .forEach(data -> board.add(new BoardField(data)));
    }

    public int size() {
        return board.size();
    }

    public BoardField get(int position) {
        return board.get(position);
    }

    public void draw(String sign, int position) throws BoardException {

        try {
            if (board.get(position).isTaken())
                throw new AlreadyTakenPositionException();
            board.get(position).setSign(sign);

        } catch (IndexOutOfBoundsException e) {
            throw new DrawBesideBoardException();
        }

    }

    public void clear() {
        board = new ArrayList<>(boardSize);
        fillBoard();
    }

    public List<BoardField> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (BoardField boardField : board) {
            stringBuilder.append(boardField);
            counter++;

            if (counter == boardWidth) {
                //stringBuilder.append(System.getProperty("line.separator"));
                stringBuilder.append("\n");
                counter = 0;
            }
        }
        return stringBuilder.toString();
    }
}
