package pgdp.blatt12;

public class Sesselfabrik extends Thread {

    private int sesselBestellbar = 0;
    private int lagerPlaetzeFrei = 20;
    private boolean rampeBelegt = false;
    // synchronize variable sesselBestellbar
    private final Object lockSessel = new Object();
    // synchronize variable lagerPlaetzeFrei
    private final Object lockLager = new Object();
    // synchronize variable rampeBelegt
    private final Object lockRampe = new Object();

    @Override
    public void run() {
        try {
            while (true) {
                sesselEinlagern();
            }
        } catch (Exception e) {
            if (!(e instanceof InterruptedException))
                e.printStackTrace();
        }
    }

    private void sesselEinlagern() throws InterruptedException {
        synchronized (lockLager) {
            System.out.println("Fabrik will einlagern. Freie Lagerplätze: " + lagerPlaetzeFrei);
            while (lagerPlaetzeFrei == 0) {
                System.out.println("Warten, dass Lager frei wird.");
                lockLager.wait();
            }
            System.out.println("Sessel eingelagert.");
            lagerPlaetzeFrei--;
            synchronized (lockSessel) {
                sesselBestellbar++;
                lockSessel.notify();
            }
        }
    }

    public void sesselBestellen(int id) throws InterruptedException {
        synchronized (lockSessel) {
            System.out.println(id + " will einen Sessel bestellen. " + sesselBestellbar + " Sessel sind bestellbar.");
            while (sesselBestellbar <= 0) {
                System.out.println(id + " muss mit dem Bestellen warten.");
                lockSessel.wait();
            }
            System.out.println(id + " hat einen Sessel bestellt.");
            sesselBestellbar--;
        }
    }

    public void sesselAbholen(int id) {
        synchronized (lockLager) {
            System.out.println(id + " hat einen Sessel abgeholt.");
            lagerPlaetzeFrei++;
            lockLager.notify();
        }
    }

    public void rampeBelegen(int id) throws InterruptedException {
        synchronized (lockRampe) {
            System.out.println(id + " will die Rampe belegen.");
            while (rampeBelegt) {
                System.out.println(id + " muss warten, bis die Rampe frei wird.");
                lockRampe.wait();
            }
            rampeBelegt = true;
        }
    }

    public void rampeFreigeben(int id) {
        synchronized (lockRampe) {
            System.out.println(id + " hat die Rampe freigegeben.");
            rampeBelegt = false;
            lockRampe.notify();
        }
    }
}
