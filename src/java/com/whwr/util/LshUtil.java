/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whwr.util;

import com.whwr.service.impl.A01ServiceImpl;
import com.whwr.service.inter.A01ServiceInter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class LshUtil {
    
    private static HashMap<String,Integer> lshMap = new HashMap<String,Integer>();
    
    public static String getRcdLsh(){
        String result = DateUtil.DateToStr(new Date());
        String key = "rkd" + DateUtil.DateToStr(new Date());
        Integer i = 0;
        if(lshMap.get(key) == null){
            Set<String> keySet = lshMap.keySet();
            for(String k : keySet){
                if(k.startsWith("rkd")){
                    lshMap.remove(k);
                }
            }
            String sql = "select max(lsh) from ckrkd where lsh like '"+result+"%'";
            A01ServiceInter a01ServiceInter = (A01ServiceImpl) ApplicationUtil.getBean("a01ServiceImpl");
            List list = a01ServiceInter.getSqlResult(sql);
            if(list.isEmpty()){
                i = 1;
            }else{
                Object obj = list.get(0);
                i = Integer.valueOf(obj.toString().substring(10)) + 1;
            }
        }else{
            i = lshMap.get(key) + 1;
        }
        result += getNewNumber(i,4);
        lshMap.put(key, i);
        return result;
    }
    
    public static String getKcLsh(){
        String result = DateUtil.DateToStr(new Date());
        String key = "kc" + DateUtil.DateToStr(new Date());
        Integer i = 0;
        if(lshMap.get(key) == null){
            Set<String> keySet = lshMap.keySet();
            for(String k : keySet){
                if(k.startsWith("kc")){
                    lshMap.remove(k);
                }
            }
            String sql = "select max(lsh) from ckkc where lsh like '"+result+"%'";
            A01ServiceInter a01ServiceInter = (A01ServiceImpl) ApplicationUtil.getBean("a01ServiceImpl");
            List list = a01ServiceInter.getSqlResult(sql);
            if(list.isEmpty()){
                i = 1;
            }else{
                Object obj = list.get(0);
                i = Integer.valueOf(obj.toString().substring(10)) + 1;
            }
        }else{
            i = lshMap.get(key) + 1;
        }
        result += getNewNumber(i,4);
        lshMap.put(key, i);
        return result;
    }
    
    public static String getNewNumber(Integer i,int len){
        String result = i.toString();
        while(result.length() < len){
            result += "0";
        }
        return result;
    }
    
}
