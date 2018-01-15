/*пример годного файла лежит в корне программы*/

import java.io.FileWriter;
import java.io.FileReader;

public class MainDevParsTxt {


    static String nameOfTXTFile =
            //            "//Users//mackret//Downloads/dev.txt";
            "C://Users//SBT-Kretov-AA//Downloads/12_dev.txt";
    static String textFromTXTFile = "";

    public static void main (String[]xxx) {

        String regexBegin = "Цуп/Тск/Sm";
        String regex1 = "CRM-";
        String regex2 = "Проблем:";
        String regex3 = "Обновить";
        String regexEnd = "Нравится";

//из тексового файла все переводим в тектовую переменную строку
        try {
            FileReader fr = new FileReader(nameOfTXTFile);
            int c;
            while ((c = fr.read()) != -1) {
                textFromTXTFile += ((char) c);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(textFromTXTFile);


//разбиваем строку по символу начала новой строки
String[]buffArrFromTXT = textFromTXTFile.split("\n");

for (int i = 0; i < buffArrFromTXT.length; i ++) {

    if (buffArrFromTXT[i].contains(regexBegin)) {
        continue;
    } else {
        System.out.println(buffArrFromTXT[i]);
    }
}


    } //end of method

} //end of class
