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

//        List<String> inputLines = new ArrayList(); // TODO: init to large capacity based on heuristic
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                inputLines.add(line);
//            }
//        }
//        catch (FileNotFoundException f) {
//            System.out.println(filePath + " does not exist");
//            return null;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return inputLines;
    }

    protected List<String> readInner(InputStreamReader isReader) {
        List<String> inputLines = new ArrayList(); // TODO: init to large capacity based on heuristic
        try (BufferedReader reader = new BufferedReader(isReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line);
            }
        }
//        catch (FileNotFoundException f) {
//            System.out.println(filePath + " does not exist");
//            return null;
//        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return inputLines;
    }

    public List<String> readInputDataFile(byte[] bytes) throws IOException {

        return readInner(new InputStreamReader(new ByteArrayInputStream(bytes)));

//        InputStream is = null;
//        BufferedReader bfReader = null;
//        try {
//            is = new ByteArrayInputStream(bytes);
//            bfReader = new BufferedReader(new InputStreamReader(is));
//        }
//        finally {
//            try{
//                if(is != null) is.close();
//            } catch (Exception ex){
//
//            }
//        }

//        List<String> inputLines = new ArrayList(); // TODO: init to large capacity based on heuristic
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                inputLines.add(line);
//            }
//        }
//        catch (FileNotFoundException f) {
//            System.out.println(filePath + " does not exist");
//            return null;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return inputLines;
    }
}
