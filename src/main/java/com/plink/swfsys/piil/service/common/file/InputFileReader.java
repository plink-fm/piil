package com.plink.swfsys.piil.service.common.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    public List<String> readInputDataFile(String filePath) throws IOException {
        if (filePath == null || filePath.length() == 0) {
            throw new IllegalStateException("invalid inputData file: " + filePath);
        }

        return readInner(new FileReader(filePath));
    }

    public List<String> readInputDataFile(byte[] bytes) {
        return readInner(new InputStreamReader(new ByteArrayInputStream(bytes)));
    }

    protected List<String> readInner(InputStreamReader isReader) {
        ArrayList<String> inputLines = new ArrayList(); // TODO: init to large capacity based on heuristic
        try (BufferedReader reader = new BufferedReader(isReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return inputLines;
    }


}
