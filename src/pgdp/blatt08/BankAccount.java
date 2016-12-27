package pgdp.blatt08;

public class BankAccount {
	private int bankaccount;
	private String firstname;
	private String surname;
	private Money balance;
	
	public BankAccount(int accountnumber, String fname, String sname) {
		this.bankaccount = accountnumber;
		this.firstname = fname;
		this.surname = sname;
		this.balance = new Money(0);
	}

	public int getAccountnumber() {
		return bankaccount;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public Money getBalance() {
		return balance;
	}
	
	public void addMoney(Money m) {
		balance = balance.addMoney(m);
	}
	
	public void removeMoney(Money m) {
		balance = balance.addMoney(m.negativeValue());
	}

	@Override
	public String toString() {
		return "BankAccount [bankaccount=" + bankaccount + ", firstname=" + firstname + ", surname=" + surname
				+ ", balance=" + balance + "]";
	}
}
