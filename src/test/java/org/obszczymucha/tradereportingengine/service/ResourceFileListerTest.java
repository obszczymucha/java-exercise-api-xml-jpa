package org.obszczymucha.tradereportingengine.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.obszczymucha.tradereportingengine.service.utils.ResourceFileLister;
import org.springframework.core.io.DefaultResourceLoader;

import lombok.val;

public class ResourceFileListerTest {
    @Test
    void shouldListFilesInAResourceDirectory() throws IOException {
        // Given
        val lister = new ResourceFileLister(new DefaultResourceLoader());

        // When
        val result = lister.listFiles("data").stream().map(resource -> resource.getFilename()).toList();

        // Then
        assertThat(result).containsExactly("event0.xml", "event6.xml");
    }
}
