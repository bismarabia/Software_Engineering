package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ClassStats {

    private String file_name;

    public ClassStats(String file_name) {
        this.file_name = file_name;
    }

    public int count_lines(){
        BufferedReader bufferedReader;
        FileReader fileReader;
        int count = 0;
        try {
            fileReader = new FileReader(file_name);
            String line;
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.trim().isEmpty())
                    continue;
                if (!line.startsWith("//"))
                    count++;
                if (line.startsWith("/*"))
                    while (!line.endsWith("*/"))
                        line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int count_methods() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        int count = 0;

        try {
            fileReader = new FileReader(file_name);
            String line;
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
                if (line.matches("\\s*(public|private|protected)*\\s*(final|static)*\\s*" +
                        "(int|double|float|String|void).+[(].*[)].*"))
                    count++;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return count;
    }

    String file_name(){

        String class_name = "";
        String[] line_in_words;

        try {

            FileReader fileReader = new FileReader(file_name);
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {

                line_in_words = line.replaceAll("\\s+", " ").trim().split(" ");
                if (line.matches(".*class.*")) {
                    class_name = line_in_words[Arrays.asList(line_in_words).indexOf("class")+1]+".java";
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return class_name;
    }

    public float method_average(){

        int count_up_to_first_method = 0;

        try {

            FileReader fileReader = new FileReader(file_name);
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.equals(""))
                    continue;

                if (!line.matches("\\s*(public|private|protected)*\\s*(final|static)*\\s*(void)*\\s*" +
                        "(int|double|float|String).+[(].*[)].*")) {
                    if (!line.startsWith("//"))
                        count_up_to_first_method++;

                    if (line.startsWith("/*"))
                        while (!line.endsWith("*/"))
                            line = bufferedReader.readLine();
                } else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (count_lines() - count_up_to_first_method -1 - 2*count_methods())
                /(float)(count_methods());
    }

}
