package com.codegym.service;

import com.codegym.model.Song;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongService implements ISongService {
	private static SessionFactory sessionFactory;
	private static EntityManager entityManager;

	static {
		try {
			sessionFactory = new Configuration()
					.configure("hibernate.conf.xml")
					.buildSessionFactory();
			entityManager = sessionFactory.createEntityManager();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Song> findAll() {
		String queryStr = "FROM Song";
		TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
		return query.getResultList();
	}

	@Override
	public void add(Song song) {

	}

	@Override
	public Song findById(int id) {
		return null;
	}

	@Override
	public void update(Song song) {

	}

	@Override
	public void remove(int id) {

	}
}
