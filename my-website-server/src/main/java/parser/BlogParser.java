package parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BlogParser {

    // Parse a blog file into title, content, author and creation date components.
    public static List<Blog> parseBlogs(String dirPath) throws IOException {
        List<File> blogs = Files.list(Paths.get("./my-website-server/src/main/resources/blogs"))
                .map(Path::toFile)
                .filter(File::isFile)
                .collect(Collectors.toList());

        List<Blog> blogList = new ArrayList<>();

        for (File blog : blogs) {
            String overallContent = Files.readString(blog.toPath());
            String title = overallContent.substring(0, overallContent.indexOf("\n"));
            String content = overallContent.substring(overallContent.indexOf("\n") + 1);
            String date = formatDate(blog);
            blogList.add(new Blog(title, content, "Zhihao Li", date));
        }

        return blogList;
    }

    /**
     * Return the formatted creation time of the given blog file.
     * @param blog The blog file whose creation date is being formatted.
     * @return A formatted creation date of the given blog file.
     * @throws IOException
     */
    private static String formatDate(File blog) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(blog.toPath(), BasicFileAttributes.class);
        FileTime fileTime = attr.creationTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = df.format(new Date(fileTime.toMillis()));
        return date;
    }
}
