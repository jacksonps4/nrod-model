package examples;

import com.cwpad.rail.model.RailNetworkLocation;
import com.cwpad.rail.nrod.cif.CommonInterfaceFile;
import com.cwpad.rail.services.LocationLookupService;
import com.cwpad.rail.services.NetworkRailStaticReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class LocationLookupExample {
    public static void main(String[] args) {
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
    }
}
