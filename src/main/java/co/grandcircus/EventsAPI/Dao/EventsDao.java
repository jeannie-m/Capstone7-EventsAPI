package co.grandcircus.EventsAPI.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.EventsAPI.Model.FavEvent;



public interface EventsDao extends JpaRepository<FavEvent, Long>{

}
