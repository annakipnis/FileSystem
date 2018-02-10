# FileSystem

The file system contains the following entities and operations:

File:

1. name - up to 32 characters long
2. size - positive long integer
3. create date (date type)

Directory:

1. name - up to 32 characters long
2. create date (date type)
3. A directory can contain directories or files

Functionalities:

1. addFile (string parentDirName, String fileName, integer fileSize) - Adds new File under the Directory branch
2. addDir (string parentDirName, String dirName) - Adds new Directory under the parent Directory
3. delete (string name) - Deletes the Directory or the File with this name
4. showFileSystem () - Displays all files & directories with their hierarchical structure 


* Each name, file or directory is unique in the file system
* Each directory can contain an unlimited number of files or directories
