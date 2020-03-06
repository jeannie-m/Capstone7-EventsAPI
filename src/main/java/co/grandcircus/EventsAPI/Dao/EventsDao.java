package co.grandcircus.EventsAPI.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import co.grandcircus.EventsAPI.Entities.FavEvent;



public interface EventsDao extends JpaRepository<FavEvent, Long>{
	
	FavEvent findByEventId(String id);

}
