package com.fazlyeva.dao;

import com.fazlyeva.model.Advert;
import java.util.List;

public interface IAdvertDao {

    public List<Advert> getAllAdverts();

    public List<Advert> getAllAdvertsByPersonId(int person_id);

    public Advert getAdvertById(int id);

    public boolean createAdvert(Advert advert);

    public boolean editAdvert(Advert advert, int id);

    public boolean deleteAdvert(int id);
}
