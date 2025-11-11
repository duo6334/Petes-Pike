package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import petespike.model.*;
import petespike.view.AsciiColorCodes;

public class PetesPikeTest {

    @Test
    public void PetesPikeConstructorTest(){
        try{
        PetesPike game = new PetesPike("data/petes_pike_5_5_2_0.txt");
        String expected ="-"+AsciiColorCodes.BLUE+"G"+AsciiColorCodes.RESET+"--"+AsciiColorCodes.GOLD+"P"+AsciiColorCodes.RESET+"\n-----\n--T--\n--"+AsciiColorCodes.GREEN+"G"+AsciiColorCodes.RESET+"--\n-----\n";
        String actual = game.toString();
        assert actual.equals(expected);
        }catch(PetesPikeException e){
            System.out.println(e.getMessage());
        }

        
    }

    @Test
    public void getPossibleMovesTest(){
        try{
        PetesPike game = new PetesPike("data/petes_pike_5_5_2_0.txt");
        List<Move> expectedList = new ArrayList<>();
        expectedList.add(new Move(new Position(0,1),Direction.RIGHT));
        expectedList.add(new Move(new Position(0,4),Direction.LEFT));

        List<Move> actualList = game.getPossibleMoves();
        for (int i = 0; i < actualList.size(); i++) {
            String actual = actualList.remove(0).toString();
            String expected = expectedList.remove(0).toString();
            assert actual.equals(expected);
        }
        
        }catch(PetesPikeException e){
            System.out.println(e.getMessage());
        }
    }
}
