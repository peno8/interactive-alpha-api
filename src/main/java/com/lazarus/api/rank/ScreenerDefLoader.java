package com.lazarus.api.rank;

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
public class ScreenerDefLoader {

    @Value("${DATA_ROOT}")
    private String dataRoot;

    public List<ScreenerDef> load() throws IOException {
        String data = new String(
                Files.readAllBytes(Paths.get(dataRoot + "/screener_definition.json")));

        List<ScreenerDef> screenerDefList = JSON.parseArray(data, ScreenerDef.class);
        return screenerDefList;
    }
}
