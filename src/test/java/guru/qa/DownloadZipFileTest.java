package guru.qa;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DownloadZipFileTest {
    static final String PASSWORD = "123";
    static final Charset CHARSET = StandardCharsets.UTF_8;

    @Test
    void zipFileTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("Sitelink.zip");
        String entryAsString = null;
        try (ZipInputStream zipStream = new ZipInputStream(stream, PASSWORD.toCharArray(), CHARSET)) {
            while (zipStream.getNextEntry() != null) {
                entryAsString = IOUtils.toString(zipStream, CHARSET);
            }
        }
        assertThat(entryAsString).contains("http://azuon.com/");
    }
}