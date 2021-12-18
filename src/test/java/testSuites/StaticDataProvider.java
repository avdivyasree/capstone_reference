package testSuites;


import org.testng.annotations.DataProvider;

public class StaticDataProvider {
	
	@DataProvider(name ="invalidLogin")
	public static Object[][] param_il() {
		return new Object[][] {
			// wrong id, correct pass
			// correct id, wrong pass
			{ "wrong@simplilearn.com", "testTEST!" },
			{ "testuser1@simplilearn.com", "WRONGPass!" }
		};
	}
	
	@DataProvider(name ="validUserLogin")
	public static Object[][] param_ul() {
		return new Object[][] {
			// id, pass, fullname
			{ "testuser1@simplilearn.com" , "testTEST!", "Test User1" }			
		};
	}

	@DataProvider(name ="validAdminLogin")
	public static Object[][] param_al() {
		return new Object[][] {
			// id, pass, fullname
			{ "vk@gmail.com" , "admin", "Vikas Kashyap" }			
		};
	}

	@DataProvider(name ="buy2Products")
	public static Object[][] param_b2() {
		return new Object[][] {
			// login, pass, product name 1, product name 2
			{ "testuser1@simplilearn.com" , "testTEST!", "Combiflame" , "Amoxicillin" }			
		};
	}	
	
}
