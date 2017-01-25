package pgdp.blatt12;

public class Sessellieferant extends Thread {

    public int id;
    private Sesselfabrik sf;
    private int anzahl;

    public Sessellieferant(int id, Sesselfabrik sf, int anzahl) {
        this.id = id;
        this.sf = sf;
        this.anzahl = anzahl;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < anzahl; i++) {
                sf.sesselBestellen(id);
            }
            sf.rampeBelegen(id);
            for(int i = 0; i < anzahl; i++) {
                sf.sesselAbholen(id);
            }
            sf.rampeFreigeben(id);
            System.out.println(id + " hat alle Sessel und ist zufrieden.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Sesselfabrik sf = new Sesselfabrik();
        sf.setDaemon(true);
        Sessellieferant t1 = new Sessellieferant(1, sf, 4);
        Sessellieferant t2 = new Sessellieferant(2, sf, 3);
        Sessellieferant t3 = new Sessellieferant(3, sf, 4);
        Sessellieferant t4 = new Sessellieferant(4, sf, 4);
        sf.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
