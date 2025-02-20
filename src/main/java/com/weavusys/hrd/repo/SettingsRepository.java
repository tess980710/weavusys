package com.weavusys.hrd.repo;

import com.weavusys.hrd.entity.Amount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Amount, Integer> {
    Amount findById(int i);
}