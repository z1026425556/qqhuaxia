package com.pengcheng.dao;

import com.pengcheng.domain.CombatPet;
import org.springframework.stereotype.Repository;

@Repository
public interface CombatPetMapper {

    void addOne(CombatPet combatPet);

}
