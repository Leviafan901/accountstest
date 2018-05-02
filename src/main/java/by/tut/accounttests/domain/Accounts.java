package by.tut.accounttests.domain;

import java.util.ArrayList;
import java.util.List;

public class Accounts {

	private List<UserAccount> accountList = new ArrayList<UserAccount>();

	public Accounts() {

	}

	public Accounts(List<UserAccount> accountList) {
		this.accountList = accountList;
	}

	public List<UserAccount> getAccountList() {
		return this.accountList;
	}

	public void setAccountList(List<UserAccount> accountList) {
		this.accountList = accountList;
	}

	public boolean add(UserAccount userAccount) {
		return this.accountList.add(userAccount);
	}

	public boolean addAll(List<UserAccount> userAccountList) {
		return this.accountList.addAll(userAccountList);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountList == null) ? 0 : accountList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Accounts other = (Accounts) obj;
		if (accountList == null) {
			if (other.accountList != null) {
				return false;
			}
		} else if (!accountList.equals(other.accountList)) {
			return false;
		}
		return true;
	}
}
