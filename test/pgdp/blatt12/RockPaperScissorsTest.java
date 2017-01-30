/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgdp.blatt12;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static pgdp.blatt12.RockPaperScissors.getWinner;
/**
 *
 * @author Mathias
 */
public class RockPaperScissorsTest {

    @Test
    public void determineWinner_givenSame_return0() throws Exception {
        Assert.assertEquals(0, getWinner(0, 0));
        Assert.assertEquals(0, getWinner(1, 1));
        Assert.assertEquals(0, getWinner(2, 2));
    }

    @Test
    public void determineWinner_given1Wins_return1() throws Exception {
        Assert.assertEquals(1, getWinner(1, 0));
        Assert.assertEquals(1, getWinner(2, 1));
        Assert.assertEquals(1, getWinner(0, 2));
    }

    @Test
    public void determineWinner_given2Wins_return2() throws Exception {
        Assert.assertEquals(2, getWinner(0, 1));
        Assert.assertEquals(2, getWinner(1, 2));
        Assert.assertEquals(2, getWinner(2, 0));
    }

    @Test(timeout = 1500)
    public void run_1Game() throws Exception {
        Thread game = new Thread(new RockPaperScissors());
        game.start();
        game.join();
        Assert.assertFalse(game.isAlive());
    }

    @Test(timeout = 15000)
    @Ignore
    public void run_10Games() throws Exception {
        for (int i = 0; i <= 10; i++) {
            run_1Game();
        }
    }

}
