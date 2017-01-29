package model.dao;

import java.util.List;
import java.util.Map;

import model.entity.Department;
import model.entity.User;

public interface SheetDao {
	long add(long idEnrollee, long idDepartment);

	Map<Department, List<User>> getSheet();

	void deleteDepartment(long idDepartment);

	void deleteEnrollee(long idEnrollee);

	void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment);
}
