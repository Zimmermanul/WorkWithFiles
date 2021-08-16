package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;


public class DownloadTxtFileTest {
    static final String PATH_TO_DWD = "downloads";


    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DWD));
    }

    @Test
    void txtDownload() throws Exception {
        Configuration.downloadsFolder = PATH_TO_DWD;

        Selenide.open("https://github.com/zimmermanul/WorkWithFiles/blob/master/src/test/resources/Railwaylinks.txt");
        File downloadedFile = $("#raw-url").download();

        String FileContent1 = FileUtils.readFileToString(downloadedFile, "UTF-8");
        Assertions.assertTrue(FileContent1.contains("https://www.tutu.ru/"));
    }

}
