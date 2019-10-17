package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author andy
* @date 2019-10-17
*/
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor {
}