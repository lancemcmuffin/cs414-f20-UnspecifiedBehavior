package com.mrpowers;
import com.mrpowers.exceptions.IllegalMoveException;
import com.mrpowers.exceptions.IllegalPositionException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.mrpowers.chess.*;
import com.mrpowers.requests.*;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTests {

/*
    @BeforeAll

    public void Before() {
        Boolean CI = System.getenv("CI").equals("true");
        if (CI) {
            System.out.println(CI);
        }

    }*/

/*
    @Test
    public void NewUserTest(){
        NewUser aUser=new NewUser("user5", "password", "user@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        UserData d=new UserData("user");
        try{
            d.buildResponse();
            if(d.getGamesLost()!=0){
                fail();
            }
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void MoveTest() throws IllegalPositionException {
        Move aMove=new Move();
        String fen0 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String fen1 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        ChessBoard board0=aMove.makeBoard(fen0);
        ChessBoard board1=aMove.makeBoard(fen1);
        if(board0.getPiece("c2").legalMoves().size()!=4){
            fail();
        }
        if(board1.getPiece("e4").legalMoves().size()!=3){
            fail();
        }
    }
    @Test
    public void MessagesTest() throws RequestException {
        NewUser aUser=new NewUser("to", "password", "to@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        aUser=new NewUser("from", "password", "from@mail.com");
        try {
            aUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        try{
            DeleteMessage del=new DeleteMessage("to");
            assertTrue(del.DeleteAll());
        }catch (Exception e){
            fail();
        }
        DeleteMessage d=new DeleteMessage("to");
        d.DeleteAll();
        MatchRequest invite=new MatchRequest("to", "from");
        System.out.println(invite.getErr());
        invite.Do();
        CheckMessages cm=new CheckMessages("to");
        Boolean check=cm.Check();
        if(!check){fail();}
        GetMessage m = new GetMessage("from", "to");
        try{
            String message=m.Get();
            if(!message.equals("INVITATION")){
                fail();
            }
            else{System.out.println("test");}
        }
        catch(RequestException e){
            fail();
        }
    }
    @Test
    public void MessageTest2() throws IllegalMoveException, RequestException {
        NewUser aUser=new NewUser("to2", "password", "to@mail.com");
        NewUser bUser=new NewUser("from0", "password", "to@mail.com");
        NewUser cUser=new NewUser("from1", "password", "to@mail.com");
        try {
            aUser.buildResponse();
            bUser.buildResponse();
            cUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        DeleteMessage d=new DeleteMessage("to2");
        d.DeleteAll();
        MatchRequest m=new MatchRequest("to2", "from0");
        m.buildResponse();
        m=new MatchRequest("to2", "from1");
        m.buildResponse();
        GetAllMessages gal=new GetAllMessages("to2");
        gal.buildResponse();
        String[][] mur=gal.GetData();
        assertTrue(mur[1][0].equals("INVITATION"));
        assertTrue(mur[0][0].equals("from1")||mur[0][1].equals("from1"));
    }
    @Test
    public void NewMatchTest() throws IllegalMoveException, RequestException {
        NewUser aUser=new NewUser("to2", "password", "to@mail.com");
        NewUser bUser=new NewUser("from0", "password", "to@mail.com");
        try {
            aUser.buildResponse();
            bUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        NewChessMatch ncm=new NewChessMatch("to", "from0");
        try{
            ncm.buildResponse();
            assertTrue(ncm.getFen().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0"));
        }catch(Exception e){
            fail();
        }
        Concede c=new Concede("to2");
        assertTrue(c.RemoveAllGames());
    }
    @Test
    public void MoveTest2() throws IllegalMoveException, RequestException {
        NewUser aUser=new NewUser("to2", "password", "to@mail.com");
        NewUser bUser=new NewUser("from0", "password", "to@mail.com");
        try {
            aUser.buildResponse();
            bUser.buildResponse();
        }catch(RequestException | IllegalMoveException e){
            System.out.println("error");
        }
        MatchRequest mr=new MatchRequest("to2", "from0");
        mr.buildResponse();
        NewChessMatch ncm=new NewChessMatch("to", "from0");
        ncm.buildResponse();
        assertTrue(ncm.getFen().equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - - 0"));
        Move aMove=new Move(ncm.getWhiteUser(), ncm.getBlackUser(), "c2", "c3");
        assertTrue(aMove.Do());

        Concede c=new Concede("to2");
        assertTrue(c.RemoveAllGames());
    }
*/
}