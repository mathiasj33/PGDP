package pgdp.blatt08;

public class Test {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.newAccount("Mathias", "Jackermeier");
        bank.newAccount("Hurz", "Hurtig");
        System.out.println(bank);
        bank.depositOrWithdraw(0, new Money(500));
        bank.depositOrWithdraw(1, new Money(500));
        bank.transfer(0, 1, new Money(200));
        System.out.println(bank);
    }
}
