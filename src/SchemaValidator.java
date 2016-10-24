import java.util.Map;;

public interface SchemaValidator {
	public SchemaValidationResult validate(Object object, Map<String, Object>schema);
}
