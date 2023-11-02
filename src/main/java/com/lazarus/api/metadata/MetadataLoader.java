package com.lazarus.api.metadata;

import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@NoArgsConstructor
public class MetadataLoader {

    @Value("${DATA_ROOT}")
    private String dataRoot;

    public Metadata load() throws IOException {
        String data = new String(
                Files.readAllBytes(Paths.get(dataRoot + "/metadata.json")));

        Metadata metadata = JSON.parseObject(data, com.lazarus.api.metadata.Metadata.class);
        return metadata;
    }
}
