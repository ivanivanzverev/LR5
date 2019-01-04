import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileManager {

    Vector<String> fileContentLine = new Vector<String>();

    boolean readFile(String filePath){
        InputStream in = null;

        try {
            in = new FileInputStream(filePath);
        } catch(FileNotFoundException e){
            System.out.println("Файл не найден!");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = br.readLine()) != null){
                fileContentLine.add(line);
            }
            in.close();
        } catch (IOException e){
            System.out.println("Ошибка ввода!");
        }

        return true;
    }

    boolean writeFile(String filePath){
        OutputStream out;
        try{
            out = new FileOutputStream(filePath,false);
        } catch(FileNotFoundException e){
            return false;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));


        try {
            for (String s:fileContentLine) {
                bw.write(s+"\r\n");
            }
            bw.flush();
        }catch (IOException e){
            return false;
        }

        fileContentLine.clear();

        return true;
    }

    boolean changeLine(String filePath){
        System.out.println("--Функция замены строки--\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите номер строки, которую вы хотите заменить");
        int stringNum=scan.nextInt();
        System.out.println("Введите строку, которую вы хотите вставить");
        scan.nextLine();
        String newString = scan.nextLine();

        readFile(filePath);
        fileContentLine.set(stringNum,newString);
        writeFile(filePath);

        System.out.println("--Успешно!--\n");
        return true;
    }

    boolean changeWord(String filePath){
        System.out.println("--Функция замены слова--\n");
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите номер строки, слово в которой вы хотите заменить");
        int stringNum = scan.nextInt();
        System.out.println("Введите номер слова, которое вы хотите заменить");
        int wordNum = scan.nextInt();
        System.out.println("Введите слово, которое вы хотите вставить");
        String newWord = scan.next();

        readFile(filePath);

        String[] words = fileContentLine.elementAt(stringNum).split(" ");
        words[wordNum] = " "+newWord+" ";
        StringBuilder builder = new StringBuilder();
        for(String w:words){
            builder.append(w);
        }
        fileContentLine.set(stringNum,builder.toString());

        writeFile(filePath);
        System.out.println("--Успешно!--\n");
        return true;
    }
}
