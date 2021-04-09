# Product Information Ingestion Library / piil

This service provides processing of input data for the purpose of generating `ProductRecord` items that may be consumed by other services/systems.   



###### Build Status
<!-- ![Appveyor build status!](https://ci.appveyor.com/api/projects/status/github/plink-fm/piil) --> 
![GitHub build action workflow](https://github.com/plink-fm/piil/actions/workflows/javaci.yml/badge.svg)



#### Architecture

The library is implemented as a processing chain that provides several plugin points for data parsing, custom logic, and parceling the output `ProductRecord` to multiple destination services (e.g. persistence, search indexer).

The following figure depicts the processing pipeline.

![piil Processing Pipeline](https://github.com/plink-fm/piil/blob/master/ProcessingPipeline.PNG?raw=true)

The processing pipeline is defined as a set of interfaces, for which default implementations are provided (including a fixed-width input item reader).  The handlers provide the "grocery chain-specific" business logic for performing calculations and formatting (e.g. `ChainXFooHandler`). 

#### Packages and classes of interest
 - the `com.plink.swfsys.piil.service` package root contains the common code (and data) structure and default handlers
 - `com.plink.swfsys.piil.chain_x.handler` contains 'Chain X' specific handlers for calculation and formatting
 - the `ProductInfoIngestionLibraryService` class provides the primary entry point for input item data processing, including overloads for custom extensions

This service is implemented as a Spring Boot application, and may be invoked either via command line or an HTTP endpoint.

#### Running the piil application

To run the application, download the [PiilPackage.zip](https://github.com/plink-fm/piil/suites/2450896733/artifacts/52543565), unzip, and run the jar file:  
`java -jar piil-0.0.1-SNAPSHOT.jar` 

 - The application will bind to port 8080 by default.  

 - (Optional) specify the server port as a command line arg, e.g.:
`-Dserver.port=9001`

 - (Optional) specify a fixed-width format file (as defined in the specification) as the first parameter, e.g.:
`java -jar piil-0.0.1-SNAPSHOT.jar ./inputData.txt`

 - (Optional) post an input file tp the /processInput endpoint, e.g.:
`POST http://localhost:8080/processFile` with a `file` parameter loaded as the post body.

 - (Optional) pull down source and run tests, e.g. `ProductInfoIngestionLibraryServiceTest`

#### Future Enhancements

 - `ProductRecord` persistence
 - add parameterized `InputSpecification`
 - add authentication/authorization
 - devise a strategy for capturing and aggregating error items
 - develop `BatchInputJobSplitHandler` based on observed throughput; this service-level handler will split the input data batch across n threads for parallel prcessing
  