import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class FileSystem {

	public Directory _root;
	public HashMap<String, AbstractFile> _names; //Key=file or directory name, Value=file or directory object
	public HashMap<String, String> _parentDirNames; //Key=file or directory name, Value=parent directory name
	
	public FileSystem (String rootName) {
		_root = new Directory (rootName);
		_names = new HashMap<String, AbstractFile>();
		_parentDirNames = new HashMap<String, String>();
		addToNamesMap("", rootName, _root);
	}
	
	public void addFile (String parentDirName, String fileName, long fileSize) throws FileSystemException {
		addToFileSystem(parentDirName, fileName, fileSize, false);
	}

	public void addDir (String parentDirName, String dirName) throws FileSystemException {
		addToFileSystem(parentDirName, dirName, 0, true);
	}
	
	public void delete (String fileName) throws FileSystemException {
		if (!_names.containsKey(fileName)) {
			throw new FileSystemException("A file/directory with this name (" + fileName + ") does not exist.");
		}
		
		String parentDirName = _parentDirNames.get(fileName);
		if (!parentDirName.isEmpty()) {
			AbstractFile parentDir = _names.get(parentDirName);
			((Directory) parentDir).delete(fileName);
			//remove from maps
			_names.remove(fileName);
			_parentDirNames.remove(fileName);
			deleteSubFiles(fileName);
		} else {
			//only root has empty parent
			throw new FileSystemException("You cannot delete root directory.");
		}
	}

	public void showFileSystem () {
		showInfo (0, _root);
	}
	
	private void showInfo (int spacing, AbstractFile file) {
		for (int i = 0; i < spacing; i++) {
		      System.out.print (' ');
		}
		System.out.println (file.toString ());
		if (file instanceof Directory) {
			HashMap<String,AbstractFile> filesList = ((Directory) file).getFilesList();
			for (AbstractFile subfile : filesList.values ()) {
				showInfo (spacing + 4, subfile);
			}
		}
	}
	
	private void addToFileSystem (String parentDirName, String fileName, long fileSize, boolean isDirectory) throws FileSystemException {
		validateFile(parentDirName, fileName, fileSize, isDirectory);
		
		AbstractFile parentDir = _names.get(parentDirName);
		if (parentDir instanceof Directory) {
			AbstractFile newFile;
			if (isDirectory) {
				newFile = new Directory(fileName);
			} else {
				newFile = new File(fileName, fileSize);
			}
			((Directory) parentDir).addFile(newFile);
			addToNamesMap(parentDirName, fileName, newFile);
		} else {
			throw new FileSystemException("Parent directory name that was specified (" + parentDirName + ") is not a directory.");
		}
	}
	
	private void addToNamesMap (String parentDirName, String fileName, AbstractFile file) {
		_names.put(fileName, file);
		_parentDirNames.put(fileName, parentDirName);
	}
	
	private void validateFile (String parentDirName, String fileName, long fileSize, boolean isDirectory) throws FileSystemException {
		List<String> errors = new ArrayList<String>();
		
		if (_names.containsKey(fileName)) {
			errors.add("A file/directory with this name (" + fileName + ") already exists.");
		}
		if (!_names.containsKey(parentDirName)) {
			errors.add("Parent directory (" + parentDirName + ") does not exist.");
		}
		if (fileName.length() > 32 || fileName.isEmpty()) {
			errors.add("File/directory name should be between 1 to 32 characters long.");
		}
		if (!isDirectory && fileSize <= 0) {
			errors.add("File size (" + fileSize + ") should be a positive number.");
		}
		
		if (!errors.isEmpty()) {
			throw new FileSystemException(String.join ("\n", errors));
		}
	}
	
	private void deleteSubFiles(String fileName) {
		for (Entry<String, String> entry : _parentDirNames.entrySet()) {
			String subFileName = entry.getKey();
			String parentDirName = entry.getValue();
			if (parentDirName.equals(fileName)) {
				deleteSubFiles(subFileName);
				_names.remove(subFileName);
			}
		}
	}
}
