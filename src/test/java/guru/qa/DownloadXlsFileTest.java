package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;


public class DownloadXlsFileTest {
    static final String PATH_TO_DWD = "downloads";

    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DWD));
    }

    @Test
    void xlsDownload() throws Exception {
        Configuration.downloadsFolder = PATH_TO_DWD;
        Selenide.open("https://github.com/zimmermanul/WorkWithFiles/blob/master/src/test/resources/Food%20table.xlsx");
        File downloadedXlsFile = $("#raw-url").download();
        XLS xlsFile = new XLS(downloadedXlsFile);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("xlsFile");
        Assertions.assertEquals("горох", xlsFile.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue());

    }

}
