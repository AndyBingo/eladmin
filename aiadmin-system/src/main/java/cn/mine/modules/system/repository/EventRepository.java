package cn.mine.modules.system.repository;

import cn.mine.modules.system.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
* @author andy
* @date 2019-10-17
*/
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor {


    /**
     *
     * @param start
     * @param end
     * @return
     */
    @Query(value = "select exception, count(1) from event e join algorithm a on e.algorithm_id=a.id where e.start_time > ?1 and e.end_time < ?2 group by exception", nativeQuery = true)
    List<Map<String, Object>> queryAllByStartTimeAndEndTimeGroupByException(String start, String end);

    @Query(value = "select count(1) from event where device_id=?1 and algorithm_id=?2 and is_closed=0 group by device_id, algorithm_id", nativeQuery = true)
    Integer countEventsByDeviceIdAndAlgorithmIdAndIsClosed(Long deviceId, Long algorithmId);
}