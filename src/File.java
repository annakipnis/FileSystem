import java.time.LocalDateTime;

public class File extends AbstractFile {
	
	long _size;
	
	public File(String name, long size) {
		_name = name;
		_size = size;
		_createDate = LocalDateTime.now();
	}
	
	public String toString() {
		return _name + " [Size: " + _size + "KB, Creation Date: " + getCreationDate() + "]";
	}
	
}
