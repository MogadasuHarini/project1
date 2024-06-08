import java.io.*;

public class FileUtil {
    public static String readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file at " + filePath + " was not found.");
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred while reading " + filePath);
        }
        return null;
    }

    public static void main(String[] args) {
        String content = readFile("example.txt");
        if (content != null) {
            System.out.println(content);
        }
    }
}
