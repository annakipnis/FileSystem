import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractFile {
	
	protected String _name;
	protected LocalDateTime _createDate;

	public String getName() {
		return _name;
	}
	
	public String getCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return _createDate.format(formatter);
	}
}
