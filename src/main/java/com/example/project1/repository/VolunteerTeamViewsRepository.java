/*import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VolunteerTeamsViewRepository extends JpaRepository<VolunteerTeamsView, Long> {

    @Query("SELECT v FROM VolunteerTeamsView v WHERE v.volunteerUsername = :username")
    List<VolunteerTeamsView> findTeamsByVolunteerUsername(@Param("username") String username);
}*/