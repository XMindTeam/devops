package com.intel.spring.boot.sample.boot.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.intel.spring.boot.sample.boot.model.Character;
/**
 * Created by Ecic Chen on 2016/2/28.
 */
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
