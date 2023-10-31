package com.lazarus.api.metadata;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class MetadataService {

    @Value("${DATA_ROOT}")
    private String dataRoot;

    private Metadata metadata;

    private void load() throws IOException {
        String data = new String(
                Files.readAllBytes(Paths.get(dataRoot + "/metadata.json")));

        Metadata metadata = JSON.parseObject(data, Metadata.class);
        this.metadata = metadata;
    }

    @PostConstruct
    private void initialize() throws IOException {
        load();
    }

    public Metadata getMetadata() {
        return metadata;
    }


}
