package by.tut.accounttests.util;

public enum BrowserType {
	
	FIREFOX("firefox"), CHROME("chrome"), IE("internet_explorer"), OPERA("opera");

	private String value;

	BrowserType(String value) {
		this.value = value;
	}

	public String getBrowserName() {
		return this.value;
	}
}
