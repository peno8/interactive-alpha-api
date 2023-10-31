package com.lazarus.api.util;

import com.alibaba.fastjson.JSON;
import com.lazarus.api.rank.ScreenerDef;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@NoArgsConstructor
public class ResourceService {

    @Value("${DATA_ROOT}")
    private String dataRoot;

    public String load(String filename) throws IOException {
        String data = new String(
                Files.readAllBytes(Paths.get(dataRoot + "/" + filename)));

        return data;
    }
}
