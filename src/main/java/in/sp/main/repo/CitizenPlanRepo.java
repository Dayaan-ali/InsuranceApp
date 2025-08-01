package in.sp.main.repo;

import in.sp.main.entities.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

    @Query("select distinct (planName) from CitizenPlan")
    public List<String> getPlanNames();

    @Query("select distinct (planStatus) from CitizenPlan")
    public List<String> getPlanStatus();
}
