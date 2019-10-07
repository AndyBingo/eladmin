package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author andy
* @date 2019-10-07
*/
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor {
}