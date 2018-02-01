/*пример годного файла лежит в корне программы*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;


public class DevParsTxt {


    static String nameOfTXTFile = MainDevDef.pathTotxt;


    static String strDoubles = "";
    static String[]stringBuffArr; //обработанный массив со списком только разрабов и их дефектов

    public static void readTxtParsArr () {

        String regexBegin = "Цуп/Тск/Sm";
        String regexCRM = "CRM-";
        String regexTab = "\t";
        String regex2 = "Проблем:";
        String regex3 = "Обновить";


        String textFromTXTFile = "";
        String stringBuff = "";

//из тексового файла все переводим в текстовую переменную строку
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(nameOfTXTFile),"windows-1251")); //UTF8

            String c;
            while ((c = br.readLine() ) != null) {

                textFromTXTFile += c + "\n";
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
            }
        }
        //System.out.println(empCount);
        //System.out.println(stringBuff);

//обратно строку в массив
        stringBuffArr = stringBuff.split("\n");


//ищем дубли в массиве - типа одинаковые задачи
        int countDoubles = 0;

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

        //for (String i: stringBuffArr) {     System.out.println(i);    }

        System.out.println("повторяющихся значений: " + countDoubles);
        System.out.println(strDoubles);





    } //end of method

} //end of class


