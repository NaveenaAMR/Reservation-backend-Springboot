package com.naveena.Reservation.repository;

import com.naveena.Reservation.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {


    Users findByUsername(String username);
}
