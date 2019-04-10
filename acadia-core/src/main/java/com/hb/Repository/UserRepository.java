package com.hb.Repository;

import com.hb.Model.Address;
import com.hb.Model.user.Role;
import com.hb.Model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param uuid
     * @return the given User
     */
    public User findByUuid(String uuid);

    /**
     * @param firstName
     * @return list of the given Users
     */
    public List<User> findByFirstName(String firstName);

    /**
     * @param name
     * @return list of the given Users
     */
    public List<User> findByName(String name);

    /**
     * @param email
     * @return the given User
     */
    public User findByEmail(String email);

    /**
     * @param role
     * @return list of the given Users
     */
    public List<User> findByRole(Role role);

    /**
     * @param address
     * @return the given User
     */
    User findByAddress(Address address);

    /**
     * Pagination
     *
     * @param pageable
     * @return
     */
    @Override
    Page<User> findAll(Pageable pageable);

    /**
     *
     * @param pageable
     * @return
     */

    // Paginations

    /**
     * @param pageable
     * @return
     */
    Page<User> findByName(String name, Pageable pageable);

    /**
     * @param pageable
     * @return
     */
    Page<User> findByFirstName(String firstName, Pageable pageable);

    @Query("select u from User u where u.email like CONCAT('%',:search,'%') or u.firstName like CONCAT('%',:search,'%') or u.name like CONCAT('%',:search,'%')")
    Page<User> findUsersByResearch(@Param("search") String search, Pageable pageable);


}

