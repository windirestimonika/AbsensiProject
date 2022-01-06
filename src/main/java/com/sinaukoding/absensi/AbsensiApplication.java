package com.sinaukoding.absensi;

import com.sinaukoding.absensi.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class AbsensiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbsensiApplication.class, args);
	}

	public static User getCurrentUser(){
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal != null && principal.getClass().equals(User.class)){
				return (User) principal;
			}
		}catch (Exception ignore){

		}
		return null;
	}

}
