# NROD MODEL LIBRARY

## Purpose

This library provides a Java model for the abstractions published in the 
National Rail On Demand (NROD) APIs, aimed at speeding up development work for
those who are building applications - to save them having to implement from
scratch.


## Building

Compiling the code requires a Java SDK supporting at least Java 8 and a
Maven installation.

Building the library should be as simple as:
```
$ git checkout <url>
$ mvn install
```

## Contributions

Contributions are welcome - please fork the project and raise a pull-request 
in GitHub.


## How to use this library

The NROD API provides the ability to download a CIF schedule file, which 
contains the information needed to understand which trains are scheduled to run
at which times.

You can either use the source code or if you're using Maven, this library can 
be included as follows:
```xml
<dependency>
    <groupId>com.cwpad</groupId>
    <artifactId>nrod-model</artifactId>
    <version>0.0.3</version>
</dependency>
```

### Architecture

The library uses an in-memory model - so it trades off speed of access to data
for performance. This means you are able to run queries quickly but there is an
initial up-front time cost for parsing the file and an ongoing fixed memory 
cost for keeping the data in memory. For this reason, you will want to ensure 
you re-use the service objects you create. 


### Parsing a CIF schedule

```java
String nrodUsername = System.getenv("NROD_USERNAME");
String nrodPassword = System.getenv("NROD_PASSWORD");
String cifUrl = System.getenv("CIF_URL");

NetworkRailStaticReader reader = new NetworkRailStaticReader();
InputStream in = new ByteArrayInputStream(reader.readUrlData(nrodUsername, nrodPassword, cifUrl));

TrainOperatingCompanyRepository trainOperatingCompanyRepository = Services.INSTANCE.newTrainOperatingCompanyRepository();

// Creating and bootstrapping the ScheduleLookupService is a relatively expensive operation:
// so make sure you keep it around and re-use it.
ScheduleLookupService sls = CommonInterfaceFile.INSTANCE.newScheduleLookupService(trainOperatingCompanyRepository);
sls.bootstrap(new InputStreamReader(in));

List<TrainSchedule> schedules = sls.findByUid("L77926", LocalDate.of(2022, 6, 1));
if (schedules.size() < 1) {
    System.out.printf("No schedules found%n");
}

TrainSchedule s1 = schedules.get(0);
System.out.printf("Found schedule for service %s from %s to %s%n", s1.getTrainIdentity(),
        s1.getOriginTiploc(), s1.getDestinationTiploc());
```

### Looking up a location

```java
String nrodUsername = System.getenv("NROD_USERNAME");
String nrodPassword = System.getenv("NROD_PASSWORD");
String cifUrl = System.getenv("CIF_URL");

NetworkRailStaticReader reader = new NetworkRailStaticReader();
InputStream in = new ByteArrayInputStream(reader.readUrlData(nrodUsername, nrodPassword, cifUrl));

// Creating and bootstrapping the LocationLookupService is a relatively expensive operation:
// so make sure you keep it around and re-use it.
LocationLookupService lls = CommonInterfaceFile.INSTANCE.newLocationLookupService();
lls.bootstrap(new InputStreamReader(in));

Optional<RailNetworkLocation> location = lls.lookup("KNGX");
if (location.isPresent()) {
    System.out.printf("Found station: %s%n", location.get().getDescription());
} else {
    System.out.printf("Not found%n");
}
```

## Examples

For more details on the examples above, see 
[Location lookup example](./src/test/java/examples/LocationLookupExample.java) and 
[Schedule lookup example](./src/test/java/examples/ScheduleLookupExample.java).