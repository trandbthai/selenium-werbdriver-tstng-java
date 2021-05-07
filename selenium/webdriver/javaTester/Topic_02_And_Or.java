package webdriver.javaTester;

public class Topic_02_And_Or {
	
	public void main(String[] args ) {
		boolean nam = true;
		boolean nu = false;
		
		// AND
		// Both need to be true to correct
		nam = true;
		nu = true;
		System.out.println(nam && nu);
		
		// OR
		// Only false when both are false
		nam = false;
		nu = false;
		System.out.println(nam || nu);
	}

}
