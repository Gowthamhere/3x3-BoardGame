package Tictactoecolors.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Stones_StateTest {


    @Test
    void testMove() {
        Stones_State state = new Stones_State();
        state.setCurrentStone(Stones.P2_GREENSTONE);
        state.move(1,2);
        assertThrows(IllegalMoveException.class, () -> state.move(1,2));
        state.setCurrentStone(Stones.P1_GREENSTONE);
        assertThrows(IllegalMoveException.class,() -> state.move(1,2));
        assertThrows(IllegalMoveException.class,() -> state.move(-100,-2));
        state.reset();
    }

    @Test
    void testCheckIfGameOver() {
        Stones_State state = new Stones_State();
        state.setCurrentStone(Stones.P1_REDSTONE);
        state.setNoOfFills(8);
        state.move(2,1);
        assertTrue(state.isGameOver());
        state.setNoOfFills(9);
        assertTrue(state.isGameOver());
        state.reset();
        state.move(2,0);
        assertFalse(state.isGameOver());

    }

    @Test
    void testCheckBounds() {
        Stones_State state = new Stones_State();
        assertTrue(state.checkBounds(0,0));
        assertTrue(state.checkBounds(0,1));
        assertTrue(state.checkBounds(0,2));
        assertTrue(state.checkBounds(1,0));
        assertTrue(state.checkBounds(1,1));
        assertTrue(state.checkBounds(1,2));
        assertTrue(state.checkBounds(2,0));
        assertTrue(state.checkBounds(2,1));
        assertTrue(state.checkBounds(2,2));
        assertFalse(state.checkBounds(0,3));
        assertFalse(state.checkBounds(3,3));
        state.reset();
    }

    @Test
    void testValidMove() {
        Stones_State state = new Stones_State();
        assertTrue(state.validMove(1,1));
        assertTrue(state.validMove(2,2));
        state.setCurrentStone(Stones.P1_YELLOWSTONE);
        state.move(1,2);
        assertFalse(state.validMove(1,2));
        state.reset();
    }
}