package or.jp.kimiyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.jp.kimiyo.entity.SampleTable;

@Repository
public interface SampleTableRepository extends JpaRepository<SampleTable, Integer> {

}
