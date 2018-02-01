import java.io.FileWriter;

public class WriteTxt {


    static void fileWriter() {

        String pathToTXTFile = MainDevDef.pathTotxt.replaceFirst(".txt","_UPDATED.txt");
        String finalDevDef = "";
        String[]devDefArr = DevParsTxt.stringBuffArr.clone();
        String[]onlyDoubles = DevParsTxt.strDoubles.split("\n");
        String[]doNotDeleteThisRow = DoubleChecker.rowForDoubles.split("\n");


//читаем массив и удаляем из него строки в которых есть дубли и которые не являются неудаляемыми элементами
        for (int i = 0; i < devDefArr.length; i++) {

            for (int j = 0; j < onlyDoubles.length; j++) {

                if (i != Integer.parseInt(doNotDeleteThisRow[j]) && onlyDoubles[j].equals(devDefArr[i])) {
                    devDefArr[i] = "DoubleWasHere";
                }

            }
            //System.out.println(devDefArr[i]);
            if (devDefArr[i] != "DoubleWasHere") {
                finalDevDef += devDefArr[i] + "\n";
            }
        }


//непосредственно сама запись в файл
        try {

            FileWriter fw = new FileWriter(pathToTXTFile);
            fw.write(finalDevDef);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\nGet your file at: " + pathToTXTFile);

    }

}
