package com.markfront.SPFH_RestApi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.net.URLDecoder;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.markfront.spfh.*;

@RestController
public class ServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/fget")
    public PageHtmlData fget(@RequestParam(value = "url", defaultValue = "") String url) {
        long id = counter.incrementAndGet();

        System.out.println("raw_url = " + url);

        String decoded_url = url;
        try {
            decoded_url = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("decoded_url = " + url);

        String out_file = SinglePageFullHtml.run(decoded_url, "", "");

        String html = getContent(out_file);

        PageHtmlData result = new PageHtmlData(id, url, html);
        return result;
    }

    private String getContent(String path) {
        String content = "";
        try {
            content = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
