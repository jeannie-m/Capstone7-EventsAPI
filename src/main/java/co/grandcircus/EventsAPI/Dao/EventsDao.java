package co.grandcircus.EventsAPI.Dao;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import co.grandcircus.EventsAPI.Entities.FavEvent;



public interface EventsDao extends JpaRepository<FavEvent, Long>{
	
	FavEvent findByEventId(String id);
	//FavEvent findByEventId(String id, Pageable pageable);

	//I think I can't figure this out cause I'm reading the instructions for the dao to seach our own repository which is totally a different method than using the api
	//https://www.baeldung.com/spring-data-jpa-pagination-sorting
	
	
}
