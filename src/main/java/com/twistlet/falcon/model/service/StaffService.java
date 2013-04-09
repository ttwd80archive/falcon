package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;

public interface StaffService {
	List<FalconUser> listPatients(String partialName);
	
	List<FalconStaff> listStaffByAdmin(String admin);
	
	List<FalconStaff> listStaffByAdminNameLike(FalconUser admin, String name);

	List<FalconStaff> listStaffByAdminNricLike(FalconUser admin, String nric);
	
	List<FalconStaff> listStaffByAdminEmailLike(FalconUser admin, String nric);
	
	List<FalconStaff> listStaffByAdminMobileLike(FalconUser admin, String mobile);
	
	List<FalconStaff> listStaffByAdminStaffLike(FalconUser admin, FalconStaff staff);
	
	FalconUser getUser(String username);
	
	void saveStaff(FalconStaff staff);
	
	void deleteStaff(FalconStaff staff);

	boolean sendEmail(String name, String address, String message);

	boolean sendSms(String phone, String message);
}
