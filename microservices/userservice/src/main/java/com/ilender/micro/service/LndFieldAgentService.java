package com.ilender.micro.service;

import com.ilender.micro.common.DateUtil;
import com.ilender.micro.jpa.LndFieldAgentRepository;
import com.ilender.micro.entity.LndFieldAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LndFieldAgentService {

    @Autowired
    LndFieldAgentRepository repository;

    @Autowired
    ImageService imageService;

    public List<LndFieldAgent> findAll() {
        List<LndFieldAgent> list = (List<LndFieldAgent>) repository.findAll();
        processAfterLoad(list);
        return list;
    }

    public Object create(LndFieldAgent lndFieldAgent) {
        return null;
    }

    public LndFieldAgent update(LndFieldAgent wcWealthManager) {

        repository.save(wcWealthManager);
        wcWealthManager = find(wcWealthManager.getId());
        return wcWealthManager;
    }

    public LndFieldAgent find(Integer id) {
        LndFieldAgent info = null;
        try {
            info = repository.findById(id).get();
            processAfterLoad(info);
        } catch ( Exception e) {
        }
        return info;
    }

    public LndFieldAgent delete(int id) {
        return null;
    }

    public LndFieldAgent findOneByLndUserId(Integer id) {
        LndFieldAgent info = repository.findOneByLndUserId(id);
        processAfterLoad(info);
        return info;
    }

    private void processAfterLoad(List<LndFieldAgent> list) {
        for (LndFieldAgent info : list) {
            processAfterLoad(info);
        }
    }

    private void processAfterLoad(LndFieldAgent info) {
        info.setStartDateString(DateUtil.convertDDMMMYYY(info.getStartDate()));
        info.setImage(imageService.createImageUrl("male", info.getId(), true));
    }

}
