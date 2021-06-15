package pk4;

public class Account {
    private int accountID, balance;

    public Account(int accountID, int balance) {
        this.accountID = accountID;
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + "]";
	}
    
    
}