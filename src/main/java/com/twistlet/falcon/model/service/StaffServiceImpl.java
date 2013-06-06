package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconStaffRepository;
import com.twistlet.falcon.model.repository.FalconUserRepository;

@Service
public class StaffServiceImpl implements StaffService {

	private final FalconUserRepository falconUserRepository;
	private final FalconStaffRepository falconStaffRepository;
	private final MailSenderService mailSenderService;
	private final SmsService smsService;

	@Autowired
	public StaffServiceImpl(FalconUserRepository falconUserRepository, FalconStaffRepository falconStaffRepository,
			MailSenderService mailSenderService, SmsService smsService) {
		this.falconUserRepository = falconUserRepository;
		this.falconStaffRepository = falconStaffRepository;
		this.mailSenderService = mailSenderService;
		this.smsService = smsService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listPatients(final String partialName) {
		return falconUserRepository.findByRolenameAndNameLike("ROLE_PATRON", partialName);
	}

	@Override
	public boolean sendEmail(final String name, final String address, final String message) {
		final String sendTo = "\"" + name + "\"" + " <" + address + ">";
		try {
			mailSenderService.send(sendTo, message);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendSms(final String phone, final String message) {
		try {
			smsService.send(phone, message);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public FalconUser getUser(final String username) {
		return falconUserRepository.findOne(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdmin(String admin) {
		FalconUser user = new FalconUser();
		user.setUsername(admin);
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserAndValid(user, true);
		return staffs;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveStaff(FalconStaff staff) {
		falconStaffRepository.save(staff);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdminNameLike(FalconUser admin, String username) {
		return falconStaffRepository.findByFalconUserNameLike(admin, username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdminNricLike(FalconUser admin, String nric) {
		return falconStaffRepository.findByFalconUserNricLike(admin, nric);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdminEmailLike(FalconUser admin, String email) {
		return falconStaffRepository.findByFalconUserEmailLike(admin, email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdminStaffLike(FalconUser admin, FalconStaff staff) {
		return falconStaffRepository.findByFalconUserStaffLike(admin, staff);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconStaff> listStaffByAdminMobileLike(FalconUser admin, String mobile) {
		return falconStaffRepository.findByFalconUserHpTelLike(admin, mobile);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteStaff(FalconStaff staff) {
		falconStaffRepository.delete(staff);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<FalconStaff> listAvailableStaff(FalconUser admin, Date start, Date end) {
		List<FalconStaff> staffs = falconStaffRepository.findByFalconUserAndValid(admin, true);
		Set<FalconStaff> busyStaffs = falconStaffRepository.findStaffDateRange(admin, start, end);
		Set<FalconStaff> availableStaffs = new HashSet<>();
		for (FalconStaff staff : staffs) {
			boolean found = false;
			for (FalconStaff busyStaff : busyStaffs) {
				if (staff.getId().equals(busyStaff.getId())) {
					found = true;
					break;
				}
			}
			if (!found) {
				availableStaffs.add(staff);
			}
		}
		return availableStaffs;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listPatronByAdminId(String adminId) {
		return falconUserRepository.findByAdmin(adminId);
	}

}
