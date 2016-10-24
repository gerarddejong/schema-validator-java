import org.junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class SchemaValidatorStringTests {
	private static SchemaValidator validator;
	
    @Before
    public void setUp() {
    	validator = new JSONSchemaValidator();
    }

	@Test
	public void testStringValidation() {
		Map<String, Object> schema = new HashMap<String, Object>() {{ put("type","string"); }};
		
		assertTrue(validator.validate("This is a string", schema).isValid);
		assertTrue(validator.validate("Déjà vu", schema).isValid); // Unicode characters
		assertTrue(validator.validate("42", schema).isValid);
		assertFalse(validator.validate(42, schema).isValid);
	}

	@Test
	public void testStringLengthValidation() {
		Map<String, Object> schema = new HashMap<String, Object>() {{ 
			put("type", "string");
			put("minLength", 2);
			put("maxLength", 3);	
		}};
		
		assertFalse(validator.validate("A", schema).isValid);
		assertTrue(validator.validate("AB", schema).isValid);
		assertTrue(validator.validate("ABC", schema).isValid);
		assertFalse(validator.validate("ABCD", schema).isValid);
	}
	
	@Test
	public void testStringRegularExpressionValidation() {
		Map<String, Object> schema = new HashMap<String, Object>() {{ 
			put("type", "string");
			put("pattern", "^(\\([0-9]{3}\\))?[0-9]{3}-[0-9]{4}$");
		}};
		
		assertTrue(validator.validate("555-1212", schema).isValid);
		assertTrue(validator.validate("(888)555-1212", schema).isValid);
		assertFalse(validator.validate("(888)555-1212 ext. 532", schema).isValid);
		assertFalse(validator.validate("(800)FLOWERS", schema).isValid);
	}
}
