/*пример годного файла лежит в корне программы*/

import java.io.FileWriter;
import java.io.FileReader;

public class MainDevParsTxt {


    static String nameOfTXTFile =
                        "//Users//mackret//Downloads/12_dev.txt";
            //"C://Users//SBT-Kretov-AA//Downloads/12_dev.txt";

    static String textFromTXTFile = "";
    static int empCount = 0;
    static int totalCount = 0;

    static String stringBuff = "";

    public static void main (String[]xxx) {

        String regexBegin = "Цуп/Тск/Sm";
        String regexCRM = "CRM-";
        String regexTab = "\t";
        String regex2 = "Проблем:";
        String regex3 = "Обновить";

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

        //System.out.println(textFromTXTFile);


//разбиваем строку в массив по символу начала новой строки
String[]buffArrFromTXT = textFromTXTFile.split("\n");

for (int i = 0; i < buffArrFromTXT.length; i ++) {

    if (buffArrFromTXT[i].contains(regexBegin)) {
        continue;
    } else {
//обработка остатка строки от "CRM-"
        if (buffArrFromTXT[i].contains(regexCRM)) {

            int indexTab = buffArrFromTXT[i].indexOf(regexTab);

                if (buffArrFromTXT[i].contains(regex2)) {
                    int indexRegex2 = buffArrFromTXT[i].indexOf(regex2);
                    int indexRegex3 = buffArrFromTXT[i].indexOf(regex3);
                    buffArrFromTXT[i] = buffArrFromTXT[i].replaceAll((buffArrFromTXT[i].substring(indexRegex2, indexRegex3 + regex3.length())), "");
                }

            buffArrFromTXT[i] = buffArrFromTXT[i].substring(0, indexTab) + regexTab + "(" + buffArrFromTXT[i].substring(indexTab + regexTab.length()) + ")";

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
            if (buffArrFromTXT[i].contains(regexCRM)) {
                continue;
            } else if (buffArrFromTXT[i].length()<1) {
                continue;
            }    else {
                    empCount++;
            }

    }
}

        System.out.println(empCount);
        //System.out.println(stringBuff);

//обратно строку в массив

    String[]stringBuffArr = stringBuff.split("\n");



//ищем дубли в массиве - типа одинаковые задачи
    int count = 0;
    int countDoubles = 0;

    for (int i = 0; i < stringBuffArr.length; i++) {
        if (stringBuffArr[i].length() != 0) {
            for (int j = 0; j < stringBuffArr.length; j++) {
                if (stringBuffArr[j].length() != 0) {


                    if (j != i && stringBuffArr[j].equals(stringBuffArr[i])) {
                        countDoubles++;
                        //stringBuffArr[i] = "Дубль" + countDoubles + " " + stringBuffArr[i];
                        System.out.println("Double" + countDoubles +" "+ stringBuffArr[i]); //раскоментить строку для отображения только дублей
                        break;
                    }


                }
            }
        }

        //System.out.println(stringBuffArr[i]);
    }

    System.out.println("дублей найдено: " + countDoubles);

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

