package com.cwpad.rail.nrod.cif;

import com.cwpad.rail.model.LocationIdentityType;
import com.cwpad.rail.model.LocationLookupRequest;
import com.cwpad.rail.model.LocationSearchRequest;
import com.cwpad.rail.model.RailNetworkLocation;
import com.cwpad.rail.services.*;

import java.io.Reader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class CIFTiplocRepository extends AbstractBootstrappable implements LocationLookupService, LocationSearchService {
    private final Logger logger = Logger.getInstance(getClass());

    private final LocationIdentityTypeParser locationIdentityTypeParser = new LocationIdentityTypeParser();
    private final Map<String, RailNetworkLocation> berthsByTiploc = new ConcurrentHashMap<>();
    private final Map<String, RailNetworkLocation> berthsByCrs = new ConcurrentHashMap<>();
    private final Map<Integer, RailNetworkLocation> berthsByStanox = new ConcurrentHashMap<>();

    @Override
    public void bootstrap(Reader reader) {
        Iterator<CIFRecord> itr = new CIFParser(reader).iterator();
        CIFRecord record = null;
        while (itr.hasNext() && !isInterrupted()) {
            try {
                record = itr.next();
                if (record instanceof CIFTiplocInsertRecord) {
                    CIFTiplocInsertRecord tiploc = (CIFTiplocInsertRecord) record;
                    add(tiploc);
                } else if (record instanceof CIFTiplocAmendRecord) {
                    CIFTiplocAmendRecord tiploc = (CIFTiplocAmendRecord) record;
                    update(tiploc);
                } else if (record instanceof CIFTiplocDeleteRecord) {
                    CIFTiplocDeleteRecord tiploc = (CIFTiplocDeleteRecord) record;
                    delete(tiploc);
                }
            } catch (RuntimeException e) {
                logger.error("Failed to bootstrap record: " + record, e);
            }
        }
    }

    private void add(CIFTiplocInsertRecord record) {
        RailNetworkLocation railNetworkLocation = map(record);
        berthsByTiploc.put(record.getTiploc(), railNetworkLocation);
        Optional<String> crsCode = record.getCrsCode();
        if (crsCode.isPresent()) {
            berthsByCrs.put(crsCode.get(), railNetworkLocation);
        }
        Optional<Integer> stanox = record.getStanox();
        if (stanox.isPresent()) {
            berthsByStanox.put(stanox.get(), railNetworkLocation);
        }
    }

    private RailNetworkLocation map(CIFTiplocRecord record) {
        RailNetworkLocation railNetworkLocation = new RailNetworkLocation();
        railNetworkLocation.setTiploc(record.getTiploc());
        railNetworkLocation.setNationalLocationCode(record.getNationalLocationCode());

        Optional<String> crsCode = record.getCrsCode();
        if (crsCode.isPresent()) {
            railNetworkLocation.setShortCode(crsCode.get());
        }
        Optional<Integer> stanox = record.getStanox();
        if (stanox.isPresent()) {
            railNetworkLocation.setStanox(stanox.get());
        }
        Optional<String> description = record.getTpsDescription();
        if (description.isPresent()) {
            railNetworkLocation.setDescription(description.get());
        }
        return railNetworkLocation;
    }

    private void update(CIFTiplocAmendRecord record) {
        RailNetworkLocation railNetworkLocation = map(record);
        String newTiploc = record.getNewTiploc();
        if (newTiploc != null) {
            berthsByTiploc.remove(record.getTiploc());
            berthsByTiploc.put(record.getNewTiploc(), railNetworkLocation);
        }
        Optional<String> crsCode = record.getCrsCode();
        if (crsCode.isPresent()) {
            berthsByCrs.put(crsCode.get(), railNetworkLocation);
        }
        Optional<Integer> stanox = record.getStanox();
        if (stanox.isPresent()) {
            berthsByStanox.put(stanox.get(), railNetworkLocation);
        }
    }

    private void delete(CIFTiplocDeleteRecord record) {
        RailNetworkLocation railNetworkLocation = berthsByTiploc.remove(record.getTiploc());
        berthsByCrs.remove(railNetworkLocation.getShortCode());
        berthsByStanox.remove(railNetworkLocation.getStanox());
    }

    public Optional<RailNetworkLocation> lookupByShortCode(String stationShortCode) {
        if (stationShortCode == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(berthsByCrs.get(stationShortCode));
    }

    public Optional<RailNetworkLocation> lookupByTiploc(String tiploc) {
        if (tiploc == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(berthsByTiploc.get(tiploc));
    }

    public Optional<RailNetworkLocation> lookupByStanox(int stanox) {
        return Optional.ofNullable(berthsByStanox.get(stanox));
    }

    public List<RailNetworkLocation> search(String term) {
        RailNetworkLocation location = berthsByCrs.get(term);
        if (location != null) {
            return Arrays.asList(location);
        }
        location = berthsByTiploc.get(term);
        if (location != null) {
            return Arrays.asList(location);
        }
        try {
            location = berthsByStanox.get(Integer.parseInt(term));
            if (location != null) {
                return Arrays.asList(location);
            }
        } catch (NumberFormatException e) {
            // rather than compiling a regex, just catch the exception
        }

       return berthsByTiploc.values().stream()
               .filter(loc -> matches(loc.getDescription(), term))
               .collect(Collectors.toList());
    }

    private boolean matches(String actual, String searchTerm) {
        return searchTerm != null && actual != null &&
                (actual.toLowerCase().startsWith(searchTerm.toLowerCase()) ||
                actual.toLowerCase().contains(searchTerm.toLowerCase()));
    }

    public Optional<RailNetworkLocation> lookup(String identifer) {
        LocationIdentityType locationIdentityType = locationIdentityTypeParser.parse(identifer);
        switch (locationIdentityType) {
            case CRS_CODE:
                return lookupByShortCode(identifer);
            case STANOX:
                return lookupByStanox(Integer.parseInt(identifer));
            case TIPLOC:
                return lookupByTiploc(identifer);
        }
        return Optional.empty();
    }

    @Override
    public Optional<RailNetworkLocation> lookup(LocationLookupRequest request) {
        RailNetworkLocation loc = null;
        if (request.getCrsCode() != null) {
            loc = lookupByShortCode(request.getCrsCode())
                    .orElse(null);
        } else if (request.getTiploc() != null) {
            loc = lookupByTiploc(request.getTiploc())
                    .orElse(null);
        } else if (request.getStanox() != null) {
            loc = lookupByStanox(request.getStanox())
                    .orElse(null);
        } else if (request.getIdentifier() != null) {
            loc = lookup(request.getIdentifier())
                    .orElse(null);
        }
        return Optional.ofNullable(loc);
    }

    @Override
    public List<RailNetworkLocation> search(LocationSearchRequest request) {
        return search(request.getTerm());
    }
}
