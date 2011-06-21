package controllers.employee;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

public class CreateActionTestCase extends StrutsSpringTestCase {
	private final String ACTION_URL = "/employee/create.action";
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String getContextLocations() {
		return "applicationContext_test.xml";
	}
	
	@Test
	public void testTheTruth() {
		log.debug("sanity test");
		assertTrue("is always true", true);
	}
	
	@Test
	public void testGetActionMapping() {
		log.debug("testing url - namespace & action mapping");
		ActionMapping mapping = getActionMapping(ACTION_URL);
		assertNotNull(mapping);
		assertEquals("/employee", mapping.getNamespace());
		assertEquals("create", mapping.getName());
	}
	
	@Test
	public void testRejectingGetMethod() throws Exception {
		log.debug("testing the ability of the action to refuse non-post method (in this case: get)");
		settingValidParameters();
		request.setMethod("get");
		
		ActionProxy proxy = getActionProxy(ACTION_URL);
		assertNotNull(proxy);
		String result = proxy.execute();
		assertNotNull(result);
		assertEquals("error", result);
	}
	
	@Test
	public void testRejectingPutMethod() throws Exception {
		log.debug("testing the ability of the action to refuse non-post method (in this case: put)");
		settingValidParameters();
		request.setMethod("put");
		
		ActionProxy proxy = getActionProxy(ACTION_URL);
		assertNotNull(proxy);
		String result = proxy.execute();
		assertNotNull(result);
		assertEquals("error", result);
	}
	
	@Test
	public void testRejectingDeleteMethod() throws Exception {
		log.debug("testing the ability of the action to refuse non-post method (in this case: delete)");
		settingValidParameters();
		request.setMethod("delete");
		
		ActionProxy proxy = getActionProxy(ACTION_URL);
		assertNotNull(proxy);
		String result = proxy.execute();
		assertNotNull(result);
		assertEquals("error", result);
	}
	
	@Test
	public void testAcceptingPostMethod() throws Exception {
		log.debug("testing the ability of the action to refuse non-post method (in this case: post, so there should be NO problem)");
		settingValidParameters();
		request.setMethod("post");
		
		ActionProxy proxy = getActionProxy(ACTION_URL);
		assertNotNull(proxy);
		String result = proxy.execute();
		assertNotNull(result);
		assertEquals("none", result);		
	}
	
	private void settingValidParameters() {
		request.setParameter("fullName", "gurmit singh");
		request.setParameter("nric", "s1234567x");
		request.setParameter("remarks", "<h1 style='color:blue;'>a.k.a phua chu kang</h1>");		
	}
}
