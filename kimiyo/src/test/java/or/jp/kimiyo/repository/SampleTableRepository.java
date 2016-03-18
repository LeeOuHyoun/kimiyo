package or.jp.kimiyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.jp.kimiyo.entity.SampleTable;

@Repository
public interface SampleTableRepository extends JpaRepository<SampleTable, Integer> {

	List<SampleTable> findAll();

//	SampleTable findById(String id);
}