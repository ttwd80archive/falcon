package com.twistlet.falcon.model.repository;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconStaffRepositoryCustom {
	
	List<FalconStaff> findByFalconUserNameLike(FalconUser falconUser, String name);
	
	List<FalconStaff> findByFalconUserNricLike(FalconUser falconUser, String nric);
	
	List<FalconStaff> findByFalconUserEmailLike(FalconUser falconUser, String email);
	
	List<FalconStaff> findByFalconUserStaffLike(FalconUser falconUser, FalconStaff staff);

}
