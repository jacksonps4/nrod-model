package com.cwpad.rail.services;

import com.cwpad.rail.model.TrainOperator;

import java.io.IOException;

public interface TrainOperatingCompanyRepository {
	TrainOperator searchByAtocCode(String atocCode);

	TrainOperator searchBySectorCode(int sectorCode);

	TrainOperator searchByBusinessCode(String businessCode);

	TrainOperator searchByCompanyName(String companyName);

	static TrainOperatingCompanyRepository instance() throws IOException {
		return new TrainOperatingCompanyRepositoryImpl();
	}
}
