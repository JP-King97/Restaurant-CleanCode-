package data;

import java.util.HashMap;
import java.util.Map;

public class ExcelFileStringReader extends ExcelFileReader{
    private Map<Integer,String> stringReader = new HashMap<>();
    public ExcelFileStringReader(Map<Integer, Integer> numericalReader, Map<Integer, String> StringReader, String sheetName) {
        super(numericalReader, StringReader, sheetName);
    }

    public ExcelFileStringReader(Map<Integer, String> StringReader, String sheetName) {
        super(sheetName);
        this.stringReader = stringReader;

    }


}
