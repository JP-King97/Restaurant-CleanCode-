package data;

import java.util.HashMap;
import java.util.Map;

public class ExcelFileNumericalReader extends ExcelFileReader{
    private String sheetName;
    private Map<Integer,Integer> numericalReader = new HashMap<>();

    public ExcelFileNumericalReader(Map<Integer, Integer> numericalReader, Map<Integer, String> StringReader, String sheetName) {
        super(numericalReader, StringReader, sheetName);
    }

    public ExcelFileNumericalReader(Map<Integer, Integer> numericalReader, String sheetName) {
        super(sheetName);
        this.numericalReader = numericalReader;
    }
}
