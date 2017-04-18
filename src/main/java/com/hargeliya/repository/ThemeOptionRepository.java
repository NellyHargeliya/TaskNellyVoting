package com.hargeliya.repository;

import com.hargeliya.models.ThemeOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ThemeOptionRepository extends CrudRepository<ThemeOption,Long>{

}
