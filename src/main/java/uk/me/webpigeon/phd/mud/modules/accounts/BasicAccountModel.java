package uk.me.webpigeon.phd.mud.modules.accounts;

import java.util.HashMap;
import java.util.Map;

public class BasicAccountModel implements AccountModel {
	//TODO datebase this
	
	private Map<String, Account> accounts;
	
	public BasicAccountModel(){
		this.accounts = new HashMap<String, Account>();
	}
	
	/* (non-Javadoc)
	 * @see uk.me.webpigeon.phd.mud.modules.accounts.AccountModel#getAccount(java.lang.String)
	 */
	@Override
	public Account getAccount(String username) {
		return accounts.get(username);
	}

	/* (non-Javadoc)
	 * @see uk.me.webpigeon.phd.mud.modules.accounts.AccountModel#createAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public Account createAccount(String username, String password) {
		if (accounts.containsKey(username)) {
			throw new RuntimeException("that username is taken");
		}
		
		Account account = new Account(username);
		account.setPassword(password);
		accounts.put(username, account);
		
		return account;
	}

}
