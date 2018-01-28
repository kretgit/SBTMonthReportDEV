/*пример годного файла лежит в корне программы*/

import java.io.FileWriter;
import java.io.FileReader;

public class MainDevParsTxt {


    static String nameOfTXTFile =
                        "//Users//mackret//Downloads/12_dev.txt";
            //"C://Users//SBT-Kretov-AA//Downloads/12_dev.txt";

    static String textFromTXTFile = "";
    static int empCount = 0;
    static String stringBuff = "";

    public static void main (String[]xxx) {

        String regexBegin = "Цуп/Тск/Sm";
        String regexCRM = "CRM-";
        String regexTab = "\t";
        String regex2 = "Проблем:";
        String regex3 = "Обновить";

//из тексового файла все переводим в текстовую переменную строку
        try {
            FileReader fr = new FileReader(nameOfTXTFile);
            int c;
            while ((c = fr.read()) != -1) {
                textFromTXTFile += ((char) c);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //System.out.println(textFromTXTFile);


//разбиваем строку в массив по символу начала новой строки
String[]buffArrFromTXT = textFromTXTFile.split("\n");

        int indexTab = 0;
        int indexRegex2 = 0;
        int indexRegex3 = 0;

for (int i = 0; i < buffArrFromTXT.length; i ++) {

    if (buffArrFromTXT[i].contains(regexBegin)) {
        continue;
    } else {
//обработка остатка строки от "CRM-"
        if (buffArrFromTXT[i].contains(regexCRM)) {

            indexTab = buffArrFromTXT[i].indexOf(regexTab);

                if (buffArrFromTXT[i].contains(regex2)) {
                    indexRegex2 = buffArrFromTXT[i].indexOf(regex2);
                    indexRegex3 = buffArrFromTXT[i].indexOf(regex3);
                    buffArrFromTXT[i] = buffArrFromTXT[i].replaceAll((buffArrFromTXT[i].substring(indexRegex2, indexRegex3 + regex3.length())), "");
                }

            buffArrFromTXT[i] = buffArrFromTXT[i].substring(0, indexTab) + regexTab + "(" + buffArrFromTXT[i].substring(indexTab + regexTab.length()) + ")";
            //System.out.println(buffArrFromTXT[i]);
        }
//убираем пустые дефекты без привязки к запросам из СМ и корректируем некоторые неровности
        if (buffArrFromTXT[i].contains("()") ) {
            continue;
        } else {
           // System.out.println(buffArrFromTXT[i]);
            buffArrFromTXT[i] = buffArrFromTXT[i].replace(" )",")");
            buffArrFromTXT[i] = buffArrFromTXT[i].replace("\t("," (");
            stringBuff+=buffArrFromTXT[i] + "\n";
        }

//получаем количество разработчиков
            if (buffArrFromTXT[i].contains(regexCRM) || buffArrFromTXT[i].length()==0 /*|| buffArrFromTXT[i].contains("Double")*/) {
                continue;
            } else { empCount++; }

    }
}

        //System.out.println(empCount);
        //System.out.println(stringBuff);

//обратно строку в массив

    String[]stringBuffArr = stringBuff.split("\n");



//ищем дубли в массиве - типа одинаковые задачи
    int countDoubles = 0;
    String strDoubles = "";


    for (int i = 0; i < stringBuffArr.length; i++) {
        if (stringBuffArr[i].length() != 0) {

            for (int j = 0; j < stringBuffArr.length; j++) {
                if (stringBuffArr[j].length() != 0) {


                    if (j != i && stringBuffArr[j].equals(stringBuffArr[i])) {

                        //stringBuffArr[i] = "Double" + countDoubles + " " + stringBuffArr[i];
                    //чтоб строка с дублями содержала только уникальные значения
                        if (strDoubles.contains(stringBuffArr[i])) {
                            continue;
                        } else {
                            countDoubles++;
                            strDoubles += stringBuffArr[i] + "\n";
                        }

                        //System.out.println("Double" + countDoubles + " " + stringBuffArr[i]); //раскоментить строку для отображения только дублей
                        //continue;
                    }
                }
            }
        }

        //System.out.println(stringBuffArr[i]);
    }

    for (String i: stringBuffArr) {     System.out.println(i);    }

    System.out.println("\nповторяющихся значений: " + countDoubles);
    System.out.println(strDoubles);


//работаем с дублями - оставляем дубли тем, у кого тикетов меньше
    String[]arrDoubles = strDoubles.split("\n");

    for (int i = 0; i < arrDoubles.length; i++) {

        for (int j = 0; j < stringBuffArr.length; j++) {


                if (arrDoubles[i].equals(stringBuffArr[j])) {

                    while (stringBuffArr[i].length() != 0) {

                        int count = 0;
                        count++;

                    }
                }



        }

    }



    } //end of method

} //end of class


/*
CRM-79722	(PM00036576, IM55808744)
Анохина, Жердева, Марков - должен остаться у Жердевой


Веснин Олег Николаевич
Управление командой поддержки СБТ

Денисов Михаил Александрович
Управление командой поддержки СБТ в части разработки, организация выпуска и учета исправлений

Кретов Алексей Алексеевич
Управление командой поддержки, операционное управления функциями по разбору обращений

*/

