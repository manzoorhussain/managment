package com.comany.example.managment.managment.endpoint;

import com.comany.example.managment.managment.bean.WsResponse;
import com.comany.example.managment.managment.business.UserBusniess;
import com.comany.example.managment.managment.utilities.ApplicationConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zam zam on 10/12/2019.
 */

@RestController
@RequestMapping(path = "/user")
public class RESTUser {

    @Autowired
    private UserBusniess userBusniess;


    @RequestMapping(path = "/get",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getUser() {

        WsResponse wsResponse = userBusniess.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(wsResponse);
    }


    @PostMapping(path = "/add",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity<Object> createUser(@RequestBody MultiValueMap<String,String> paramMap) {




        Map<String,String> map=new HashMap<>();
        //map.put("usercode",paramMap.get("usercode").get(0));
        map.put("loginid",paramMap.get("loginid").get(0));
        map.put("firstname",paramMap.get("firstname").get(0));
        map.put("middlename",paramMap.get("middlename").get(0));
        map.put("lastname",paramMap.get("lastname").get(0));
        map.put("address",paramMap.get("address").get(0));



        WsResponse wsResponse=userBusniess.applyBusniess(map, ApplicationConstraints.CREATE_USER);

        return ResponseEntity.status(HttpStatus.OK).body(wsResponse);
    }

    @RequestMapping(path = "/get/{usercode}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getUserByCode(@PathVariable("usercode") String userCode) {


        Map<String,String> map=new HashMap<>();
        map.put("usercode",userCode);

        WsResponse wsResponse=userBusniess.applyBusniess(map, ApplicationConstraints.GETUSER_BY_CODE);

        return ResponseEntity.status(HttpStatus.OK).body(wsResponse);
    }



    @RequestMapping(path = "/delete/{usercode}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteUser(@PathVariable("usercode") String userCode) {


        Map<String,String> map=new HashMap<>();
        map.put("usercode",userCode);

        WsResponse wsResponse=userBusniess.applyBusniess(map, ApplicationConstraints.DELETE_USER);

        return ResponseEntity.status(HttpStatus.OK).body(wsResponse);
    }
}
