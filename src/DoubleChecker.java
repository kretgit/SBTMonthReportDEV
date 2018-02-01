public class DoubleChecker {


    static String[]stringBuffArr = DevParsTxt.stringBuffArr.clone();
    static String[]arrDoubles = DevParsTxt.strDoubles.split("\n");
    static String rowForDoubles = "";

    public static void doubleCheck () {

//подсчитывает количество заявок у каждого разраба
        int countBugs = 0;

        for (int i = stringBuffArr.length-1; i >= 0; i--) {

            if (stringBuffArr[i].contains("CRM-")) {
                countBugs++;
                //continue;
            }
            else if (stringBuffArr[i].length()==0) {
                continue;
            }
            else {
                stringBuffArr[i] = stringBuffArr[i] + countBugs;
                countBugs = 0;
                if (stringBuffArr[i].contains("SBT-Denisov-MA")) {
                    stringBuffArr[i]+="000";
                }

            }

            //System.out.println(stringBuffArr[i]);
        }

//проставлеям количество дефектов напротив каждый строки
        String[]copyOfStringBuffArr = stringBuffArr.clone();
        String defectCount = "";
        int colonCount;

        for (int i = 0; i < stringBuffArr.length; i++) {

            if (stringBuffArr[i].contains(":") && (stringBuffArr[i].contains("CRM-")==false) ) {

                colonCount = stringBuffArr[i].indexOf(":");
                defectCount = stringBuffArr[i].substring(colonCount);

            } else if (stringBuffArr[i].length() != 0){
                stringBuffArr[i] +=defectCount;
            }
            //System.out.println(stringBuffArr[i]);
        }

        //for (String i: stringBuffArr) { System.out.println(i); }  System.out.println("\n");
        //for (String i: arrDoubles) { System.out.println(i); } System.out.println("\n");



//отбражаем строки, в которых содержаться дубли + элемент массива + количество тикетов разраба, дубли которого и отображает строка
        String antiDoubles = "";

        for (int i = 0; i < arrDoubles.length; i++) {

            for (int j = 0; j < copyOfStringBuffArr.length; j++) {

                if (arrDoubles[i].equals(copyOfStringBuffArr[j]) ) {

                    antiDoubles += j + "; " + arrDoubles[i] + " " + stringBuffArr[j].substring(stringBuffArr[j].indexOf(":")) + "\n";

                }

            }

            antiDoubles += "PART_" + i + "\n";
            //System.out.println(antiDoubles);

        }

//если нужно получить лог разбора - раскоментить
        //System.out.println(antiDoubles);

//обрабатываем строку с дефектами - выписываем элементы массива для котоых дубли не удалять
        int minIndex;
        int separatorIndex;
        String buffAntiDoubler;

        for (int i = 0; i < arrDoubles.length; i++) {

            separatorIndex = ("PART_" + i).length();
            buffAntiDoubler = antiDoubles.substring(0,antiDoubles.indexOf("PART_" + i));
            antiDoubles = antiDoubles.substring(antiDoubles.indexOf("PART_" + i) + separatorIndex + 1);
            minIndex = 0;

            String[]antiDoubleArr = buffAntiDoubler.split("\n");

            for (int j = 1; j < antiDoubleArr.length; j++) {

                if (Integer.parseInt(antiDoubleArr[j].substring(antiDoubleArr[j].indexOf(":") + 1)) < Integer.parseInt(antiDoubleArr[minIndex].substring(antiDoubleArr[minIndex].indexOf(":") + 1))) {
                    minIndex = j;
                }

            }
            //System.out.println(antiDoubleArr[minIndex]);
            rowForDoubles+=antiDoubleArr[minIndex].substring(0,antiDoubleArr[minIndex].indexOf(";")) + "\n";

        }

        //System.out.println("\n"+rowForDoubles);



    } //end of method


} //end of class
