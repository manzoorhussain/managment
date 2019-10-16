package com.comany.example.managment.managment.business;

import com.comany.example.managment.managment.bean.WsResponse;

import java.util.Map;

/**
 * Created by zam zam on 10/13/2019.
 */
abstract class Busniess {

   abstract public WsResponse applyBusniess(Map<String,String> map,String callFrom);
}
