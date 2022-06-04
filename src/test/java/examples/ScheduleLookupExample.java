package examples;

import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.nrod.cif.CommonInterfaceFile;
import com.cwpad.rail.services.NetworkRailStaticReader;
import com.cwpad.rail.services.ScheduleLookupService;
import com.cwpad.rail.services.Services;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class ScheduleLookupExample {
    public static void main(String[] args) {
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
    }
}
