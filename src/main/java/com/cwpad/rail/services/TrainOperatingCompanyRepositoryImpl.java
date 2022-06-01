package com.cwpad.rail.services;

import com.cwpad.rail.model.TrainOperator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

class TrainOperatingCompanyRepositoryImpl implements
        TrainOperatingCompanyRepository {
	private final Set<TrainOperator> operators = new HashSet<>();

	public TrainOperatingCompanyRepositoryImpl() throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().
				getClassLoader().getResourceAsStream("db/toc.csv")))) {
			// don't need header
			reader.readLine();
			
			for (String line = null; (line = reader.readLine()) != null;) {
				String[] components = line.split(",");
				TrainOperator op = new TrainOperator();
				op.setName(components[0]);
				op.setBusinessCode(components[1]);
				op.setSectorCode(Integer.parseInt(components[2]));
				op.setAtocCode(components[3]);
				operators.add(op);
			}
		}
	}

	TrainOperator getUnique(Predicate<TrainOperator> p) {
		try {
			return operators.stream().filter(p).findAny().get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public TrainOperator searchByCompanyName(String companyName) {
		return getUnique(o -> companyName.equals(o.getName()));
	}

	@Override
	public TrainOperator searchByBusinessCode(String businessCode) {
		return getUnique(o -> businessCode.equals(o.getBusinessCode()));
	}

	@Override
	public TrainOperator searchBySectorCode(int sectorCode) {
		return getUnique(o -> sectorCode == o.getSectorCode());
	}

	@Override
	public TrainOperator searchByAtocCode(String atocCode) {
		return getUnique(o -> atocCode.equals(o.getAtocCode()));
	}
}
