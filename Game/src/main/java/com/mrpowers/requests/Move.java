package com.mrpowers.requests;

import com.mrpowers.QueryBuilder;
import com.mrpowers.chess.*;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;

import java.lang.*;

public class Move extends RequestData {
    private String whiteUser;
    private String blackUser;
    private String from;
    private String to;
    private Boolean valid;
    private String turn;
    private String check;
    private String checkmate;
    private String fen;
    private String username;
    private String err;

    public Move(String whiteUser, String blackUser, String from, String to, String username){
        this.whiteUser=whiteUser;
        this.blackUser=blackUser;
        this.from=from;
        this.to=to;
        this.username=username;
    }
    public Move(){};


    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public String getFen(){
        return fen;
    }

    private Boolean makePiece(char pieceChar, ChessBoard board, int row, int col){
        row=7-row;
        ChessPiece piece;
        if(Character.isUpperCase(pieceChar)){
            switch(pieceChar){
                case 'P':
                    piece=new Pawn(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'B':
                    piece = new Bishop(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'R':
                    piece = new Rook(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'K':
                    piece = new King(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'Q':
                    piece = new Queen(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'N':
                    piece = new Knight(board, ChessPiece.Color.WHITE);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
            }

        }
        else{
            switch(pieceChar){
                case 'p':
                    piece=new Pawn(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'b':
                    piece = new Bishop(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'r':
                    piece = new Rook(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'k':
                    piece = new King(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'q':
                    piece = new Queen(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
                case 'n':
                    piece = new Knight(board, ChessPiece.Color.BLACK);
                    board.placePiece(piece, board.rowColToPosition(row, col));
                    return true;
            }
        }
        return false;
    }

    public ChessBoard makeBoard(String fen){
        ChessBoard board = new ChessBoard();
        int row=0;
        int column=0;
        Boolean complete=false;
        for(int i=0;i<fen.length();i++){
            Character c=fen.charAt(i);
            if(!complete){
                if(Character.isSpaceChar(c)){
                    complete=true;
                }
                else if(makePiece(c, board, row, column)){
                    column++;
                }
                else if(Character.isDigit(c)){
                    int s=Character.getNumericValue(c);
                    for(int j=0;j<s;j++){
                        column++;
                    }
                }
                else if(c.equals('/')){
                    row++;
                    column=0;
                }
            }
        }
        return board;
    }

    public Boolean Do() throws IllegalPositionException, IllegalMoveException {
        valid=false;
        QueryBuilder.connectDb();
        QueryBuilder.getDBTable();
        QueryBuilder.getStateTable();
        fen=QueryBuilder.getState(whiteUser, blackUser);
        try{
            turn=fen.substring(fen.length()-1);
        }catch(NullPointerException e){
            QueryBuilder.disconnectDb();
            return valid;
        }
        ChessBoard board=makeBoard(fen);
        if(((turn.equals("w")&&whiteUser==username)||(turn.equals("b")&&blackUser==username))){
            if((board.getPiece(from).getColor().equals(ChessPiece.Color.WHITE)&&username==whiteUser)||(board.getPiece(from).getColor().equals(ChessPiece.Color.BLACK)&&username==blackUser)){
                try{
                    board.move(from, to);
                    fen=board.toFen();
                    if(turn.equals("w")){
                        fen+="b";
                    }
                    else if(turn.equals("b")){
                        fen+="w";
                    }
                    QueryBuilder.updateState(whiteUser, blackUser, fen, turn);
                    valid=true;
                }catch(IllegalMoveException e){
                    err="move is illegal";
                }
            }
            else{
                err="its not your piece";
            }
        }
        else{
            err="its not your turn";
        }
        QueryBuilder.disconnectDb();
        System.out.println(fen+"test");
        return valid;
    }

    @Override
    public void buildResponse() throws RequestException, IllegalMoveException, IllegalPositionException {
        this.Do();
    }
}
