package com.codegym.service;

import com.codegym.model.Song;

import java.util.List;

public interface ISongService {
	List<Song> findAll();

	void add(Song song);

	Song findById(int id);

	void update(Song song);

	void remove(int id);
}
