package com.naveena.Reservation.controller;

import com.naveena.Reservation.model.Reservation;
import com.naveena.Reservation.repository.ReservationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
 // allows requests from React frontend
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    // In ReservationController.java
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new reservation with validation
    @PostMapping
    public ResponseEntity<?> createReservation(@Valid @RequestBody Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    // Update reservation with validation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @Valid @RequestBody Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setCustomerName(updatedReservation.getCustomerName());
                    reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
                    reservation.setGuests(updatedReservation.getGuests());
                    reservation.setReservationTime(updatedReservation.getReservationTime());
                    Reservation saved = reservationRepository.save(reservation);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservationRepository.delete(reservation);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
