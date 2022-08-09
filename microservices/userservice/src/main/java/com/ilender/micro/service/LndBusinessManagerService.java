package com.ilender.micro.service;

import com.ilender.micro.entity.LndBusinessManager;
import com.ilender.micro.jpa.LndBusinessManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LndBusinessManagerService {

    @Autowired
    LndBusinessManagerRepository repository;

    public List<LndBusinessManager> findAll() {
        List<LndBusinessManager> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public LndBusinessManager create(LndBusinessManager lndBusinessManager) {

        return repository.save(lndBusinessManager);
    }

    public LndBusinessManager update(LndBusinessManager wcBusinessManager) {

        repository.save(wcBusinessManager);
        wcBusinessManager = find(wcBusinessManager.getId());
        return wcBusinessManager;
    }

    public LndBusinessManager find(Integer id) {
        LndBusinessManager lndBusinessManager = null;
        try {
            lndBusinessManager = repository.findById(id).get();
        } catch ( Exception e) {
        }
        return lndBusinessManager;
    }

    public LndBusinessManager delete(int id) {

        com.ilender.micro.entity.LndBusinessManager wcBusinessManager = find(id);
        if(wcBusinessManager != null){
            repository.delete(wcBusinessManager);
        }
        return wcBusinessManager;
    }

    public LndBusinessManager findOneByLndUserId(Integer lndUserId) {
        return repository.findOneByLndUserId(lndUserId);
    }
}
