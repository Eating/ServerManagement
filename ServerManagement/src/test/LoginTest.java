package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import servletunit.struts.MockStrutsTestCase;



public class LoginTest extends MockStrutsTestCase {
	
	protected void setUp() throws Exception{
		super.setUp();
		setContextDirectory(new File("WebRoot"));
		setConfigFile("MyFileSystem", "src/struts.xml");
	}
	public void testSuccessfulLogin(){
		addRequestParameter("loginName","ch");
		addRequestParameter("password","hc");
		setRequestPathInfo("/login_check");
		actionPerform();
		verifyForward("MaintainerPage/Maintainer.jsp");
	}
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
