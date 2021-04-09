package com.plink.swfsys.piil;

import com.plink.swfsys.piil.service.ProductInfoIngestionLibraryService;
import com.plink.swfsys.piil.service.common.file.InputFileReader;
import com.plink.swfsys.piil.service.data.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@RestController
@EnableConfigurationProperties(ConfigProperties.class)
public class PiilApplication implements CommandLineRunner {

    @Autowired
    private ProductInfoIngestionLibraryService productInfoIngestionLibraryService;

    public PiilApplication(String[] args) {
    }

    public static void main(String[] args) {
        SpringApplication.run(PiilApplication.class, args);
    }

    /**
     * CommandLineRunner interface
     * @param args
     */
    @Override
    public void run(String... args) {
        if (args == null || args.length == 0 || args[0] == null) {
            System.out.println("No inputData file specified on command line");
            return;
        }

        System.out.println("inputData file specified on command line: " + args[0]);

        List<String> inputLines;
        InputFileReader inputFileReader = new InputFileReader();
        try {
            inputLines = inputFileReader.readInputDataFile(args[0]);
        } catch (IOException e) {
            System.out.println("Error reading inputData file");
            e.printStackTrace();
            return;
        }

        productInfoIngestionLibraryService.processStore(inputLines);
    }


    @RequestMapping(value = "/")
    public String status() {
        return "piil up";
    }

    @PostMapping("/processFile")
    public String processFile(@RequestParam("file") MultipartFile file/*, RedirectAttributes redirectAttributes*/) {

        // TODO: authentication and authorization

        if (file.isEmpty()) {
            return "Error:  input file is empty";
        }

        List<String> inputLines;
        InputFileReader inputFileReader = new InputFileReader();
        try {
            inputLines = inputFileReader.readInputDataFile(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error reading inputData file");
            e.printStackTrace();
            return "Error processing input: " + e.getMessage();
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");

        List<ProductRecord> productRecords = productInfoIngestionLibraryService.processStore(inputLines);


        for (ProductRecord productRecord : productRecords) {
            builder.append(productRecord.toString());
            builder.append("<br>");
        }

        builder.append("<br>");
        builder.append("Success");
        builder.append("</body></html>");
        return builder.toString();
    }

}
