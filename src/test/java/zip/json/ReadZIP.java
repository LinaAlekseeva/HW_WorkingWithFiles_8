package zip.json;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import qa.guru.FilesParsingTest;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadZIP {
    ClassLoader cl = FilesParsingTest.class.getClassLoader();
    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("resources/Архив ZIP - WinRAR.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")){
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("PDF");
                } else if (entry.getName().endsWith(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).contains("XLSX");
                }
               else if (entry.getName().endsWith(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[0]).contains("CSV");
                }
            }
        }
    }
}
