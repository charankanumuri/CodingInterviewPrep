/**
 * Design In-Memory File System

Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.
 

Example 1:


Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 

Constraints:

1 <= path.length, filePath.length <= 100
path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
1 <= content.length <= 50
At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */

class FileSystem {
    
    //Trie structure with required properties
    class File{
        boolean isFile = false;
        String content="";
        
        //path can point to multiple files/directory
        HashMap<String, File> map = new HashMap<>();
    }
    
    
    File root;
    public FileSystem() {
        root = new File();
    }
    
    public List<String> ls(String path) {
        
        List<String> ls = new ArrayList<>();
        File f = root;
        
        if(!path.equals("/")){
            String[] paths = path.split("/");
            
            //we start from 1 bcz root is not a valid path, its just for reference from root
            for(int i=1;i<paths.length;i++){
                f = f.map.get(paths[i]);
            }
            
            //after reaching, check if its a file, return only file name
            if(f.isFile){
                ls.add(paths[paths.length-1]);
                return ls;
            }
        }
        
        // then we take all files from the map and return the sorted list
        List<String> all = new ArrayList<>(f.map.keySet());
        Collections.sort(all);
        return all;
    }
    
    public void mkdir(String path) {
        File f = root;
        
        String[] paths = path.split("/");
        
        for(int i=1;i<paths.length;i++){
            
            //add dir if not there
            if(!f.map.containsKey(paths[i]))
                f.map.put(paths[i], new File());
            
            f = f.map.get(paths[i]);
        }
    }
    
    //      "/a/b/c/d", "hello"
    public void addContentToFile(String filePath, String content) {
        File f = root;
        
        String[] paths = filePath.split("/");
        
        //go upto /c
        for(int i=1;i<paths.length-1;i++){
            f =f.map.get(paths[i]);
        }
        
        //check if /d is there?
        if(!f.map.containsKey(paths[paths.length-1]))
            f.map.put(paths[paths.length-1], new File());
        
        //get the file, set isFile as true and concatenate its content
        f = f.map.get(paths[paths.length-1]);
        f.isFile = true;
        f.content = f.content.concat(content);
    }
    
    public String readContentFromFile(String filePath) {
        File f = root;
        String[] paths = filePath.split("/");
        
        //go all the way down upto that dir
        for(int i=1;i<paths.length;i++){
            f = f.map.get(paths[i]);
        }
        
        //return it's content
        return f.content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */