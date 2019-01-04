public class Main {

    public static void main(String[] args) {

        String filePath = "D:\\TextFile.txt";
        FileManager filemanager = new FileManager();
        filemanager.changeLine(filePath);
        filemanager.changeWord(filePath);
    }
}