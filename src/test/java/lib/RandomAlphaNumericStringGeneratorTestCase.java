package lib;

import static org.junit.Assert.*;

import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext_test.xml"})
public class RandomAlphaNumericStringGeneratorTestCase {
	private final Logger log = Logger.getLogger(this.getClass());	
	
	@Test
	public void testTheTruth() {
		log.debug("sanity test");
		assertTrue("is always true", true);
	}
	
    @Test
    public void testRandomStringLengthAndAlphanumericness() {
        log.debug("testing the correctness of random string length and its alphanumericness");
        Random r = new Random();
         
        for (int i = 0 ; i < 100 ; i++) {
            int stringLength = (int) Math.ceil(r.nextDouble() * 100);
            String randomString = RandomAlphaNumericStringGenerator.generate(stringLength);
            assertEquals(stringLength, randomString.length());     
            assertTrue("random string contains only alphanumeric", randomString.matches("^[a-zA-Z0-9]*$"));
        }
    }	
}
