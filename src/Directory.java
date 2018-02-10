import java.time.LocalDateTime;
import java.util.HashMap;

public class Directory extends AbstractFile {

	HashMap<String, AbstractFile> _filesList;
	
	public Directory (String name) {
		_name = name;
		_createDate = LocalDateTime.now ();
		_filesList = new HashMap<String,AbstractFile> ();
	}
	
	public HashMap<String,AbstractFile> getFilesList () {
		return _filesList;
	}
	
	public void addFile (AbstractFile newFile) {
		_filesList.put (newFile.getName(), newFile);
	}
	
	public void delete (String name) {
		if (_filesList.containsKey (name)) {
			_filesList.remove (name);
		} 
	}
	
	public String toString () {
		return "/" + _name + " [Creation Date: " + getCreationDate() + "]";
	}
}
