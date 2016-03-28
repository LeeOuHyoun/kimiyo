/**
 *
 */
package or.jp.kimiyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import or.jp.kimiyo.entity.Sample2Table;

/**
 * @author olee
 *
 */
@Repository
public interface Sample2TableRepository extends JpaRepository<Sample2Table, String> {

}
