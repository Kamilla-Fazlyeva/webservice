package com.fazlyeva.service;

import com.fazlyeva.dao.AdvertDao;
import com.fazlyeva.dao.IAdvertDao;
import com.fazlyeva.model.Advert;
import java.sql.SQLException;
import java.util.List;

public class AdvertService implements IAdvertService {

    private IAdvertDao advertDao = new AdvertDao();

    public AdvertService() throws SQLException {
    }

    @Override
    public List<Advert> getAllAdverts() {
        return advertDao.getAllAdverts();
    }

    @Override
    public List<Advert> getAllAdvertsByPersonId(int person_id) {
        return advertDao.getAllAdvertsByPersonId(person_id);
    }

    @Override
    public Advert getAdvertById(int id) {
        return advertDao.getAdvertById(id);
    }

    @Override
    public boolean createAdvert(Advert advert) {
        return advertDao.createAdvert(advert);
    }

    @Override
    public boolean editAdvert(Advert advert, int id) {
        return advertDao.editAdvert(advert, id);
    }

    @Override
    public boolean deleteAdvert(int id) {
        return advertDao.deleteAdvert(id);
    }
}
