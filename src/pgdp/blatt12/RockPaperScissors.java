
package pgdp.blatt12;

public class RockPaperScissors implements Runnable {

    @Override
    public void run() {
        Player p1 = new Player();
        Player p2 = new Player();
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        t1.start();
        t2.start();
        
        int p1Wins = 0, p2Wins = 0, draws = 0;
        
        for(int i = 0; i < 1000; i++) {
            try {
                int winner = getWinner(p1.getChoice(), p2.getChoice());
                p1Wins += winner == 1 ? 1 : 0;
                p2Wins += winner == 2 ? 1 : 0;
                draws += winner == 0 ? 1 : 0;
            } catch (InterruptedException ex) {
                System.out.println("Unexpected InterruptedException");
                break;
            }
        }
        
        t1.interrupt();
        t2.interrupt();
        System.out.println("Player 1 won " + p1Wins + " times; player 2 won " + p2Wins + " times and there were " + draws + " draws.");
    }
    
    private int getWinner(int p1, int p2) {
        if(p1 == p2) return 0;
        if(p1 == 0 && p2 == 1) return 2;
        if(p1 == 0 && p2 == 2) return 1;
        if(p1 == 1 && p2 == 0) return 1;
        if(p1 == 1 && p2 == 3) return 2;
        if(p1 == 2 && p2 == 0) return 2;
        if(p1 == 2 && p2 == 1) return 1;
        return -1;
    }
    
    public static void main(String[] args) {
        new Thread(new RockPaperScissors()).start();
    }
}
