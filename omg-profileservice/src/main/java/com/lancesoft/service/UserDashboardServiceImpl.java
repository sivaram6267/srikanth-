package com.lancesoft.service;

import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lancesoft.customexception.CustomException;
import com.lancesoft.dao.AddressRepo;
import com.lancesoft.dao.AuthoritiesRepo;
import com.lancesoft.dao.RegistrationRepo;
import com.lancesoft.dto.AddressDto;
import com.lancesoft.entity.AddressEntity;
import com.lancesoft.entity.Authorities;
import com.lancesoft.entity.RegistrationEntity;
import com.lancesoft.payload.ForgotPasswordPayload;

@Service
public class UserDashboardServiceImpl implements UserDashboardService {

	@Autowired
	private RegistrationRepo registrationRepo;
	
	@Autowired
	AuthoritiesRepo authoritiesRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	AddressRepo addressRepo;

	@Override
	public RegistrationEntity getMyProfile(String userName) {
		try {
			return registrationRepo.findByUserName(userName);
		} catch (Exception e) {
			throw new CustomException("error occured", e.getLocalizedMessage());
		}
	}

	@Override
	public ResponseEntity<RegistrationEntity> updateProfile(RegistrationEntity registrationEntity) {
		registrationEntity.setUser_id(registrationEntity.getUser_id());
		return new ResponseEntity<RegistrationEntity>(registrationRepo.save(registrationEntity), HttpStatus.OK);
	}

	public ResponseEntity<?> forgotPassword(ForgotPasswordPayload forgotPasswordPayload) {
		RegistrationEntity registrationEntity = registrationRepo.findByUserName(forgotPasswordPayload.getUserName());

		if (encoder.matches(forgotPasswordPayload.getOldPassword(), registrationEntity.getPassword())) {
			registrationEntity.setPassword(encoder.encode(forgotPasswordPayload.getNewPassword()));
			registrationRepo.save(registrationEntity);
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	public AddressEntity addAddress(AddressDto addressdto) {

		try {
			AddressEntity entity = new AddressEntity();
			ModelMapper mapper = new ModelMapper();
			mapper.map(addressdto, entity);
			entity.setDeafultAddress(true);
			return addressRepo.save(entity);
		} catch (Exception e) {
			throw new CustomException("error occured", e.getLocalizedMessage());
		}
	}

	@Override
	public AddressEntity updateAddress(AddressDto addressdto) {
		try {

			AddressEntity entity = new AddressEntity();
			ModelMapper mapper = new ModelMapper();
			mapper.map(addressdto, entity);
			return addressRepo.save(entity);
		} catch (Exception e) {
			throw new CustomException("error occured", e.getLocalizedMessage());
		}
	}

	// to add the delivery address
	public AddressEntity addNewAddress(AddressDto addressdto, String userName) {
		try {

			AddressEntity entity = new AddressEntity();
			ModelMapper mapper = new ModelMapper();
			List<AddressEntity> addressEntities = addressRepo.findByUserName(userName);
			Iterator<?> iterator = addressEntities.iterator();
			addressdto.setDeafultAddress(true);
			while (iterator.hasNext()) {
				AddressEntity addressEntity = (AddressEntity) iterator.next();
				addressEntity.setDeafultAddress(false);
				addressRepo.save(addressEntity);
			}

			mapper.map(addressdto, entity);
			return addressRepo.save(entity);
		} catch (Exception e) {
			throw new CustomException("error occured", e.getLocalizedMessage());
		}
	}
	
	@Override
	public List<AddressEntity> findAddressByUserName(String userName) {
		return addressRepo.findByUserName(userName);
	}

	@Override
	public Authorities findByRole(String role) {
		return authoritiesRepo.findByRole(role);
	}

	@Override
	public List<RegistrationEntity> findByAuthorities(Authorities authorities) {
		// TODO Auto-generated method stub
		return registrationRepo.findByAuthorities(authorities);
	}

	@Override
	public List<AddressEntity> findByAddressUserName(String userName) {
		return addressRepo.findByUserName(userName);
	}

	@Override
	public RegistrationEntity findByUserName(String userName) {
		return registrationRepo.findByUserName(userName);
	}

	@Override
	public AddressEntity findByAddressId(String addressId) {
		return addressRepo.findById(addressId).get();
	}

}