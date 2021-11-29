package com.minibank.repository;

import com.minibank.entity.SavedPhone;
import com.minibank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedPhoneRepository extends JpaRepository<SavedPhone, Integer> {

    public List<SavedPhone> findSavedPhonesByUser(User user);
}
