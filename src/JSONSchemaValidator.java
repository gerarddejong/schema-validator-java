import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class JSONSchemaValidator implements SchemaValidator {
		
	@Override
	public SchemaValidationResult validate(Object object, Map<String, Object> schema) {
		if(object.getClass().equals(String.class)) {
			return this.validateString((String)object, schema);
		}
		
		return SchemaValidationResult.invalidResultWithMessage("Invalid object! Object type cannot be validated with schema.");
	}
	
	public SchemaValidationResult validateString(String stringToValidate, Map<String, Object> schema) {
		if(schema.containsKey("type") && "string".equals(schema.get("type"))) {
			if(schema.containsKey("minLength") && stringToValidate.length() < (int)schema.get("minLength")) {
				return SchemaValidationResult.invalidResultWithMessage("Too short!");
			}
			
			if(schema.containsKey("maxLength") && stringToValidate.length() > (int)schema.get("maxLength")) {
				return SchemaValidationResult.invalidResultWithMessage("Too long!");
			}
			
			if(schema.containsKey("pattern")) {
				Pattern pattern = Pattern.compile((String)schema.get("pattern"));
				Matcher matcher = pattern.matcher(stringToValidate);
				if(!matcher.find()) {
					return SchemaValidationResult.invalidResultWithMessage("Failed to match regular expression.");
				}
			}
			
			return SchemaValidationResult.validResultWithMessage("Okay!");
		}
		
		return SchemaValidationResult.invalidResultWithMessage("Invalid string! Type not defined as string in schema!");
	}
}
