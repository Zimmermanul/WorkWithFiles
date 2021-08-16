package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadPdfFileTest {
    static final String PATH_TO_DWD = "downloads";

    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DWD));
    }

    @Test
    void pdfDownload() throws IOException {
        Selenide.open("https://github.com/zimmermanul/WorkWithFiles/blob/master/src/test/resources/Eightydays.pdf");
        File pdfFile = $("#raw-url").download();
        PDF parsedPdf = new PDF(pdfFile);
        assertThat(parsedPdf.text).contains("http://eightydays.me/");
    }
}
