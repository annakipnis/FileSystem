
public class main {

	public static void main(String[] args) {
		FileSystem fileSystem = new FileSystem("root");
		try {
			fileSystem.addDir ("root", "User");
			fileSystem.addDir ("User", "annak");
			fileSystem.addFile("annak", "A", 1000);
			fileSystem.addFile("annak", "B", 2000);
			fileSystem.addFile("annak", "C", 300);
			fileSystem.addDir ("User", "anna");
			fileSystem.addDir ("anna", "D");
			fileSystem.showFileSystem();
			System.out.println("________________________________________");
			fileSystem.delete("annak");
			fileSystem.delete("D");
			fileSystem.addDir("User", "annak");
			fileSystem.addDir("annak", "A");
			fileSystem.addDir("A", "B");
			fileSystem.addFile("B", "B0", 2000);
			fileSystem.addFile("B", "B1", 2000);
			fileSystem.addDir("A", "B2");
			fileSystem.addDir("B", "C");
			fileSystem.addDir("anna", "E");		
			fileSystem.delete("C");
			fileSystem.showFileSystem();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
