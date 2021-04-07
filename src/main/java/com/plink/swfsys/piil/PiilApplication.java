package com.plink.swfsys.piil;

import com.plink.swfsys.piil.service.ProductInfoIngestionLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableConfigurationProperties(ConfigProperties.class)
public class PiilApplication {

    @Autowired
    private ConfigProperties configProperties;

//	@Autowired
//	private Environment env;

    @Autowired
    private ProductInfoIngestionLibraryService productInfoIngestionLibraryService;

    public static void main(String[] args) {
        SpringApplication.run(PiilApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String hello() {

//        productInfoIngestionLibraryService.process();

        // TODO: need to be able to pass in data here to processStore:
        productInfoIngestionLibraryService.processStore(null);

        return "Hello World";
    }

}
