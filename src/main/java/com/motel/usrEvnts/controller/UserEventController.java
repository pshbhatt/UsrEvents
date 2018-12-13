package com.motel.usrEvnts.controller;

import com.motel.usrEvnts.UserEventRepository;
import com.motel.usrEvnts.entity.UserEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/userEvent")
public class UserEventController {

    @Autowired
    UserEventRepository repo;

    @PostMapping(value="/addEvent")
    public UserEvents addEvents(@RequestBody UserEvents events){
        return repo.save(events);
    }

    @GetMapping(value="/timestamp/{timestamp}")
    public List<UserEvents> getByTimstamp(@PathVariable Timestamp timestamp){
    return repo.findByTimestamp(timestamp);
    }

    @GetMapping(value="/userId/{userId}")
    public List<UserEvents> getByUserId(@PathVariable Long userId){
        return repo.findByUserId(userId);
    }
}
