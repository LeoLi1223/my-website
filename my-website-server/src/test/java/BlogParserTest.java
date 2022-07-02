import org.junit.Test;
import parser.Blog;
import parser.BlogParser;

import java.io.IOException;
import java.util.List;

public class BlogParserTest {

    private static final String PATH = "./src/test/resources";

    @Test
    public void testParseBlogs() throws IOException {
        List<Blog> blogList = BlogParser.parseBlogs(PATH);
        System.out.println(blogList);
    }
}
