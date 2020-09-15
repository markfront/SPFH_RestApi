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
    public PageHtmlData fget(
            @RequestParam(value = "url", defaultValue = "") String page_url,
            @RequestParam(value = "cache", defaultValue = "true") boolean use_cache
    ) {
        long id = counter.incrementAndGet();

        System.out.println("raw_url = " + page_url);
        System.out.println("use_cache = " + use_cache);

        String decoded_url = page_url;
        try {
            decoded_url = URLDecoder.decode(page_url, StandardCharsets.UTF_8.name());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("decoded_url = " + page_url);

        String out_file = SinglePageFullHtml.run(decoded_url, "", "", use_cache);

        String html = getContent(out_file);

        PageHtmlData result = new PageHtmlData(id, page_url, html);
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
