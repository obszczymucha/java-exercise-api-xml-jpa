package org.obszczymucha.tradereportingengine.service.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import lombok.val;

@Component
public class ResourceFileLister {
    private final ResourceLoader loader;

    @Autowired
    public ResourceFileLister(final ResourceLoader loader) {
        this.loader = loader;
    }

    public List<Resource> listFiles(final String baseDirectory) throws IOException {
        val resolver = new PathMatchingResourcePatternResolver(loader);
        return Arrays.asList(resolver.getResources("classpath:" + baseDirectory + "/*.xml"));
    }
}
