package com.sinaukoding.absensi.service;

import com.sinaukoding.absensi.common.RestResult;
import com.sinaukoding.absensi.common.StatusCode;
import com.sinaukoding.absensi.entity.Employee;
import com.sinaukoding.absensi.entity.User;
import com.sinaukoding.absensi.dao.BaseDAO;
import com.sinaukoding.absensi.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }

    @Autowired
    private BankService bankService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DivisionService divisionService;

    @Transactional
    public User register(User param, User.Role role){
        User reference = dao.findOne(new User(param.getUsername()));

        if(reference != null){
            return null;
        }else {
            param.setRole(role);
            param.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));

            param.setBank(bankService.findByName(param.getBank()));
            param.setCompany(companyService.findByName(param.getCompany()));
            param.setPosition(positionService.findByName(param.getPosition()));
            param.setDivision(divisionService.findByName(param.getDivision()));

            return dao.save(param);
        }
    }

    @Transactional
    public RestResult login(User param){
        RestResult result = new RestResult(StatusCode.PASSWORD_OR_USER_NOT_REGISTERED);

        User currentUser = dao.findOne(param);

        if (currentUser == null){
            return result;
        }else if(currentUser.getPassword() != null && BCrypt.checkpw(param.getPassword(), currentUser.getPassword())){
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(currentUser.getUsername(), currentUser.getPassword(), new ArrayList<>());

            currentUser.setToken(jwtTokenService.generateToken(userDetails));

            result.setData(currentUser);
            result.setStatus(StatusCode.LOGIN_SUCCESS);
        }else {
            result.setStatus(StatusCode.LOGIN_FAILED);
        }

        return result;
    }

    @Transactional
    public User update(User entity){
        if (entity.getId() != null){
            User reference = getDAO().findReference(entity.getId());

            reference.setProfileName(entity.getProfileName() != null
                    ? entity.getProfileName()
                    : reference.getProfileName());

            reference.setNickName(entity.getNickName() != null
                    ? entity.getNickName()
                    : reference.getNickName());

            reference.setReligion(entity.getReligion() != null
                    ? entity.getReligion()
                    : reference.getReligion());

            reference.setPhone(entity.getPhone() != null
                    ? entity.getPhone()
                    : reference.getPhone());

            reference.setPendidikanTerakhir(entity.getPendidikanTerakhir() != null
                    ? entity.getPendidikanTerakhir()
                    : reference.getPendidikanTerakhir());

            reference.setPhone(entity.getPhone() != null
                    ? entity.getPhone()
                    : reference.getPhone());

            reference.setMaritalStatus(entity.getMaritalStatus() != null
                    ? entity.getMaritalStatus()
                    : reference.getMaritalStatus());

            reference.setDateofBirth(entity.getDateofBirth() != null
                    ? entity.getDateofBirth()
                    : reference.getDateofBirth());

            reference.setPlaceofBirth(entity.getPlaceofBirth() != null
                    ? entity.getPlaceofBirth()
                    : reference.getPlaceofBirth());

            reference.setNoKtp(entity.getNoKtp() != null
                    ? entity.getNoKtp()
                    : reference.getNoKtp());

            reference.setNoRekening(entity.getNoRekening() != null
                    ? entity.getNoRekening()
                    : reference.getNoRekening());

            reference.setNpwp(entity.getNpwp() != null
                    ? entity.getNpwp()
                    : reference.getNpwp());

            reference.setNamaIbu(entity.getNamaIbu() != null
                    ? entity.getNamaIbu()
                    : reference.getNamaIbu());

            reference.setNobpjsKesehatan(entity.getNobpjsKesehatan() != null
                    ? entity.getNobpjsKesehatan()
                    : reference.getNobpjsKesehatan());

            reference.setNobpjsKetenagakerjaan(entity.getNobpjsKetenagakerjaan() != null
                    ? entity.getNobpjsKetenagakerjaan()
                    : reference.getNobpjsKetenagakerjaan());

            reference.setDomicileAddress(entity.getDomicileAddress() != null
                    ? entity.getDomicileAddress()
                    : reference.getDomicileAddress());

            reference.setResidenceAddress(entity.getResidenceAddress() != null
                    ? entity.getResidenceAddress()
                    : reference.getResidenceAddress());

            entity = reference;

            return entity;
        }

        return null;
    }

    @Transactional
    public void inactiveUser(User entity){
        if (entity.getId() != null){
            User reference = getDAO().findReference(entity.getId());

            reference.setActive(entity.getActive().equals(Boolean.TRUE)
                    ? Boolean.FALSE
                    : reference.getActive());
        }
    }
}
