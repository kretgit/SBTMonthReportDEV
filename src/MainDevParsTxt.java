/*������ ������� ����� ����� � ����� ���������*/

import java.io.FileWriter;
import java.io.FileReader;

public class MainDevParsTxt {


    static String nameOfTXTFile =
                        "//Users//mackret//Downloads/12_dev.txt";
            //"C://Users//SBT-Kretov-AA//Downloads/12_dev.txt";

    //static String[]arrPrefFromSM = {"PM","IM","���","PT","RSK","���"};

    static String textFromTXTFile = "";

    public static void main (String[]xxx) {

        String regexBegin = "���/���/Sm";
        String regexCRM = "CRM-";
        String regexTab = "\t";
        String regex2 = "�������:";
        String regex3 = "��������";
        String regexEnd = "��������";

//�� ��������� ����� ��� ��������� � �������� ���������� ������
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


//��������� ������ � ������ �� ������� ������ ����� ������
String[]buffArrFromTXT = textFromTXTFile.split("\n");

for (int i = 0; i < buffArrFromTXT.length; i ++) {

    if (buffArrFromTXT[i].contains(regexBegin)) {
        continue;
    } else {
//��������� ������� ������ �� "CRM-"
        if (buffArrFromTXT[i].contains(regexCRM)) {

            int indexTab = buffArrFromTXT[i].indexOf(regexTab);

                if (buffArrFromTXT[i].contains(regex2)) {
                    int indexRegex2 = buffArrFromTXT[i].indexOf(regex2);
                    int indexRegex3 = buffArrFromTXT[i].indexOf(regex3);
                    buffArrFromTXT[i] = buffArrFromTXT[i].replaceAll((buffArrFromTXT[i].substring(indexRegex2, indexRegex3 + regex3.length())), "");
                }

            buffArrFromTXT[i] = buffArrFromTXT[i].substring(0, indexTab) + regexTab + "(" + buffArrFromTXT[i].substring(indexTab + regexTab.length()) + ")";

        }

        //System.out.println(buffArrFromTXT[i]);
    }
}

//������ � ����� ����� ������� ������ ������ (���� ������� ��� ������ � SM)
        for (String i: buffArrFromTXT) {
            if (i.contains("()")) {
               i = "";
            }

            System.out.println(i);
        }


    } //end of method

} //end of class
