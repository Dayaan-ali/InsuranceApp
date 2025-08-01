package in.sp.main.runner;

import in.sp.main.entities.CitizenPlan;
import in.sp.main.repo.CitizenPlanRepo;
import in.sp.main.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CitizenPlanRepo citizenPlanRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	citizenPlanRepo.deleteAll();
    	
        CitizenPlan c1 = new CitizenPlan();
        c1.setCitizenName("Dayaan Ali");
        c1.setGender("Male");
        c1.setPlanName("Cash Plan");
        c1.setPlanStatus("Approved");
        c1.setPlanStartDate(LocalDate.now());
        c1.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c1.setBenefitAmount(5000.0);

        CitizenPlan c2 = new CitizenPlan();
        c2.setCitizenName("Ankesh Verma");
        c2.setGender("Male");
        c2.setPlanName("Cash Plan");
        c2.setPlanStatus("Denied");
        c2.setDenialReason("Rental income");

        CitizenPlan c3 = new CitizenPlan();
        c3.setCitizenName("Anshul Malviya");
        c3.setGender("Male");
        c3.setPlanName("Cash Plan");
        c3.setPlanStatus("Terminated");
        c3.setDenialReason("Rental income");
        c3.setPlanStartDate(LocalDate.now().minusMonths(4));
        c3.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c3.setTerminationDate(LocalDate.now());
        c3.setTerminationReason("Employed");
        c3.setBenefitAmount(2000.0);

        CitizenPlan c4 = new CitizenPlan();
        c4.setCitizenName("kritika Tomar");
        c4.setGender("Female");
        c4.setPlanName("Food");
        c4.setPlanStatus("Approved");
        c4.setPlanStartDate(LocalDate.now());
        c4.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c4.setBenefitAmount(7000.0);

        CitizenPlan c5 = new CitizenPlan();
        c5.setCitizenName("Kylie Jenner");
        c5.setGender("Female");
        c5.setPlanName("Food");
        c5.setPlanStatus("Denied");
        c5.setDenialReason("Property damage");

        CitizenPlan c6 = new CitizenPlan();
        c6.setCitizenName("Elon Musk");
        c6.setGender("Male");
        c6.setPlanName("Food");
        c6.setPlanStatus("Terminated");
        c6.setDenialReason("Fraud");
        c6.setPlanStartDate(LocalDate.now().minusMonths(4));
        c6.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c6.setTerminationDate(LocalDate.now());
        c6.setTerminationReason("Khet Beech Dia");
        c6.setBenefitAmount(10000.0);

        CitizenPlan c7 = new CitizenPlan();
        c7.setCitizenName("Mark Zuckerberg");
        c7.setGender("Male");
        c7.setPlanName("Medical");
        c7.setPlanStatus("Approved");
        c7.setPlanStartDate(LocalDate.now());
        c7.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c7.setBenefitAmount(3000.0);

        CitizenPlan c8 = new CitizenPlan();
        c8.setCitizenName("Shreya");
        c8.setGender("Female");
        c8.setPlanName("Medical");
        c8.setPlanStatus("Denied");
        c8.setDenialReason("salaried");

        CitizenPlan c9 = new CitizenPlan();
        c9.setCitizenName("Bill Gates");
        c9.setGender("Male");
        c9.setPlanName("Medical");
        c9.setPlanStatus("Terminated");
        c9.setDenialReason("Black Money");
        c9.setPlanStartDate(LocalDate.now().minusMonths(4));
        c9.setPlanEndDate(java.time.LocalDate.now().plusMonths(6));
        c9.setTerminationDate(LocalDate.now());
        c9.setTerminationReason("Died");
        c9.setBenefitAmount(10000.0);
        List<CitizenPlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        citizenPlanRepo.saveAll(list);
    }
}
