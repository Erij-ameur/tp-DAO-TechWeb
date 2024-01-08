package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;
import monprojet.service.PopulationByCountry;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

        @Query(value = "SELECT SUM(population) AS population" + " FROM City" + " WHERE id=:idPays", nativeQuery = true)
        public Integer getPopulationById(Integer idPays);
    
        @Query(value = "SELECT co.id as id, SUM(population) as population " +
            "FROM Country co " +
            "INNER JOIN City ci ON co.id = ci.country_id " +
            "GROUP BY id"
            , nativeQuery = true)
    public List<PopulationByCountry> getPopulation();
}
