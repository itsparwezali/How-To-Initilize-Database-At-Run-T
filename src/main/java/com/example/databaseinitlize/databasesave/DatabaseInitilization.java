package com.example.databaseinitlize.databasesave;

import com.example.databaseinitlize.entities.Status;
import com.example.databaseinitlize.entities.StatusInitConfiguration;
import com.example.databaseinitlize.repo.StatusRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Scope("singleton")
public class DatabaseInitilization {

    @Autowired
    private  StatusInitConfiguration statusInitConfiguration;

    @Autowired
    private  StatusRepo statusRepo;

//    public DatabaseInitilization(StatusInitConfiguration statusInitConfiguration, StatusRepo statusRepo) {
//        this.statusInitConfiguration = statusInitConfiguration;
//        this.statusRepo = statusRepo;
//    }

    public void init(){
        populateStatus();
    }

    private void populateStatus() {

        List<Status> statusList = statusInitConfiguration.getStatusList();
        if(statusRepo.count() <= 0){
            statusList.forEach(statusRepo::save);
        }else{

            List<String> collect = statusList.stream()
                    .map(Status::getName)
                    .collect(Collectors.toList());
            List<String> persistedStatus = statusRepo.getAllStatusByName(collect);

            statusList.stream().filter(statusName -> !persistedStatus.contains(statusName.getName()))
                    .forEach(statusRepo :: save);
        }
    }
}
