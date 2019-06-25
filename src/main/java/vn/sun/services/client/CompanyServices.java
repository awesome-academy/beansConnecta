package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.Company;

public interface CompanyServices {
	public List<Company> loadCompanies();
	public void createCompany();
	
}
