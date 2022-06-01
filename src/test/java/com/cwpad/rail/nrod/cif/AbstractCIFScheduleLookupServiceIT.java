package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.TrainSchedule;
import com.cwpad.rail.services.Logger;
import com.cwpad.rail.services.TrainOperatingCompanyRepository;
import org.junit.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.zip.GZIPInputStream;

public abstract class AbstractCIFScheduleLookupServiceIT {
    static CIFScheduleLookupService scheduleLookupService;

    static final Logger logger = Logger.getInstance(AbstractCIFScheduleLookupServiceIT.class);

    @BeforeClass
    public static void setUp() throws IOException {
        String cifFile = System.getProperty("cifFile");
        if (cifFile == null || cifFile.trim().length() == 0) {
            return;
        }

        scheduleLookupService = new CIFScheduleLookupService(TrainOperatingCompanyRepository.instance());
        long start = System.currentTimeMillis();
        scheduleLookupService.bootstrap(new InputStreamReader(new GZIPInputStream(new FileInputStream(cifFile))));
        long timeTaken = System.currentTimeMillis() - start;
        logger.info(String.format("Bootstrapped in %dms", timeTaken));
    }

    List<TrainSchedule> getLondonToEdinburgh() {
        return scheduleLookupService.findByUid("Y72059", LocalDate.of(2016, 10, 19));
    }

    List<TrainSchedule> getNewcastleToLondon() {
        return scheduleLookupService.findByUid("Y71771", LocalDate.of(2016, 10, 19));
    }

    List<TrainSchedule> getCannonStToDartford() {
        return scheduleLookupService.findByUid("W47426", LocalDate.of(2016, 10, 19));
    }
}
