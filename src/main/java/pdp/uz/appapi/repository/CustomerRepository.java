package pdp.uz.appapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.appapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select (count(c) > 0) from Customer c where c.phoneNumber = ?1 and c.id <> ?2")
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
