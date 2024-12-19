package com.codegym.service;

import com.codegym.model.Song;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		update(song);
	}

	@Override
	public Song findById(int id) {
		String queryStr = "FROM Song WHERE id = :id";
		TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void update(Song song) {
		Transaction transaction = null;
		Song origin;
		if (song.getId() == 0) {
			origin = new Song();
		} else {
			origin = findById(song.getId());
		}
		try (Session session = sessionFactory.openSession())
		{
			transaction = session.beginTransaction();
			origin.setTitle(song.getTitle());
			origin.setArtist(song.getArtist());
			origin.setGender(song.getGender());
			origin.setSongFile(song.getSongFile());
			session.saveOrUpdate(origin);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void remove(int id) {

	}
}
