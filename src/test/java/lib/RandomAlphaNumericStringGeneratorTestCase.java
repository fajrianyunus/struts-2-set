package lib;

import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsTestCase;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:/applicationContext_test.xml"})
public class RandomAlphaNumericStringGeneratorTestCase extends StrutsTestCase {
	private final Logger log = Logger.getLogger(this.getClass());	
	
	@Test
	public void testTheTruth() {
		log.debug("sanity test");
		Assert.assertTrue(true);
	}
	
    @Test
    public void testRandomStringLengthAndAlphanumericness() {
        log.debug("testing the correctness of random string length and its alphanumericness");
        Random r = new Random();
         
        for (int i = 0 ; i < 100 ; i++) {
            int stringLength = (int) Math.ceil(r.nextDouble() * 100);
            String randomString = RandomAlphaNumericStringGenerator.generate(stringLength);
            Assert.assertNotNull(randomString);
            Assert.assertTrue(randomString.matches("^[a-zA-Z0-9]{"+stringLength+"}$"));
        }
    }	
}
