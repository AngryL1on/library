package dev.angryl1on.library.core.repositories;

import dev.angryl1on.library.core.models.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID> {
    /**
     * Finds libraries by their name.
     *
     * @param name the name or part of the name of the library to search for.
     * @return a list of libraries that match the search criteria.
     */
    List<Library> findByName(String name);

    /**
     * Finds libraries by their address.
     *
     * @param address the address or part of the address of the library to search for.
     * @return a list of libraries located at the specified address.
     */
    List<Library> findByAddress(String address);
}
