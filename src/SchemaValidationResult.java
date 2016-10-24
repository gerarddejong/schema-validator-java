
public class SchemaValidationResult {
	public boolean isValid;
	public String message;
	
	public static SchemaValidationResult validResultWithMessage(String message) {
		SchemaValidationResult result = new SchemaValidationResult();
		result.isValid = true;
		result.message = message;
		return result;
	}
	
	public static SchemaValidationResult invalidResultWithMessage(String message) {
		SchemaValidationResult result = new SchemaValidationResult();
		result.isValid = false;
		result.message = message;
		return result;
	}
}
