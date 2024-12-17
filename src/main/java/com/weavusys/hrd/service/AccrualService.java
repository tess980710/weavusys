package com.weavusys.hrd.service;

import com.weavusys.hrd.entity.Accrual;
import com.weavusys.hrd.entity.Employee;
import com.weavusys.hrd.repo.AccrualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccrualService {

    private final AccrualRepository accrualRepository;


    public int calculateTotalAccrual(Accrual accrual) {
        YearMonth startMonth = accrual.getStartDate() != null ? YearMonth.from(accrual.getStartDate()) : YearMonth.now();
        LocalDate endDate = accrual.getEndDate() != null ? accrual.getEndDate() : LocalDate.now();
        YearMonth endMonth = YearMonth.from(endDate);

        // 월수 계산, 월 초 및 월 말에 대한 특별 처리 추가
        long months = ChronoUnit.MONTHS.between(startMonth, endMonth);
        if (endDate.getDayOfMonth() < endDate.lengthOfMonth()) {
            months -= 1; // 종료일이 월말이 아닐 때, 한 달 빼기
        } else if (endDate.getDayOfMonth() == 1) {
            months += 1; // 종료일이 1일일 때, 그 달을 추가
        }

        int monthlyAmount = 5000;
        int result = (int) Math.max(months, 1) * monthlyAmount;
        accrual.setTotalAmount(result);

        if (accrual != null) {
            accrualRepository.save(accrual);
        }

        return result;
    }


    public Optional<Accrual> findByEmployeeId(String id) {
        return accrualRepository.findByEmployeeId(id);
    }

    public List<Accrual> findAll() {
        List<Accrual> accrualList = accrualRepository.findAll();
        for (Accrual accrual : accrualList) {
            if (!accrual.getEmployee().getEmployeeType().equals(Employee.EmployeeType.CONTRACT)) {
                if (accrual.getTotalAmount() == null || accrual.getTotalAmount() == 0) {
                    accrual.setTotalAmount(calculateTotalAccrual(accrual));
                }
            }
        }
        return accrualList;
    }

}
