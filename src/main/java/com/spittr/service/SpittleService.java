package com.spittr.service;

import com.spittr.exception.spitter.SpitterNotFoundException;
import com.spittr.pojo.Page;
import com.spittr.pojo.Spittle;

import java.util.List;

public interface SpittleService {
    List<Spittle> getListByUsernameAndPage(String username, int pageIndex, int pageSize);
    void saveSpittle(String username, String text, String attachment, boolean enabled);
    Spittle getLastestOne(String username);
    boolean deleteSpittle(String username, long id);
    boolean queryIfExistById(long spittleId);
    boolean isSpitterHasChangePermission(String username, long id);
    Spittle getSpittleDetail(long spittleId);
}
