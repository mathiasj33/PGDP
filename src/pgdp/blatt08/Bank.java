package pgdp.blatt08;

public class Bank {

    private BankAccountList accounts;

    public int newAccount(String firstname, String lastname) {
        int id = accounts == null ? 0 : accounts.getLastID() + 1;
        BankAccount acc = new BankAccount(id, firstname, lastname);
        if (accounts == null) {
            accounts = new BankAccountList(acc, null);
        } else {
            accounts.add(acc);
        }
        return acc.getAccountnumber();
    }
    
    public void removeAccount(int accountnumber) {
        if (accounts == null)
            return;
        BankAccount acc = accounts.getAccount(accountnumber);
        if (acc == null)
            return;
        if (!accounts.remove(acc)) {
            removeFirstAccount();
        }
    }

    private void removeFirstAccount() {
        accounts = accounts.next;
    }

    public Money getBalance(int accountnumber) {
        BankAccount acc = accounts.getAccount(accountnumber);
        if (acc == null)
            return null;
        return acc.getBalance();
    }

    public boolean depositOrWithdraw(int accountnumber, Money m) {
        BankAccount acc = accounts.getAccount(accountnumber);
        if (acc == null)
            return false;
        acc.addMoney(m);
        return true;
    }

    public boolean transfer(int from, int to, Money m) {
        BankAccount fromAcc = accounts.getAccount(from);
        BankAccount toAcc = accounts.getAccount(to);
        if (fromAcc == null || toAcc == null)
            return false;

        fromAcc.removeMoney(m);
        toAcc.addMoney(m);
        return true;
    }

    @Override
    public String toString() {
        if (accounts == null)
            return "{}";
        return accounts.toString();
    }

    private class BankAccountList {

        public BankAccount info;
        public BankAccountList next;

        public BankAccountList(BankAccount info, BankAccountList next) {
            this.info = info;
            this.next = next;
        }

        public BankAccount getAccount(int accountnumber) {
            BankAccountList next = this;
            while (next != null) {
                if (next.info.getAccountnumber() == accountnumber)
                    return next.info;
                next = next.next;
            }
            return null;
        }

        public void add(BankAccount acc) {
            getLast().next = new BankAccountList(acc, null);
        }

        /**
         * @return returns false if the account is the first one in the list
		 *
         */
        public boolean remove(BankAccount acc) {
            BankAccountList prev = null;
            BankAccountList next = this;
            while (next != null) {
                if (next.info.getAccountnumber() == acc.getAccountnumber()) {
                    if (prev == null) {
                        return false;
                    }
                    prev.next = next.next;
                    break;
                }
                prev = next;
                next = next.next;
            }
            return true;
        }

        public int getLastID() {
            int max = 0;
            BankAccountList next = this;
            while (next != null) {
                if (next.info.getAccountnumber() > max)
                    max = next.info.getAccountnumber();
                next = next.next;
            }
            return max;
        }

        private BankAccountList getLast() {
            BankAccountList next = this;
            while (next.next != null) {
                next = next.next;
            }
            return next;
        }

        @Override
        public String toString() {
            String result = "{";
            BankAccountList next = this;
            while (next != null) {
                result += next.info + ", ";
                next = next.next;
            }
            result += "}";
            return result;
        }
    }
}
